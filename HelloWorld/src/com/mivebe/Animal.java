package com.mivebe;

public class Animal {
public String name;

    public static void makeSound(Animal a) {
        System.out.println(a.name);
    }  // takes a class object of type Animal and prints its name

    public void makeSound2() {  // prints the name from the Animal class
        System.out.println(name);
    }
    public static void makeSound3() {  //just prints ASD even without name
        System.out.println("Hardcoded sound");
    }


    private static void privateForAnimal(){
        System.out.println("privateAnimal");
    }
}
