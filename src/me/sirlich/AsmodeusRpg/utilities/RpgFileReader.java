package me.sirlich.AsmodeusRpg.utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

public class RpgFileReader
{
    BufferedReader reader;
    public RpgFileReader(BufferedReader reader){
        this.reader = reader;
    }

    public double readDouble(String tag, int level){
        double returnThis = 0.0;
        if(level >= 10){
            //Early cancelation because the level is wacked out.
            return returnThis;
        }
        try {
            String line = reader.readLine();
            while (line != null)
            {
                if(line.charAt(0) == '['){
                    //Is it our line?
                    if(line.contains(tag)){
                        //Take the second half
                        String doubleList = line.split("]")[1];
                        //Generate the numbers
                        double[] doubles = Stream.of(doubleList.split(",")).mapToDouble (Double::parseDouble).toArray();
                        returnThis = doubles[level];
                    }
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnThis;
    }

    public int readInt(String tag, int level){
        int returnThis = 0;
        if(level >= 10){
            //Early cancelation because the level is wacked out.
            return returnThis;
        }
        try {
            String line = reader.readLine();
            while (line != null)
            {
                if(line.charAt(0) == '['){
                    //Is it our line?
                    if(line.contains(tag)){
                        //Take the second half
                        String intList = line.split("]")[1];
                        //Generate the numbers
                        int[] ints = Stream.of(intList.split(",")).mapToInt (Integer::parseInt).toArray();
                        returnThis = ints[level];
                    }
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnThis;
    }

    public String readString(String tag, int level){
        String returnThis = "NULL PLEASE FIX";
        if(level >= 10){
            //Early cancelation because the level is wacked out.
            return returnThis;
        }
        try {
            String line = reader.readLine();
            while (line != null)
            {
                if(line.charAt(0) == '['){
                    //Is it our line?
                    if(line.contains(tag)){
                        //Take the second half
                        String stringList = line.split("]")[1];
                        String[] strings = stringList.split(",");
                        returnThis = strings[level].trim();
                    }
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnThis;
    }

    public String readString(String tag){
        System.out.println("Reading String");
        String returnThis = "NULL PLEASE FIX";
        System.out.println("Attempting to find " + tag);
        try {
            String line = reader.readLine();
            while (line != null)
            {
                System.out.println(line);
                if(line.length() >= 1 && line.charAt(0) == '['){
                    //Is it our line?
                    if(line.contains(tag)){
                        System.out.println("FOUND DE TAG DAD");
                        //Take the second half
                        String stringList = line.split("]")[1];
                        System.out.println(stringList);
                        String[] strings = stringList.split(",");
                        System.out.println(strings.toString());
                        //Generate random one instead.
                        int level = ThreadLocalRandom.current().nextInt(0, strings.length);
                        System.out.println("level: " + level);
                        returnThis = strings[level].trim();
                        System.out.println(returnThis);
                        break;
                    }
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("PAY ATTENTION HOME: " + returnThis);
        return returnThis;
    }
}
