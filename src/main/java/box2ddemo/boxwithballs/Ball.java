package box2ddemo.boxwithballs;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class Ball {

    Body ball;
    private final World world;
    int radius;
    private int forceX, forceY;
    private float xCoordinate, yCoordinate;

    public Ball(World world, int radius, int forceX, int forceY, int xCoordinate, int yCoordinate) {
        this.world = world;
        this.radius = radius;
        this.forceX = forceX;
        this.forceY = forceY;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        ball = createBall();
        createFixture();
    }

    private Body createBall() {
        BodyDef ballDef = new BodyDef();
        ballDef.position.set(new Vector2(xCoordinate, yCoordinate));
        ballDef.type = BodyDef.BodyType.DynamicBody;
        return world.createBody(ballDef);
    }

    private void createFixture() {
        FixtureDef fixtureDef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(radius);
        fixtureDef.shape = shape;
        fixtureDef.restitution = 1;
        ball.createFixture(fixtureDef);
        ball.applyForceToCenter(forceX, forceY, true);
    }
}
