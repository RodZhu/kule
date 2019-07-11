package com.jar.asm;

import com.sun.deploy.util.JarUtil;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

import java.io.*;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarInputStream;

/**
 * Created by rod on 2019/4/16.
 */
public class AsmTest {
    public static void main(String[] args) throws Exception {
        //JarFile jarFile = new JarFile("/Users/rod/bigdata_jar/stormWordCount.jar");
         FileInputStream fileInputStream = new FileInputStream(new File("/Users/rod/bigdata_jar/test-flink.jar"));
//        hbase-protocol-1.2.0.jar

        // getTest(new FileInputStream(new File("/Users/rod/bigdata_jar/stormWordCount.jar")));

//        test3(fileInputStream);

        System.out.println("s".substring(0,0));

    }

    public static void getTest(InputStream in) throws Exception {
        JarInputStream jarInputStream = new JarInputStream(in);
        JarEntry nextJarEntry = null;
        ClassPrint cp = new ClassPrint();
        while ((nextJarEntry = jarInputStream.getNextJarEntry()) != null) {
            String className = nextJarEntry.getName();
            //&& className.indexOf("WordCountCLusterTopology") > -1
            if (className.endsWith(".class")) {
                try {
                    long size = nextJarEntry.getSize();
//                    InputStream classIn = new ByteArrayInputStream(extra);
//                    ClassReader cr = new ClassReader(classIn);
//                    cr.accept(cp, 0);
//                    classIn.close();
                    System.out.println(className + ": " + size);
                } catch (IndexOutOfBoundsException e) {
                    /**
                     * 由于平台的ASM版本较低不兼容箭头函数，针对此class解析问题出现IndexOutOfBoundsException
                     */

                } catch (Exception e) {

                }
            }
        }
    }

    public static void getTest2(InputStream in) throws Exception {
        JarFile jarFile = new JarFile("/Users/rod/bigdata_jar/stormWordCount.jar");

        Enumeration<JarEntry> entries = jarFile.entries();
        ClassPrint cp = new ClassPrint();
        while (entries.hasMoreElements()) {
            JarEntry jarEntry = entries.nextElement();
            String className = jarEntry.getName();
            //&& className.indexOf("WordCountCLusterTopology") > -1
            if (className.endsWith(".class")) {
                InputStream inputStream = jarFile.getInputStream(jarEntry);
                System.out.println("Class Name: " + className.substring(0, className.lastIndexOf(".")).replaceAll("/", "."));
//                ClassPrint cp = new ClassPrint(Opcodes.ASM7);
                ClassReader cr = new ClassReader(inputStream);
                cr.accept(cp, 0);
                inputStream.close();
            }
        }
    }

    public static void test3(InputStream input) {
        try {
            JarInputStream in = new JarInputStream(input);
            JarEntry entry = null;
            while ((entry = in.getNextJarEntry()) != null) {
                ClassNode node = new ClassNode();
                String name = entry.getName();
                if (!name.endsWith(".class")) continue;
                name = name.replaceAll(".class$", "").replaceAll("\\/",".");
                try {
                    ClassReader reader = new ClassReader(in);
                    reader.accept(node, ClassReader.SKIP_CODE);
                    List<MethodNode> methods = node.methods;
                    for (MethodNode mn : methods) {
                        if ("main".equals(mn.name))
                            System.out.println(name + ":" + mn.name + " " + mn.signature);
                    }
                }catch (ArrayIndexOutOfBoundsException e) {

                }
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
