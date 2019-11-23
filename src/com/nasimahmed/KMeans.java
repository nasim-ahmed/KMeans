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
        kMeans.calculate();
        kMeans.plotClusters();
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

    }

    public void plotClusters(){
        for(int i = 0; i < NUM_CLUSTERS; i++){
            Cluster c = clusters.get(i);
            c.plotCluster();
        }
    }


    public void calculate(){
         boolean isFinish = false;
         int iteration = 0;

         while (!isFinish) {
             clearClusters();

             List lastCentroids = getCentroids();

             //Assign points to the closer cluster
             assignClusters();

             //calculate new centroids
             calculateCentroids();

             iteration ++;

             if (iteration == 3){
                 isFinish = true;
             }
         }

    }

    // remove all the points from the cluster
    public void clearClusters(){
        for(Cluster cluster: clusters){
            cluster.clear();
        }
    }

    public List getCentroids(){
        List centroids = new ArrayList(NUM_CLUSTERS);

        for (Cluster cluster: clusters){
            Point pt = cluster.getCentroid();
            Point point = new Point(pt.getX(), pt.getY());
            centroids.add(point);
        }
        return centroids;
    }

    public void assignClusters(){
         double max = Double.MAX_VALUE;
         double min = max;
         int cluster = 0;
         double distance = 0.0;

         for(Point point: points){
             min = max;

             for(int i = 0; i < NUM_CLUSTERS; i++){
                Cluster c = clusters.get(i);
                distance = Point.distance(point, c.getCentroid());

                if (distance < min) {
                    min = distance;
                    cluster = i;
                }
             }

             point.setCluster_number(cluster);
             clusters.get(cluster).addPoint(point);
         }
    }

    public void calculateCentroids(){
        for(Cluster cluster: clusters){
            double sumX = 0.0;
            double sumY = 0.0;

            List<Point> list = cluster.getPoints();
            int number_ofPoints = list.size();

            for(Point pt: list){
                sumX += pt.getX();
                sumY += pt.getY();
            }

            Point centroid = cluster.getCentroid();

            if (number_ofPoints > 0){
                double newX = sumX / number_ofPoints;
                double newY = sumY / number_ofPoints;
                centroid.setX(newX);
                centroid.setY(newY);
            }

        }
    }




}
