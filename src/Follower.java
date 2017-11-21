
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Geer
 */
public class Follower extends AngleTurnBee{
    private Queen myQueen;
    
    public Follower(SwarmInterface sw, Point2D oldLocation, Point2D firstMoveVector) {
        super(sw, oldLocation, firstMoveVector);
        super.setColor(Color.BLUE);
        myQueen = sw.getQueen();
    }
    
   @Override
    public Point2D getCurrentTarget() {
        return myQueen.getQueenLocation();
    }
    
   
}
