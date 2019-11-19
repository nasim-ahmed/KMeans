package com.nasimahmed;

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

}
