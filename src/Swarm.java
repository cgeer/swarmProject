/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.Random;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

/**
 *
 * @author pipWolfe
 */
public class Swarm extends DrawingPane implements SwarmInterface {

    private static final int NUM_BEES = 200;
    private Follower[] bees = new Follower[NUM_BEES];
    private Queen queen;
    private static final int MILLISEC = 50;


    public Swarm() {
        super(MILLISEC, Color.BLACK);
        initializeBees();

    }

    public static void main(String args[]) {
        Application.launch(args);
    }

    
    private void initializeBees() {
        Random random = new Random();
        queen = new Queen(this, new Point2D(random.nextInt(xDim()), random.nextInt(yDim())), 
                new Point2D(random.nextInt(12), random.nextInt(12)));
        for (int i = 0; i < bees.length; i++) {
            bees[i] = new Follower(this, new Point2D(random.nextInt(xDim()), random.nextInt(yDim())), 
                new Point2D(random.nextInt(12), random.nextInt(12)));
        }
    }

    @Override
    public void update() {
        queen.update();
        for (int i = 0; i < bees.length; i++) {
            bees[i].update();
        }
    }

    @Override
    public int xDim() {
        return (int) pane.getHeight();
    }

    @Override
    public int yDim() {
        return (int) pane.getWidth();
    }

    @Override
    public void addLine(Line line) {
        pane.getChildren().add(line);
    }
    
    public Queen getQueen() {
        return queen;
    }
}
