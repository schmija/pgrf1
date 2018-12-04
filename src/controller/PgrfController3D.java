package controller;

import model3D.Cube;
import model3D.Solid;
import renderer.Renderer3D;
import view.Raster;

public class PgrfController3D {

    private Raster raster;
    private Renderer3D renderer3D;
    private Solid cube;

    public PgrfController3D(Raster raster) {
        this.raster = raster;
        initObjects();
        initListeners();

    }

    private void initObjects() {
        cube = new Cube();
        renderer3D = new Renderer3D(raster);
        renderer3D.draw(cube);
    }

    private void initListeners() {

    }
}
