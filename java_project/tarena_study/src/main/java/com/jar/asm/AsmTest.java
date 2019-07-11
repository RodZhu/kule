package com.jar.asm;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.tree.ClassNode;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Stream;

/**
 * Created by rod on 2019/4/16.
 */
public class AsmTest {
    public static void main(String[] args) throws  Exception{
        JarFile jarFile = new JarFile("/Users/rod/bigdata_jar/stormWordCount.jar");

        Enumeration<JarEntry> entries = jarFile.entries();
        while(entries.hasMoreElements()) {
            JarEntry jarEntry = entries.nextElement();
            if (jarEntry.getName().endsWith(".class")) {
                InputStream inputStream = jarFile.getInputStream(jarEntry);
                System.out.println("Class Name: " + jarEntry.getName());
                ClassPrint cp = new ClassPrint();
                ClassReader cr = new ClassReader(inputStream);
                cr.accept(cp, 0);
                inputStream.close();
            }
        }
    }



//    Map<String, ClassNode> loadClasses(File jarFile) throws Exception {
//        Map<String, ClassNode> classes = new HashMap<String, ClassNode>();
//        JarFile jar = new JarFile(jarFile);
//        Stream<JarEntry> str = jar.stream();
//        str.forEach(z -> readJar(jar, z, classes));
//        jar.close();
//        return classes;
//    }
//
//    Map<String, ClassNode> readJar(JarFile jar, JarEntry entry, Map<String, ClassNode> classes) {
//        String name = entry.getName();
//        try (InputStream jis = jar.getInputStream(entry)){
//            if (name.endsWith(".class")) {
//                byte[] bytes = IOUtils.toByteArray(jis);
//                String cafebabe = String.format("%02X%02X%02X%02X", bytes[0], bytes[1], bytes[2], bytes[3]);
//                if (!cafebabe.toLowerCase().equals("cafebabe")) {
//                    // This class doesn't have a valid magic
//                    return classes;
//                }
//                try {
//                    ClassNode cn = getNode(bytes);
//                    classes.put(cn.name, cn);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return classes;
//    }
//
//    ClassNode getNode(byte[] bytes) {
//        ClassReader cr = new ClassReader(bytes);
//        ClassNode cn = new ClassNode();
//        try {
//            cr.accept(cn, ClassReader.EXPAND_FRAMES);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        cr = null;
//        return cn;
//    }
}
