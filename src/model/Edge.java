package model;

public class Edge {

    private int x1, y1, x2, y2;

    public Edge(Point p1, Point p2){
        x1 = p1.x;
        y1 = p1.y;
        x2 = p2.x;
        y2 = p2.y;
    }

    public boolean isHorizontal(){

        return false;
    }

    public void orientate(){

    }

    public boolean intersectionExist(int y){

        return false;
    }

    public int getIntersection(int y){
        //TODO vypočitat průsečík pomocí y, k, q
        return 0;
    }
}
