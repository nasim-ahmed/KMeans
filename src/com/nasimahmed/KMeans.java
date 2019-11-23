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
    }

    public KMeans(){
        this.points = new ArrayList<Point>();
        this.clusters = new ArrayList();
    }


    //initialize the process
    public void init(){
        //read from text file
        points = InputFileReader.readFile("data.txt");
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

             List<Point> lastCentroids = getCentroids();

             //Assign points to the closer cluster
             assignClusters();

             //calculate new centroids
             calculateCentroids();

             iteration ++;

             double distance = terminateClustering(lastCentroids, iteration);



             if (distance == 0){
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

    // terminate clustering process
    public double terminateClustering(List<Point> oldCentroids, int iterations){

        List<Point> currentCentroids = getCentroids();

        double distance = 0.0;

        for(int i = 0; i < oldCentroids.size(); i++){
           distance += Point.distance(currentCentroids.get(i), oldCentroids.get(i));
        }

        System.out.println("#################");
        System.out.println("Iteration: " + iterations);
        plotClusters();

        return distance;
    }




}
