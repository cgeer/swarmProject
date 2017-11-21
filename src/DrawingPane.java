/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author pipWolfe
 */
public abstract class DrawingPane extends Application {
    static final int PANE_X_DIM = 800;
    static final int PANE_Y_DIM = 800;
    private int millisec = 100;
    Pane pane;
    private Scene scene;

    public DrawingPane(int millisec, Color backgroundColor) {
        pane = new Pane();
        BackgroundFill myBF = new BackgroundFill(backgroundColor, new CornerRadii(1),
         new Insets(0.0,0.0,0.0,0.0));// or null for the padding
        pane.setBackground(new Background(myBF));
        this.millisec = millisec;
        scene = new Scene(pane, PANE_X_DIM, PANE_Y_DIM);

    }
    

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Swarm"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage

        setUpAnimation();
        primaryStage.show(); // Display the stage
        // set up a timeline?
    }

    protected void setUpAnimation() {
        // Create a handler
        EventHandler<ActionEvent> eventHandler = (ActionEvent e) -> {
            update();
        };
        // Create an animation for alternating text
        Timeline animation = new Timeline(new KeyFrame(Duration.millis(millisec), eventHandler));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();
    }

    
    
    abstract public void update();
    
}
