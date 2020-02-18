import AvroKotlinLab.Schemas.Amount
import org.apache.avro.Conversions.DecimalConversion
import org.apache.avro.LogicalTypes
import org.apache.avro.Schema
import org.apache.avro.file.DataFileWriter
import org.apache.avro.generic.GenericData
import org.apache.avro.generic.GenericDatumWriter
import org.apache.avro.generic.GenericRecord
import org.apache.avro.io.DatumWriter
import org.apache.avro.specific.SpecificDatumWriter
import java.io.File
import java.math.BigDecimal
import java.nio.ByteBuffer


fun main(args: Array<String>) {
    val schema = getSchema()
    println(schema.toString(true))
    writeGenericRecords(schema, "amounts-generic.avro")
    writeSpecificRecords(schema, "amounts-specific.avro")
}

fun toMoney(x: BigDecimal): ByteBuffer {
    val bytes = DecimalConversion().toBytes(x, null, LogicalTypes.decimal(4, 2))
    return bytes
}

fun writeSpecificRecords(schema: Schema, filename: String) {
    val integerAmount = AvroKotlinLab.Schemas.Amount.newBuilder()
        .setValue(toMoney(BigDecimal.valueOf(100, 2))) // 1.00
        .build()
    val decimalAmount = AvroKotlinLab.Schemas.Amount.newBuilder()
        .setValue(toMoney(BigDecimal.valueOf(128, 2))) // 1.28
        .build()
    val userDatumWriter: DatumWriter<Amount> = SpecificDatumWriter<Amount>(Amount::class.java)
    val file = File(filename)
    println("Saving ${file.absolutePath}")
    DataFileWriter<Amount>(userDatumWriter).use {
        it.create(schema, file)
        it.append(integerAmount)
        it.append(decimalAmount)
    }
}


private fun writeGenericRecords(schema: Schema, filename: String) {
    val integerAmount = GenericData.Record(schema)
    integerAmount.put("value", toMoney(BigDecimal.valueOf(4200, 2)))
    println("Int: ${integerAmount.toString()}")

    val decimalAmount = GenericData.Record(schema)
    decimalAmount.put("value", toMoney(BigDecimal.valueOf(1234, 2)))
    println("Dec: ${decimalAmount.toString()}")

    val datumWriter: DatumWriter<GenericRecord> = GenericDatumWriter(schema)
    val file = File(filename)
    println("Saving ${file.absolutePath}")
    DataFileWriter(datumWriter).use {
        it.create(schema, file)
        it.append(integerAmount)
        it.append(decimalAmount)
        it.close()
    }
}

private fun getSchema(): Schema {
    val schemaJson =
        """
            {
                "namespace": "AvroKotlinLab.Schemas",
                "type": "record",
                "name": "Amount",
                "fields": [
                    {
                        "name": "value",
                        "type": "bytes",
                        "logicalType": "decimal",
                        "precision": 4,
                        "scale": 2
                    }
                ]
            }
        """.trimIndent()
    return Schema.Parser().parse(schemaJson)!!
}