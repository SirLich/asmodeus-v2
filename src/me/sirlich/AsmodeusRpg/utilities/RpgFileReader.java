package me.sirlich.AsmodeusRpg.utilities;

import java.io.BufferedReader;
import java.io.IOException;
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
                //Is it our line?
                if(line.contains(tag)){
                    //Take the second half
                    String doubleList = line.split("]")[1];
                    //Generate the numbers
                    double[] doubles = Stream.of(doubleList.split(",")).mapToDouble (Double::parseDouble).toArray();
                    returnThis = doubles[level];
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnThis;
    }
}
