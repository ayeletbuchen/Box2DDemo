package box2ddemo;

import box2ddemo.boxwithballs.BoxWithBalls;

import javax.swing.*;
import java.awt.*;

public class Box2DFrame extends JFrame {

    public Box2DFrame() {
        setSize(800, 600);
        setTitle("Box2DDemo");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        add(new BoxWithBalls(), BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        new Box2DFrame().setVisible(true);
    }
}
