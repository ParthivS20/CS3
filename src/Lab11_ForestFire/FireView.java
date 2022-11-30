package Lab11_ForestFire;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;

/*
  this is the View component
*/

class FireView extends JPanel {
    private FireCell[][] myGrid;

    // entry point from model, requests grid be redisplayed
    void updateView(FireCell[][] mg) {
        myGrid = mg;
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int testWidth = getWidth() / (FireModel.SIZE+1);
        int testHeight = getHeight() / (FireModel.SIZE+1);
        // keep each Fire cell square
        int boxSize = Math.min(testHeight, testWidth);

        for( int r = 0; r < FireModel.SIZE; r++ ) {
            for(int c = 0; c < FireModel.SIZE; c++ ) {
                if(myGrid[r][c] != null) {
                    int ulX = (c+1) * boxSize;
                    int ulY = (r+1) * boxSize;

                    g.setColor(myGrid[r][c].color);

                    int topLeftX = ulX+2, topLeftY = ulY+2;
                    int sizeX = boxSize-2, sizeY = boxSize-2;
                    g.fillRect( topLeftX, topLeftY, sizeX, sizeY);
                }
            }
        }
    }
}
