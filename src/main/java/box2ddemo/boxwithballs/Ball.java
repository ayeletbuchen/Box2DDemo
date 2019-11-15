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
        ballDef.position.set(new Vector2(xCoordinate * BoxWithBalls.SCREEN_TO_BOX, yCoordinate * BoxWithBalls.SCREEN_TO_BOX));
        ballDef.type = BodyDef.BodyType.DynamicBody;
        return world.createBody(ballDef);
    }

    private void createFixture() {
        FixtureDef fixtureDef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(radius * BoxWithBalls.SCREEN_TO_BOX);
        fixtureDef.shape = shape;
        fixtureDef.restitution = 1f;
        ball.createFixture(fixtureDef);
        ball.applyForceToCenter(forceX * BoxWithBalls.SCREEN_TO_BOX, forceY * BoxWithBalls.SCREEN_TO_BOX, true);
    }
}
