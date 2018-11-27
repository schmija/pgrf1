package renderer;

import model3D.Solid;
import transforms.*;
import view.Raster;

import java.awt.*;
import java.util.List;

public class Renderer3D {

    private final Raster raster;
    private Mat4 projection;
    private Mat4 view;
    private Mat4 model;

    public Renderer3D(Raster raster) {
        this.raster = raster;

        model = new Mat4Identity();

        Vec3D eye = new Vec3D(0, -5, 2);
        Vec3D direction = new Vec3D(0, 5, -2);
        Vec3D up = new Vec3D(0, 0, 1);

        view = new Mat4ViewRH(eye, direction, up);

        projection = new Mat4PerspRH(Math.PI / 3, Raster.HEIGHT / (float) Raster.WIDTH, 1, 200);
    }

    public void draw(Solid... solids) {
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

    public void drawLine(Point3D a, Point3D b, Color color) {
        a = a.mul(model).mul(view).mul(projection);
        b = b.mul(model).mul(view).mul(projection);

        Vec3D v1, v2;
        if (a.dehomog().isPresent()){
            v1 = a.dehomog().get();
        }else {
            v1 = new Vec3D(0);
        }
        if (b.dehomog().isPresent()){
            v2 = b.dehomog().get();
        }else {
            v2 = new Vec3D(0);
        }

        v1 = v1.mul(new Vec3D(Raster.WIDTH / 2f, Raster.HEIGHT / 2f, 1));
        v2 = v2.mul(new Vec3D(Raster.WIDTH / 2f, Raster.HEIGHT / 2f, 1));

        raster.drawLine(v1.getX(), v1.getY(), v2.getX(), v2.getY(), color);
    }
}
