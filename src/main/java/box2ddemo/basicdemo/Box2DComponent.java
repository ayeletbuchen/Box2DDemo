package box2ddemo.basicdemo;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import javax.swing.*;
import java.awt.*;

public class Box2DComponent extends JComponent {

    private static final float BOX_TO_SCREEN = 5f;
    private static final float SCREEN_TO_BOX = 1f / BOX_TO_SCREEN;
    private long startTime;
    private final World world;
    private Body ball;
    private Body ground;

    public Box2DComponent() {
        World.setVelocityThreshold(0);
        world = new World(new Vector2(0, 9.8f * SCREEN_TO_BOX), false);
        createGround();
        createBall();
        startTime = System.currentTimeMillis();
    }

    private void createGround() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(new Vector2(-100f * SCREEN_TO_BOX, 200f * SCREEN_TO_BOX));
        bodyDef.type = BodyDef.BodyType.StaticBody;
        ground = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(900 * SCREEN_TO_BOX, 1f * SCREEN_TO_BOX);
        fixtureDef.shape = shape;
        fixtureDef.restitution = 1;
        ground.createFixture(fixtureDef);
    }

    private void createBall() {
        BodyDef ballDef = new BodyDef();
        ballDef.type = BodyDef.BodyType.DynamicBody;
//        ballDef.angularVelocity = 40;
//        ballDef.angle =  (float) Math.toRadians(30);
        ball = world.createBody(ballDef);
        FixtureDef fixtureDef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(20 * SCREEN_TO_BOX);
        fixtureDef.shape = shape;
        ball.createFixture(fixtureDef);

        ball.applyForceToCenter(200 * SCREEN_TO_BOX, -30 * SCREEN_TO_BOX, true);
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        long currentTime = System.currentTimeMillis();
        world.step((currentTime - startTime) / 1000f,6, 2);
        startTime = currentTime;
        // int radius = (int) (ball.getFixtureList().get(0).getShape() * BOX_TO_SCREEN);
        graphics.fillOval((int) (ball.getPosition().x * BOX_TO_SCREEN), (int) (ball.getPosition().y * BOX_TO_SCREEN), 20, 20);
        graphics.fillRect((int) (ground.getPosition().x * BOX_TO_SCREEN), (int) (ground.getPosition().y * BOX_TO_SCREEN), 900, 1);
        repaint();
    }
}
