import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import java.util.Random;


/**
 *
 * @author Geer
 */
public class Queen extends AngleTurnBee{
    
    private Point2D location;
    SwarmInterface swarm;
            
    public Queen(SwarmInterface sw, Point2D oldLocation, Point2D firstMoveVector) {
        super(sw, oldLocation, firstMoveVector);
        swarm = sw;
        super.setColor(Color.CRIMSON);
    }
    
    @Override
    public double findNewSpeed() {
        Random r = new Random();
        int high = 20;
        int low = 10;
        double speed = (r.nextInt(high - low) + low);
        return speed;
    }
    
     
    @Override
    public Point2D findNewDirection() {
        Point2D oldDir = this.getDirection();
        double change = Math.random() - 0.5;
        location = getLocation();
        Point2D newDir = new Point2D(oldDir.getX() + change, oldDir.getY() + change);
        if(location.getX() >= swarm.xDim() || location.getY() >= swarm.yDim() || 
                location.getX() <= 0 || location.getY() <= 0)
            return newDir.multiply(-1).normalize();
        return newDir.normalize();
    }
        
    @Override
    void update() {
        turnAndMove(); 
    }
    
    public Point2D getQueenLocation() {
        return this.location;
    }
}
