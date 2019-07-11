package com.jar.asm.example;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;

import static org.objectweb.asm.Opcodes.ASM4;

/**
 * Created by rod on 2019/5/9.
 */
public class AddFieldAdapter extends ClassVisitor{

    private String fieldName;
    private String fieldDefault;
    private int access = org.objectweb.asm.Opcodes.ACC_PUBLIC;
    private boolean isFieldPresent;

    public AddFieldAdapter(
            String fieldName, int fieldAccess, ClassVisitor cv) {
        super(ASM4, cv);
        this.cv = cv;
        this.fieldName = fieldName;
        this.access = fieldAccess;
    }

    @Override
    public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
        if (name.equals(fieldName)) {
            isFieldPresent = true;
        }
        return cv.visitField(access, name, desc, signature, value);
    }

    @Override
    public void visitEnd() {
        if (!isFieldPresent) {
            FieldVisitor fv = cv.visitField(
                    access, fieldName, "", null, null);
            if (fv != null) {
                fv.visitEnd();
            }
        }
        cv.visitEnd();
    }
}
