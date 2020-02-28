package avrokotlinlab

import AvroKotlinLab.Schemas.Amount
import org.apache.avro.Conversions.DecimalConversion
import org.apache.avro.LogicalTypes
import org.apache.avro.Schema
import org.apache.avro.file.DataFileReader
import org.apache.avro.file.DataFileWriter
import org.apache.avro.generic.GenericData
import org.apache.avro.generic.GenericDatumReader
import org.apache.avro.generic.GenericDatumWriter
import org.apache.avro.generic.GenericRecord
import org.apache.avro.io.DatumWriter
import org.apache.avro.specific.SpecificDatumReader
import org.apache.avro.specific.SpecificDatumWriter
import java.io.File
import java.math.BigDecimal
import java.nio.ByteBuffer


fun main(args: Array<String>) {
    val schema = getSchema()
    println(schema.toString(true))
    writeGenericRecords(schema, "amounts-generic.avro")
    writeSpecificRecords(schema, "amounts-specific.avro")
    readGenericRecords(schema, "amounts-generic.avro")
    readSpecificRecords("amounts-specific.avro")
}


val MONEY_PRECISION = 10
val MONEY_SCALE = 2
val MONEY_SCHEMA = LogicalTypes.decimal(MONEY_PRECISION, MONEY_SCALE)

fun toMoney(x: BigDecimal): ByteBuffer {
    val bytes = DecimalConversion().toBytes(x, null, MONEY_SCHEMA)
    return bytes
}

fun fromMoney(x: ByteBuffer): BigDecimal {
    return DecimalConversion().fromBytes(x, null, MONEY_SCHEMA)
}


fun writeSpecificRecords(schema: Schema, filename: String) {
    val integerAmount = Amount.newBuilder()
        .setValue(toMoney(BigDecimal.valueOf(100, 2))) // 1.00
        .build()
    val decimalAmount = Amount.newBuilder()
        .setValue(toMoney(BigDecimal.valueOf(128, 2))) // 1.28
        .build()
    val userDatumWriter: DatumWriter<Amount> = SpecificDatumWriter<Amount>(
        Amount::class.java
    )
    val file = File(filename)
    println("Saving ${file.absolutePath}")
    DataFileWriter<Amount>(userDatumWriter).use {
        it.create(schema, file)
        it.append(integerAmount)
        it.append(decimalAmount)
    }
}

private fun readSpecificRecords(filename: String) {
    val file = File(filename)
    println("Loading ${file.absolutePath}")
    val datumReader = SpecificDatumReader<Amount>()
    DataFileReader(file, datumReader).use { fileReader ->
        fileReader.forEach {
            println("\tAmount with value: ${fromMoney(it.value)}")
        }
    }
}


private fun writeGenericRecords(schema: Schema, filename: String) {
    val integerAmount = GenericData.Record(schema)
    integerAmount.put(
        "value",
        toMoney(BigDecimal.valueOf(4200, 2))
    )
    println("Int: ${integerAmount.toString()}")

    val decimalAmount = GenericData.Record(schema)
    decimalAmount.put(
        "value",
        toMoney(BigDecimal.valueOf(1234, 2))
    )
    println("Dec: ${decimalAmount.toString()}")

    val datumWriter: DatumWriter<GenericRecord> = GenericDatumWriter(schema)
    val file = File(filename)
    println("Saving ${file.absolutePath}")
    DataFileWriter(datumWriter).use {
        it.create(schema, file)
        it.append(integerAmount)
        it.append(decimalAmount)
    }
}


private fun readGenericRecords(schema: Schema, filename: String) {
    val file = File(filename)
    println("Loading ${file.absolutePath}")
    val datumReader = GenericDatumReader<GenericRecord>(schema)
    DataFileReader(file, datumReader).use { fileReader ->
        fileReader.forEach {
            val buffer = it.get("value") as ByteBuffer
            println("\tRecord with value: ${fromMoney(buffer)}")
        }
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
                        "precision": ${MONEY_PRECISION},
                        "scale": ${MONEY_SCALE}
                    }
                ]
            }
        """.trimIndent()
    return Schema.Parser().parse(schemaJson)!!
}
