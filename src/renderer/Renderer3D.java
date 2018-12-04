package renderer;

import model3D.Solid;
import view.Raster;
import transforms.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Renderer3D {

    private final Raster raster;
    private Mat4 projection;
    private Mat4 view;
    private Mat4 model;
    private List<Solid> solids = new ArrayList<>();

    public Renderer3D(Raster raster) {
        this.raster = raster;

        model = new Mat4Identity();

        Vec3D e = new Vec3D(0, -5, 2);
        Vec3D d = new Vec3D(0, 5, -2);
        Vec3D u = new Vec3D(0, 0, 1);
        view = new Mat4ViewRH(e, d, u);

        projection = new Mat4PerspRH(Math.PI / 4, Raster.HEIGHT / (float) Raster.WIDTH, 1, 200);
    }

    public void add(Solid... solids){
        this.solids.addAll(Arrays.asList(solids));
        repaint();
    }

    public void repaint() {
        raster.clear();
        for (Solid solid : solids) {
            List<Point3D> vertices = solid.getVertices();
            List<Integer> indices = solid.getIndices();
            for (int i = 0; i < indices.size(); i += 2) {
                int index1 = indices.get(i);
                int index2 = indices.get(i + 1);
                Point3D a = vertices.get(index1);
                Point3D b = vertices.get(index2);
                drawLine(a, b, solid.getColor());
            }
        }
    }

    private void drawLine(Point3D a, Point3D b, Color color) {
        a = a.mul(model).mul(view).mul(projection);
        b = b.mul(model).mul(view).mul(projection);
/*
Homogeneous Coordinates
https://youtu.be/BwJ8sLYcPzc

https://prateekvjoshi.com/2014/06/13/the-concept-of-homogeneous-coordinates/

Perspective Projection
https://youtu.be/veLvYQpxe6Y

Homogeneous Coordinates
https://youtu.be/GGG3cL6vfSc
*/
        Vec3D v1, v2;
        if (a.dehomog().isPresent()) {
            v1 = a.dehomog().get();
        } else {
            v1 = new Vec3D(0);
        }
        if (b.dehomog().isPresent()) {
            v2 = b.dehomog().get();
        } else {
            v2 = new Vec3D(0);
        }

        //ořezání
        if(Math.abs(v1.getX()) > 1 || Math.abs(v2.getX()) > 1) return;
        if(Math.abs(v1.getY()) > 1 || Math.abs(v2.getY()) > 1) return;
        if(v1.getZ() > 1 || v1.getZ() < 0 || v2.getZ() > 1 || v2.getZ() < 0) return;


        v1 = transformToWindow(v1);
        v2 = transformToWindow(v2);

        // TODO transformace do okna

        raster.drawLine(
                v1.getX(), v1.getY(),
                v2.getX(), v2.getY(),
                color
        );

    }

    private Vec3D transformToWindow(Vec3D v){
        return v.mul(new Vec3D(1,-1,1))
                .add(new Vec3D(1,1,0))
                .mul(new Vec3D(Raster.WIDTH / 2f, Raster.HEIGHT / 2f, 1));
    }

    public Mat4 getProjection() {
        return projection;
    }

    public void setProjection(Mat4 projection) {
        this.projection = projection;
    }

    public Mat4 getView() {
        return view;
    }

    public void setView(Mat4 view) {
        this.view = view;
        repaint();
    }

    public Mat4 getModel() {
        return model;
    }

    public void setModel(Mat4 model) {
        this.model = model;
        repaint();
    }
}
