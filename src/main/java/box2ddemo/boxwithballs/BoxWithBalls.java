package box2ddemo.boxwithballs;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BoxWithBalls extends JComponent {

    private long startTime;
    private final World world;
    private Box box;
    private ArrayList<Ball> balls;

    public BoxWithBalls() {
        World.setVelocityThreshold(0);
        world = new World(new Vector2(0, 9.8f), false);
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
        graphics.fillRect((int) box.top.getPosition().x, (int) box.top.getPosition().y, (int) box.width, 1);
        graphics.fillRect((int) box.bottom.getPosition().x, (int) box.bottom.getPosition().y, (int) box.width, 1);
        graphics.fillRect((int) box.left.getPosition().x, (int) box.left.getPosition().y, 1, (int) box.height);
        graphics.fillRect((int) box.right.getPosition().x, (int) box.right.getPosition().y, 1, (int) box.height);
        for (Ball ball : balls) {
            graphics.fillOval((int) ball.ball.getPosition().x, (int) ball.ball.getPosition().y, ball.radius, ball.radius);
        }
        repaint();
    }
}
