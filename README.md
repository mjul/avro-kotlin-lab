# Avro Kotlin Lab

Experiments using the Java version of Apache Avro.
Written in Kotlin.


## Code generation with Avro Tools

Extract the schema into an `.avsc`file from the source or from a data file like this:
    
    java -jar .\lib\avro-tools-1.9.2.jar getschema amounts-generic.avro > amounts.avsc

Now, run the generator to generate Java classes for the Avro types in the schema:

    java -jar .\lib\avro-tools-1.9.2.jar compile schema .\amounts.avsc src\generated

