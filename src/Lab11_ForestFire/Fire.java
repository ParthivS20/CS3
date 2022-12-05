package Lab11_ForestFire;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/*
    this is the Controller component
*/

class Fire extends JFrame implements ActionListener{
    private FireView view;
    private FireModel model;

    private JLabel msg;
    private JButton playButton;
    private JButton pauseButton;
    private JButton resetButton;
    private Thread solver;

    private Fire() {
        super("Forest Fire");

        view = new FireView();
        view.setBackground(Color.white);

        msg = new JLabel("Ready");
        msg.setFont(new Font("", Font.PLAIN, 18));

        JPanel controlPanel = new JPanel(new GridLayout(1,2,150,0));
        JPanel buttons = new JPanel(new GridLayout(1,3,5,0));

        playButton = new JButton("▶");
        playButton.setBackground(new Color(252, 127, 3));
        playButton.setMargin(new Insets(0,0,0,0));
        playButton.setFocusPainted(false);
        playButton.addActionListener(this);

        pauseButton = new JButton("⏸");
        pauseButton.setBackground(new Color(252, 127, 3));
        pauseButton.setMargin(new Insets(0,0,0,0));
        pauseButton.setFocusPainted(false);
        pauseButton.addActionListener(this);

        resetButton = new JButton("↺");
        resetButton.setBackground(new Color(252, 127, 3));
        resetButton.setMargin(new Insets(0,0,0,0));
        resetButton.setFocusPainted(false);
        resetButton.addActionListener(this);

        buttons.add(playButton);
        buttons.add(pauseButton);
        buttons.add(resetButton);

        controlPanel.add(msg);
        controlPanel.add(buttons);

        add(view, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);

        reset();
    }

    private void solve() {
        msg.setText("Simulating...");
        playButton.setEnabled(false);
        pauseButton.setEnabled(true);
        solver.start();
    }

    private void pause() {
        /*try {
            solver.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/


    }

    private void reset() {
        msg.setText("Ready");
        playButton.setEnabled(true);
        pauseButton.setEnabled(false);

        if(solver != null) {
            model.stop();
            solver.interrupt();
        }

        model = new FireModel(view);
        solver = new Thread(new Runnable() {
            @Override
            public void run() {
                model.solve(msg);
            }
        });
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
        smokey.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == playButton) solve();
        if(e.getSource() == pauseButton) pause();
        if(e.getSource() == resetButton) reset();
    }
}
