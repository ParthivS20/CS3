package Lab11_ForestFire;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/*
    this is the Controller component
*/

class Fire extends JFrame {
    private FireView view;
    private FireModel model;

    private Fire() {
        super("Forest Fire");

        // build the view
        view = new FireView();
        view.setBackground(Color.white);
        Container c = getContentPane();
        c.add(view, BorderLayout.CENTER);

        // build the model
        model = new FireModel(view);
    }

    void solve() {
        model.solve();
    }

    public static void main(String[] args) {
        Fire smokey = new Fire();
        smokey.addWindowListener(
                new WindowAdapter() {
                     public void windowClosing(WindowEvent e) {
                         System.exit(0);
                     }
                });
        smokey.setSize(570, 640);

        //smokey.show();
        smokey.setVisible(true);

        smokey.solve();
    }
}
