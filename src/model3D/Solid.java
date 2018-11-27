package model3D;

import transforms.Point3D;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Solid {

    final List<Point3D> vertices = new ArrayList<>();
    final List<Integer> indices = new ArrayList<>();
    Color color;

    public List<Point3D> getVertices() {
        return vertices;
    }

    public List<Integer> getIndices() {
        return indices;
    }

    public Color getColor() {
        return color;
    }
}
