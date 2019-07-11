package com.jar.asm.example;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;

/**
 * Created by rod on 2019/5/9.
 */
public class CustomClassWriter {

    static String className = "java.lang.Integer";
    static String cloneableInterface = "java/lang/Cloneable";
    ClassReader reader;
    ClassWriter writer;

    AddFieldAdapter addFieldAdapter;

    PublicizeMethodAdapter pubMethAdapter;

    public CustomClassWriter() throws Exception {
        reader = new ClassReader(className);
        writer = new ClassWriter(reader, 0);
    }

    public byte[] addField() {
        addFieldAdapter = new AddFieldAdapter(
                "aNewBooleanField",
                org.objectweb.asm.Opcodes.ACC_PUBLIC,
                writer);
        reader.accept(addFieldAdapter, 0);
        return writer.toByteArray();
    }

//    public byte[] publicizeMethod() {
//        pubMethAdapter = new PublicizeMethodAdapter(writer);
//        reader.accept(pubMethAdapter, 0);
//        return writer.toByteArray();
//    }
}
