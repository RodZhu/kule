package com.jar;

import sun.net.www.protocol.jar.JarURLConnection;

import java.net.URL;
import java.util.jar.Attributes;

/**
 * Created by rod on 2019/4/16.
 */
public class JarMainClassTest {

    public static void main(String[] args) throws Exception{
        JarURLConnection jrc = new JarURLConnection(new URL("/dd "), null);
        Attributes mainAttributes = jrc.getMainAttributes();
        mainAttributes.getValue(Attributes.Name.MAIN_CLASS);

    }
}
