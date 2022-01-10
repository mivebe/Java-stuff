package com.mivebe;

import java.lang.reflect.*;

public class PrintClass {

    public static void printClass(Class c){
        printClass(c, false);
    }

    public static void printClass(Class c, boolean full){
        System.out.println("Class name: " + c.getSimpleName());
        Method[] methods = full ? c.getMethods() : c.getDeclaredMethods();
        if(methods.length > 0){
            System.out.println();
            System.out.println("Methods:");
            for(int i = 0; i < methods.length; i++){
                System.out.println("-- " + methods[i]);
            }
        }

        Field[] fields = full ? c.getFields() : c.getDeclaredFields();
        if(fields.length > 0){
            System.out.println();
            System.out.println("Fields:");
            for(int i = 0; i < fields.length; i++){
                System.out.println("-- " + fields[i]);
            }
        }
    }
}