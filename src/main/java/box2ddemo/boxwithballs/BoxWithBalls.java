package box2ddemo.boxwithballs;

import box2ddemo.Box2DRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BoxWithBalls extends JComponent {

    static final float BOX_TO_SCREEN = 5f;
    static final float SCREEN_TO_BOX = 1f / BOX_TO_SCREEN;
    private long startTime;
    private final World world;
    private Box box;
    private ArrayList<Ball> balls;
    private Box2DRenderer renderer;

    public BoxWithBalls() {
        World.setVelocityThreshold(0);
        world = new World(new Vector2(0, 9.8f), false);
        renderer = new Box2DRenderer(world, BOX_TO_SCREEN);
        box = new Box(world, 100f, 100f, 300f, 300f);
        balls = new ArrayList<Ball>();
        balls.add(new Ball(world, 30, 50, 50, 105, 105));
        balls.add(new Ball(world, 20, 40, 40, 195, 105));
        balls.add(new Ball(world, 10, 30, 30, 105, 195));

        startTime = System.currentTimeMillis();
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        long currentTime = System.currentTimeMillis();
        world.step((currentTime - startTime) / 1000f,6, 2);
        startTime = currentTime;
        renderer.render((Graphics2D) graphics);
        repaint();
    }
}
