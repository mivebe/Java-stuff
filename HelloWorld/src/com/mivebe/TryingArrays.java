package com.mivebe;

import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.Arrays;

public class TryingArrays {

     public static void tryArrays() {
        String[] someArray = new String[4];
        String[] someArray2 = {"John", "Chris", "Eric", "Luke"};

        ArrayList<String> someArrayList = new ArrayList<>();
        ArrayList<String> someArrayList2 = new ArrayList<>(Arrays.asList("John2", "Chris2", "Eric2", "Luke2"));

        // arraylist cannot hold values of primitive type it accepts only objects
        // primitive values tho  are an object wrapper  so instead of int Integer instead of boolean B etc

        // ArrayList<int> someArrayList = new ArrayList<>();   //this will throw error
        ArrayList<Integer> someArrayList3 = new ArrayList<>();  //but this wont

        // accessing values in array and arraylist
        System.out.println(someArray2[1]);
        System.out.println(someArrayList2.get(1));

        // accessing size
         System.out.println(someArray2.length);
         System.out.println(someArrayList2.size());

         // adding elements
         // cannot do it for arrays
         System.out.println(someArrayList2.add("Gosho"));

         // setting the value of an element
         someArray2[2] = "Pesho";
         someArrayList2.set(2,"Pesho");

         // removing elements
         // cannot do it with arrays
         someArrayList2.remove(3);
         // or
         someArrayList2.remove("Chris2");

         // printing
         System.out.println(someArray2);  // haha slap that memory address
         System.out.println(someArrayList2);
    }
}
