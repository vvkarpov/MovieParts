package ru.vkarpov.dev;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.TreeSet;

/*
LEARNING TASK - FILE COLLECT.
[someName].partN  - Lion.avi.part1 ... Lion.avi.part37
*/

public class Solution {
    public static void main(String[] args) throws IOException, InterruptedException {

        String fileName;
        String movieName = null;
        ArrayList<String> arrayPartsName = new ArrayList<>();
        TreeSet<Integer> arraySortPartsName = new TreeSet<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
            while (!(fileName = reader.readLine()).equals("end")){
                arrayPartsName.add(fileName); // add all parts
            }
        }

        for (String part : arrayPartsName){
            String[] split = part.split(".part");
            movieName = split[0];
            int partNumber = Integer.parseInt(split[1]);
            System.out.println("Searching part number..." + "find: " + partNumber);
            Thread.sleep(1000);
            arraySortPartsName.add(partNumber);//sorted parts
        }

        Path newPath = Path.of(movieName);
        try {
            if (!Files.exists(newPath)){
                Files.createFile(newPath);
                System.out.println("Movie create success: " + movieName);
            }
        }
        catch (Exception exp){
            exp.printStackTrace();
        }

        try (FileWriter fileWriter = new FileWriter(movieName);
             BufferedWriter writer = new BufferedWriter(fileWriter)){

            for (Integer i : arraySortPartsName){
                try (InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(movieName + ".part" + i));
                     BufferedReader reader = new BufferedReader(inputStreamReader)){
                    while (reader.ready()){
                        writer.write(reader.readLine());
                    }
                }
            }
        }
        System.out.println("All movie parts write success!");
    }
}