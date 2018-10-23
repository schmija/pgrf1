package renderer;

import view.Raster;

public class Renderer {

    private Raster raster;

    public Renderer(Raster raster) {
        this.raster = raster;
    }

    public void drawLine(int x1, int y1, int x2, int y2, int color){
        int dx = x2-x1;
        int dy = y2-y1;

        float k = dy/(float)dx;

        if (dx == 0){
            k = dy;
        }

        float q = y1 - k*x1;

        if (Math.abs(k)<=1){

                if(dx < 0){
                 int x3 = x1;
                 x1 = x2;
                 x2 = x3;
                }

                for (int x = x1; x <= x2; x++) {
                    float y = k * x + q;
                    int ry = Math.round(y);
                    raster.drawPixel(x, ry, color);
                }
        }else{
                if(dy < 0){
                    int y3 = y1;
                    y1 = y2;
                    y2 = y3;
                }

            for (int y = y1; y <= y2; y++) {
                float x = (y-q)/k;
                int rx = Math.round(x);
                raster.drawPixel(rx, y, color);
            }
        }
    }
    public void drawDda(int x1, int y1, int x2, int y2, int color){
        int dx = x2 - x1;
        int dy = y2 - y1;

        float x, y, k, g, h;

        k = dy/(float)dx;

        if (Math.abs(k) < 1){
            g = 1;
            h = k;
        }else {
            g = 1/k;
            h = 1;
        }

        x = x1;
        y = y1;

        for (int i = 0; i <= Math.max(Math.abs(dx),Math.abs(dy)); i++){
            raster.drawPixel(Math.round(x),Math.round(y),color);
            x = x + g;
            y = y + h;
        }
    }
}
