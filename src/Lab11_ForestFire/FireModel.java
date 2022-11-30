package Lab11_ForestFire;

import java.io.File;

class FireModel {
    static int SIZE = 47;
    private FireCell[][] myGrid;
    private FireView myView;
    private boolean onett;

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
            myView.updateView(myGrid);
            if(r == 0) {
                System.out.println("Onett is in trouble!");
                onett = true;
                return;
            }

            try{
                Thread.sleep( 7);
            }
            catch (InterruptedException ex) { }

            simulate(r - 1, c);
            simulate(r + 1, c);
            simulate(r, c - 1);
            simulate(r, c + 1);
        }
    }

    boolean solve() {
        for(int i = 0; i < myGrid.length; i++) {
            simulate(myGrid.length - 1, i);
        }

        if(!onett) System.out.println("Onett is safe.");

        return onett;
    }
}
