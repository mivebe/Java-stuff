package com.mivebe;

public class Dog {

    String name;
    int age;

    // this no-args constructor is included by default when no constructors are specified,
    //  but needs to be specified in order to be kept alongside other constructors
    public Dog(){

    }

    public Dog(String name){
        this.name = name;
    }

    public Dog(int age){
        this.age = age;
    }

    public Dog(String name, int age){
        this.name = name;
        this.age = age;
    }

    public void stateNameAndAge(){
        System.out.printf(name, age);
    }
}
