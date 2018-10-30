package fill;

import model.Edge;
import model.Point;
import renderer.Renderer;
import view.Raster;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ScanLine implements Filler{

    private Renderer renderer;
    private List<Point> points;
    private int fillColor, edgeColor;

    @Override
    public void setRaster(Raster raster) {

    }

    @Override
    public void fill() {
        scanline();
    }

    public void init(List<Point> points, int fillColor, int edgeColor){
        this.points = points;
        this.fillColor = fillColor;
        this.edgeColor = edgeColor;
    }

    private void scanline() {
        List<Edge> edges = new ArrayList<>();
        /*vytvořit z bodů úsečky
        0. a 1. první hrana, 1. a 2. druhá hrana atd.
        ignorovat vodorovné hrany
         */

        int minY = points.get(0).y;
        int maxY = minY;

        for (int y = 0; y < maxY; y++) {
            List<Integer> intersections = new ArrayList<>();

            Collections.sort(intersections);
        }

        renderer.drawPolygon(points, edgeColor);
    }
}
