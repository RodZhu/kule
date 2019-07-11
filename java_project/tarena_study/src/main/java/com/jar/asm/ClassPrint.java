package com.jar.asm;

import org.objectweb.asm.*;

/**
 * Created by rod on 2019/4/16.
 */
public class ClassPrint implements ClassVisitor {

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {

    }

    @Override
    public void visitSource(String source, String debug) {

    }

    @Override
    public void visitOuterClass(String owner, String name, String desc) {
        System.out.println("[visitOuterClass] desc: " + desc + " |name: " + name + " |owner: " + owner);
    }

    @Override
    public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
        System.out.println("[visitAnnotation] desc: " + desc);
        return null;
    }

    @Override
    public void visitAttribute(Attribute attr) {
        System.out.println("[visitAttribute] attr: " + attr);
    }

    @Override
    public void visitInnerClass(String name, String outerName, String innerName, int access) {
        System.out.println("[visitInnerClass] access: " + access + " | name: " + name + " | access: " + access);
    }

    @Override
    public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {

        System.out.println("[visitField] access: " + access + " | name: " + name + " | signature: " + signature);
        return null;
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        System.out.println("[visitMethod] access: " + access + " | name: " + name + " | signature: " + signature);
        return null;
    }

    @Override
    public void visitEnd() {
        System.out.println("this is visit end.");
    }
}
