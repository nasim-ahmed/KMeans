package com.nasimahmed;

import java.util.Random;

public class Point {
    private double x;
    private double y;
    private int cluster_number = 0;

    //Constructor
    public Point(double x, double y){
         this.setX(x);
         this.setY(y);
    }

    public void setX(double x){
        this.x = x;
    }
    public void setY(double y){
        this.y = y;
    }

    public double getX(){
        return this.x;
    }

    public double getY(){
        return this.y;
    }

    public void setCluster_number(int n){
        this.cluster_number = n;
    }

    public int getCluster_number(){
        return this.cluster_number;
    }

    public String toString(){
        return "("+x+","+y+")";
    }

    //create random point
    protected static Point createRandomPoint(int min, int max){
        Random r = new Random();
        double x = min + (max - min) * r.nextDouble();
        double y = min + (max - min) * r.nextDouble();
        return new Point(x, y);
    }


    // Calculates the distance between two points.
     protected static double distance(Point p, Point centroid){
        return Math.sqrt(Math.pow((centroid.getY() - p.getY()), 2) + Math.pow((centroid.getX() - p.getX()), 2));
     }


}
