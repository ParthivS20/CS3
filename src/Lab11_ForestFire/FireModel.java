package Lab11_ForestFire;

import javax.swing.*;
import java.awt.*;
import java.io.File;

class FireModel {
    static int SIZE = 47;
    private FireCell[][] myGrid;
    private FireView myView;
    private boolean onett;
    private FireCell finalCell;
    private final int delay = 15;

    FireModel(FireView view) {
        myGrid = new FireCell[SIZE][SIZE];
        int setNum = 0;
        for (int r=0; r<SIZE; r++)
        {
            for (int c=0; c<SIZE; c++)
            {
                myGrid[r][c] = new FireCell();
            }
        }
        myView = view;
        myView.updateView(myGrid);

        onett = false;
    }

    private void simulate(int r, int c) {
        if(r < 0 || r >= myGrid.length || c < 0 || c >= myGrid.length || onett) return;

        if(myGrid[r][c].light()) {
            finalCell = myGrid[r][c];
            updateView();
            if(r == 0) {
                onett = true;
                updateView();
                return;
            }

            try{
                Thread.sleep(delay);
            }
            catch (InterruptedException ex) { }

            simulate(r - 1, c);
            simulate(r + 1, c);
            simulate(r, c - 1);
            simulate(r, c + 1);
        }
    }

    void solve() {
        for(int i = 0; i < myGrid.length; i++) {
            simulate(myGrid.length - 1, i);
        }

        while(!finalCell.color.equals(new Color(240, 40, 40))) {
            updateView();
            try{
                Thread.sleep(delay);
            }
            catch (InterruptedException ex) { }
        }

        if(onett){
            System.out.println("Onett is in trouble!");
            myView.add(new JLabel("Onett is in trouble!"), BorderLayout.SOUTH);

            while(true) {
                finalCell.blink();
                myView.updateView(myGrid);
                try{
                    Thread.sleep( 500);
                }
                catch (InterruptedException ex) { }
            }
        }
        else {
            System.out.println("Onett is safe.");
            myView.add(new JLabel("Onett is safe."), BorderLayout.SOUTH);
        }
    }

    void updateView() {
        for(FireCell[] a : myGrid) {
            for(FireCell c : a) {
                c.burn();
            }
        }
        myView.updateView(myGrid);
    }
}
