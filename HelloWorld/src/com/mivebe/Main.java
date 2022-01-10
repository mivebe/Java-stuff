package com.mivebe;

public class Main {

    public static void main(String[] args) {

        System.out.println("Hello World - MiVeBe");

        char a = 'a';  // char must be single quote
        String asd = "ASD";  // string must be double quote

        short kur = 12;  // shorter ints
        long hui = 120000;  // complete ints up to 32 bits
        float decimal = 1.234f;  // up to 7 decimal digits
        double million = 1.234567890123;  // up to 16 decimal digits
        Boolean condition = true;  // true or false

        notSoMain();
        System.out.println(generateName());

        if(randomNumber(1,10) < 5){
            for(int i = 1; i <= 5; i++){
                System.out.println(i);
            };
        }else{
            for(int i = 10; i > 5; i--){
                System.out.println(i);
            };
        }

        Animal.makeSound3();  // just call the class method

        Animal someAnimal = new Animal();

        someAnimal.name="pesho";
        Animal.makeSound(someAnimal); // call Animal class method passing the locally created instance

        someAnimal.name="gosho";
        someAnimal.makeSound2();  // call the instance's local method which uses the instance name internally

        System.out.println(someAnimal);  // console logging non-primitive data will result in the address in memory

        PrintClass.printClass(Main.class);
        System.out.println();
        PrintClass.printClass(Cat.class, false);
        System.out.println();
        TryingEnums.useWeekDays();

        FileRW.writeTest();
        FileRW.read();

        System.out.println();
        MapAndHashMap.HashMap();
        System.out.println();

//        SortingAlgs.test("insertion");
//        SortingAlgs.test("quick");
//        SortingAlgs.test("merge");
//        SortingAlgs.test("bubble");
        SortingAlgs.test("bogus");

        System.out.println();

        Sudoku.solve();
    }

    public static void notSoMain(){
        System.out.println("Not So Main");
    }

    public static String generateName(){
        String name = "Generated Pesho";
        return name;
    }

    public static int randomNumber(int min, int max){
        final int num = (int)Math.floor(Math.random()*(max-min+1)+min);
        System.out.println("Random number is: " + num);
        return num;
    }
    private static void hui(){
        System.out.println("hui");
    }
}
