/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package AvroKotlinLab.Schemas;

import org.apache.avro.specific.SpecificData;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@org.apache.avro.specific.AvroGenerated
public class Amount extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 6934858402423364377L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"Amount\",\"namespace\":\"AvroKotlinLab.Schemas\",\"fields\":[{\"name\":\"value\",\"type\":\"bytes\",\"logicalType\":\"decimal\",\"precision\":4,\"scale\":2}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<Amount> ENCODER =
      new BinaryMessageEncoder<Amount>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<Amount> DECODER =
      new BinaryMessageDecoder<Amount>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<Amount> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<Amount> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<Amount> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<Amount>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this Amount to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a Amount from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a Amount instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static Amount fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

   private java.nio.ByteBuffer value;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public Amount() {}

  /**
   * All-args constructor.
   * @param value The new value for value
   */
  public Amount(java.nio.ByteBuffer value) {
    this.value = value;
  }

  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return value;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: value = (java.nio.ByteBuffer)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'value' field.
   * @return The value of the 'value' field.
   */
  public java.nio.ByteBuffer getValue() {
    return value;
  }


  /**
   * Sets the value of the 'value' field.
   * @param value the value to set.
   */
  public void setValue(java.nio.ByteBuffer value) {
    this.value = value;
  }

  /**
   * Creates a new Amount RecordBuilder.
   * @return A new Amount RecordBuilder
   */
  public static AvroKotlinLab.Schemas.Amount.Builder newBuilder() {
    return new AvroKotlinLab.Schemas.Amount.Builder();
  }

  /**
   * Creates a new Amount RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new Amount RecordBuilder
   */
  public static AvroKotlinLab.Schemas.Amount.Builder newBuilder(AvroKotlinLab.Schemas.Amount.Builder other) {
    if (other == null) {
      return new AvroKotlinLab.Schemas.Amount.Builder();
    } else {
      return new AvroKotlinLab.Schemas.Amount.Builder(other);
    }
  }

  /**
   * Creates a new Amount RecordBuilder by copying an existing Amount instance.
   * @param other The existing instance to copy.
   * @return A new Amount RecordBuilder
   */
  public static AvroKotlinLab.Schemas.Amount.Builder newBuilder(AvroKotlinLab.Schemas.Amount other) {
    if (other == null) {
      return new AvroKotlinLab.Schemas.Amount.Builder();
    } else {
      return new AvroKotlinLab.Schemas.Amount.Builder(other);
    }
  }

  /**
   * RecordBuilder for Amount instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<Amount>
    implements org.apache.avro.data.RecordBuilder<Amount> {

    private java.nio.ByteBuffer value;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(AvroKotlinLab.Schemas.Amount.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.value)) {
        this.value = data().deepCopy(fields()[0].schema(), other.value);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
    }

    /**
     * Creates a Builder by copying an existing Amount instance
     * @param other The existing instance to copy.
     */
    private Builder(AvroKotlinLab.Schemas.Amount other) {
      super(SCHEMA$);
      if (isValidValue(fields()[0], other.value)) {
        this.value = data().deepCopy(fields()[0].schema(), other.value);
        fieldSetFlags()[0] = true;
      }
    }

    /**
      * Gets the value of the 'value' field.
      * @return The value.
      */
    public java.nio.ByteBuffer getValue() {
      return value;
    }


    /**
      * Sets the value of the 'value' field.
      * @param value The value of 'value'.
      * @return This builder.
      */
    public AvroKotlinLab.Schemas.Amount.Builder setValue(java.nio.ByteBuffer value) {
      validate(fields()[0], value);
      this.value = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'value' field has been set.
      * @return True if the 'value' field has been set, false otherwise.
      */
    public boolean hasValue() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'value' field.
      * @return This builder.
      */
    public AvroKotlinLab.Schemas.Amount.Builder clearValue() {
      value = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Amount build() {
      try {
        Amount record = new Amount();
        record.value = fieldSetFlags()[0] ? this.value : (java.nio.ByteBuffer) defaultValue(fields()[0]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<Amount>
    WRITER$ = (org.apache.avro.io.DatumWriter<Amount>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<Amount>
    READER$ = (org.apache.avro.io.DatumReader<Amount>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

  @Override protected boolean hasCustomCoders() { return true; }

  @Override public void customEncode(org.apache.avro.io.Encoder out)
    throws java.io.IOException
  {
    out.writeBytes(this.value);

  }

  @Override public void customDecode(org.apache.avro.io.ResolvingDecoder in)
    throws java.io.IOException
  {
    org.apache.avro.Schema.Field[] fieldOrder = in.readFieldOrderIfDiff();
    if (fieldOrder == null) {
      this.value = in.readBytes(this.value);

    } else {
      for (int i = 0; i < 1; i++) {
        switch (fieldOrder[i].pos()) {
        case 0:
          this.value = in.readBytes(this.value);
          break;

        default:
          throw new java.io.IOException("Corrupt ResolvingDecoder.");
        }
      }
    }
  }
}










