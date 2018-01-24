package me.sirlich.AsmodeusRpg.utilities;

import me.sirlich.AsmodeusRpg.AsmodeusRpg;

import java.io.*;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class RpgFileReader
{
    private File file;
    private int level;
    public RpgFileReader(String fileName, int level){
        this.file = new File(AsmodeusRpg.getInstance().getDataFolder() + "/monsters/" + fileName);
        this.level = level;
    }

    public double readDouble(String tag){
        double returnThis = 0.0;
        System.out.println("Attempting to read... " + tag);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if(line.length() >= 1 && line.charAt(0) == '[' && line.contains(tag)) {
                    String data = line.split("]")[1];
                    returnThis = Double.parseDouble(data.split(",")[level].trim());
                    System.out.println("Read: " + returnThis);
                    break;
                }
            }
        } catch(FileNotFoundException e) {
            System.out.println("MAJOR ISSUE IN READRANDOMSTRING");
        }
        return returnThis;
    }

    public int readInt(String tag){
        int returnThis = 0;
        System.out.println("Attempting to read... " + tag);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if(line.length() >= 1 && line.charAt(0) == '[' && line.contains(tag)) {
                    String data = line.split("]")[1];
                    returnThis = Integer.parseInt(data.split(",")[level].trim());
                    System.out.println("Read: " + returnThis);
                    break;
                }
            }
        } catch(FileNotFoundException e) {
            System.out.println("MAJOR ISSUE IN READRANDOMSTRING");
        }
        return returnThis;
    }


    public String readRandomString(String tag){
        String returnThis = "NULL! Issue with: +" + tag;
        System.out.println("Attempting to read... " + tag);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.println("Is it: " + line + "?");
                if(line.length() >= 1 && line.charAt(0) == '[' && line.contains(tag)) {
                    System.out.println("YES! It is!");
                    String data = line.split("]")[1];
                    String[] strings = data.split(",");
                    returnThis = strings[ThreadLocalRandom.current().nextInt(0, strings.length)];
                    System.out.println("Read: " + returnThis);
                    break;
                }
            }
        } catch(FileNotFoundException e) {
            System.out.println("MAJOR ISSUE IN READRANDOMSTRING");
        }
        return returnThis;
    }

    public String readIndexedString(String tag){
        String returnThis = "NULL! Issue with: +" + tag;
        System.out.println("Attempting to read... " + tag);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if(line.length() >= 1 && line.charAt(0) == '[' && line.contains(tag)) {
                    String data = line.split("]")[1];
                    returnThis = data.split(",")[level];
                    System.out.println("Read: " + returnThis);
                    break;
                }
            }
        } catch(FileNotFoundException e) {
            System.out.println("MAJOR ISSUE IN READRANDOMSTRING");
        }
        return returnThis;
    }
}
