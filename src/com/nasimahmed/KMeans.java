package com.nasimahmed;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class KMeans {
    private int NUM_CLUSTERS = 3;
    private List<Point> points;
    private List<Cluster> clusters;


    public static void main(String[] args) {
        KMeans kMeans = new KMeans();
        kMeans.init();
    }

    public KMeans(){
        this.points = new ArrayList<Point>();
        this.clusters = new ArrayList();
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

    //to print all points from text file
    public void printAllPoints(){
        System.out.println(points.toString());
    }

    //initialize the process
    public void init(){
        //read from text file
        readFile("data.txt");
        //printAllPoints();
        for(int i=0; i < NUM_CLUSTERS; i++){
           Cluster cluster = new Cluster(i);
           Point centroid = Point.createRandomPoint(KMeansConstants.MIN_COORDINATE, KMeansConstants.MAX_COORDINATE);
           cluster.setCentroid(centroid);
           clusters.add(cluster);
        }
       plotClusters();
    }

    public void plotClusters(){
        for(int i = 0; i < NUM_CLUSTERS; i++){
            Cluster c = clusters.get(i);
            c.plotCluster();
        }
    }


}
