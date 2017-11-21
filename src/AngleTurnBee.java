/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

class AngleTurnBee {

    public final static double EPSILON = 0.3;
    private final Point2D[] corners = new Point2D[8];
    private int currentCorner;
    public final static int BORDER = 80;
    private final static Color[] COLORS = {Color.DARKSLATEBLUE, Color.BLUEVIOLET, Color.DARKRED,
        Color.DARKORANGE, Color.YELLOWGREEN, Color.DARKGREEN, Color.DARKSEAGREEN, Color.CADETBLUE
    };
    private Point2D location;
    private final Line line;

    /**
     * Create a bee at a given location on the Swarm window.
     *
     * @param sw the Swarm Window
     * @param oldLocation the Bee's first location
     * @param firstMoveVector the amount that the Bee moves in its first step.
     */
    AngleTurnBee(SwarmInterface sw, Point2D oldLocation, Point2D firstMoveVector) {
        this.location = oldLocation.add(firstMoveVector);

        this.line = new Line(oldLocation.getX(), oldLocation.getY(),
                location.getX(), location.getY());
        line.setStrokeWidth(2.0);
        line.setStroke(Color.BLUEVIOLET);
        sw.addLine(this.line);
        setColor(COLORS[0]);
        
        corners[0] = new Point2D(BORDER, BORDER);
        corners[1] = new Point2D(sw.xDim() / 2, sw.yDim() / 2);
        corners[2] = new Point2D(BORDER, sw.yDim() - BORDER);
        corners[3] = new Point2D(sw.xDim() / 2, sw.yDim() / 2);
        corners[4] = new Point2D(sw.xDim() - BORDER, sw.yDim() - BORDER);
        corners[5] = new Point2D(sw.xDim() / 2, sw.yDim() / 2);
        corners[6] = new Point2D(sw.xDim() - BORDER, BORDER);
        corners[7] = new Point2D(sw.xDim() / 2, sw.yDim() / 2);
        
        currentCorner = 0;

    }

    /**
     * Sets the stroke color of the Bee's line.
     * @param color 
     */
    public void setColor(Color color) {
        line.setStroke(color);
    }

    /**
     * Get the bee's current location.
     *
     * @return a point in 2D space representing the Bee's location on the
     * screen.
     */
    public Point2D getLocation() {
        return location;
    }

    /**
     * This method should move the bee, treating the parameter passed in as a
     * vector. The vector records both the amount the bee must move in the x
     * direction (use getX()), and in the y direction (use getY()).
     *
     * @param vector amount to move the bee in the x and y dimensions
     */
    public void move(Point2D vector) {
        line.setStartX(location.getX());
        line.setStartY(location.getY());
        location = location.add(vector);
        line.setEndX(location.getX());
        line.setEndY(location.getY());
    }

    /**
     * Get the bee's most recent direction, as a normalized (length = 1) vector.
     *
     * @return A normalized vector that points in the direction the bee most
     * recently moved.
     */
    public Point2D getDirection() {
        Point2D lastLocation = new Point2D(line.getStartX(), line.getStartY());
        return location.subtract(lastLocation).normalize();
    }

    /**
     * Get the direction of the target location.
     *
     * @return
     */
    public Point2D getDirectionOfTarget() {
        return getCurrentTarget().subtract(location).normalize();
    }


    /**
     * Finds a new direction for the bee to move in. The new direction should be
     * something between the bee's current direction (getVector()) and the
     * target vector from getDirectionOfTarget(), so that the bee slowly turns
     * towards the target. The amount by which the bee turns should have a
     * random component, so that all the bees don't clump together. See the
     * assignment handout for a suggested algorithm.
     *
     * @return a vector representing a direction between the current and target
     * directions.
     */
    public Point2D findNewDirection() {
        // get the two direction vectors and the fraction we want to turn between
        // them
        Point2D targetVec = getDirectionOfTarget();
        Point2D oldVec = getDirection();
        double fraction = EPSILON * Math.random();
        
        // get the angles of the two vectors, and the difference between the angles
        double oldAngle = Math.atan2(oldVec.getY(), oldVec.getX());
        double targetAngle = Math.atan2(targetVec.getY(), targetVec.getX());
        double difference = targetAngle - oldAngle;

        // If the angles are close to PI and -PI, respectively, the difference
        // ends up capturing the larger (>180 degrees) angle between them, rather
        // than the smaller angle from one to the other. In this case, reverse
        // the angle to get the smaller angle.
        if (Math.abs(difference) > Math.PI) {
            if (difference > 0) {
                difference = - 2*Math.PI + difference;
            } else {
                difference = 2*Math.PI + difference;
            }
        }
        
        // move the new angle a fraction of the way from the old
        // angle to the target angle
        double newAngle = oldAngle + fraction * (difference);

        // get x and y changes for the new angle, and return.
        double newX = Math.cos(newAngle);
        double newY = Math.sin(newAngle);
        return new Point2D(newX, newY);
    }

    /**
     * Gets the target location towards which the bee is currently moving. This
     * should be the target used for the most recent move.
     *
     * @return a point in 2D space representing the target location.
     */
    public Point2D getCurrentTarget() {
        return corners[currentCorner];
    }

    public double findNewSpeed() {
        return Math.min(15, location.distance(corners[currentCorner]) * 
                EPSILON * Math.random());
    }
    
    /**
     * Update the bee's location towards the target point. The bee should slowly
     * turn towards the target, rather than heading there in a straight line.
     *
     */
    public void turnAndMove() {
        double distance = findNewSpeed();
        Point2D newVector = findNewDirection();
        newVector = newVector.multiply(distance);
        move(newVector);
        //System.out.println(line);
    }
    
    /**
     * Updates the Bee's location on each animation update.
     */
    void update() {
        if (getLocation().distance(getCurrentTarget()) < 10) {
            currentCorner++;
            currentCorner = currentCorner % corners.length;
        }
        turnAndMove();
    }


}
