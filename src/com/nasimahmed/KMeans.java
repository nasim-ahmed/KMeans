package com.nasimahmed;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class KMeans {
    private ArrayList<Point> points = new ArrayList<Point>();

    public static void main(String[] args) {
          KMeans kMeans = new KMeans();
          kMeans.readFile("data.txt");
          kMeans.printAllPoints();
    }

    public void readFile(String textFile){
        try{
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
    }

    public void addPoints(String lineToParse){
       String[] line = lineToParse.split(" ");
       Point nextPoint = new Point(Double.valueOf(line[0]), Double.valueOf(line[1]) );
       points.add(nextPoint);
    }

    public void printAllPoints(){

        System.out.println(points.toString());

    }


}
