package controller;

import model3D.Cube;
import model3D.Solid;
import renderer.Renderer;
import renderer.Renderer3D;
import view.Raster;

public class PgrfController3D {

    private Raster raster;
    private Renderer3D renderer3D;
    private Solid cube;

    public PgrfController3D(Raster raster) {
        this.raster = raster;
        initbjects();
        initListeners();
    }

    private void initbjects(){
        cube = new Cube();
    }

    private void initListeners(){

    }
}
