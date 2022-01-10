package com.mivebe;

import java.io.*;

public class FileRW {

    public static void writeTest() {
        final String[] names = {"Gosho", "Pesho", "Tosho"};

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("testOutput.txt"));
            writer.write("This hardcoded stuff will be written to a file.");
            writer.write("\n");
            writer.write("This also.");
            for(String name : names){
                writer.write("\n" + name);
            }
            writer.write("End of spam.");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void write() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void read(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader("input.txt"));

            String line;
            while((line = reader.readLine()) != null){
                System.out.println(line);
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
