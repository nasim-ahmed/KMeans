package com.nasimahmed;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/* this file reads from the input file and parse each line to add the points in a list */

public class InputFileReader {

    private static List<Point> points;

    public static List<Point> readFile(String textFile){
        try{
            points = new ArrayList<Point>();
            File file = new File(textFile);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = null;
            while ( (line = reader.readLine()) != null){
                addPoints(line);
            }
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return points;
    }

    public static void addPoints(String lineToParse){
        String[] line = lineToParse.split(" ");
        Point nextPoint = new Point(Double.valueOf(line[0]), Double.valueOf(line[1]) );
        points.add(nextPoint);
    }

    //to print all points from text file
    public void printAllPoints(){
        System.out.println(points.toString());
    }
}
