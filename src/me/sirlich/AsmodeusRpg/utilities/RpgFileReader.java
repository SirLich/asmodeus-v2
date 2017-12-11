package me.sirlich.AsmodeusRpg.utilities;

import me.sirlich.AsmodeusRpg.mobs.entityHandling.RpgEntityType;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class RpgFileReader
{
    BufferedReader reader;
    public RpgFileReader(BufferedReader reader){
        this.reader = reader;
    }

    public static String trimContent(String line){
        System.out.println("Before trim: " + line);
        String result[] = line.split(":");
        line = result[1]; //Ignore the LHS stuff which is just a tag yah know?
        line = line.trim(); //Clear whitespace
        System.out.println("After trim: " + line);
        return line;
    }


    public String readSting(){
        String returnThis = "";
        try {
            returnThis = RpgFileReader.trimContent(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnThis;
    }

    public RpgEntityType readRpgMonster(){
        RpgEntityType returnThis = RpgEntityType.ERROR;
        try {
            returnThis = RpgEntityType.valueOf(RpgFileReader.trimContent(reader.readLine()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnThis;
    }

    public ArrayList<Integer> readIntList(){
        ArrayList<Integer> returnThis = new ArrayList<Integer>();
        try {
            String levels[] = RpgFileReader.trimContent(reader.readLine()).split(" ");
            for(String element : levels){
                returnThis.add(Integer.parseInt(element));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnThis;
    }

    public ArrayList<Double> readDoubleList(){
        ArrayList<Double> returnThis = new ArrayList<Double>();
        try {
            String levels[] = RpgFileReader.trimContent(reader.readLine()).split(" ");
            for(String element : levels){
                returnThis.add(Double.parseDouble(element));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnThis;
    }

    public Double readSpecificDouble(int index){
        return readDoubleList().get(index);
    }

    public int readSpecificInt(int index){
        return readIntList().get(index);
    }

    public String readRandomStringFromList(){
        String returnThis = "";
        try {
            String levels[] = RpgFileReader.trimContent(reader.readLine()).split(",");
            returnThis = levels[ThreadLocalRandom.current().nextInt(0, levels.length + 1)].trim(); //Return trimmed random element
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnThis;
    }

    public Integer readInt(){
        int returnThis = 0;
        try {
            returnThis = Integer.parseInt(RpgFileReader.trimContent(reader.readLine()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnThis;
    }
}
