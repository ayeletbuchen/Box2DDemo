package box2ddemo.boxwithballs;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class Box {

    Body top, bottom, left, right;
    private final World world;
    private float xCoordinate, yCoordinate;
    float width, height;

    public Box(World world, float xCoordinate, float yCoordinate, float width, float height) {
        this.world = world;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.width = width;
        this.height = height;

        top = createBody(xCoordinate, yCoordinate);
        bottom = createBody(xCoordinate, yCoordinate + height);
        left = createBody(xCoordinate, yCoordinate);
        right = createBody(xCoordinate + width, yCoordinate);
        createFixture(top);
        createFixture(bottom);
        createFixture(left);
        createFixture(right);
    }

    private Body createBody(float xCoordinate, float yCoordinate) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(new Vector2(xCoordinate, yCoordinate));
        bodyDef.type = BodyDef.BodyType.StaticBody;
        return world.createBody(bodyDef);
    }

    private void createFixture(Body wall) {
        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape shape = new PolygonShape();

        if (wall == top || wall == bottom) {
            shape.setAsBox(width, 1);
        } else {
            shape.setAsBox(1, height);
        }
        fixtureDef.shape = shape;
        fixtureDef.restitution = 1;
        wall.createFixture(fixtureDef);
    }
}
