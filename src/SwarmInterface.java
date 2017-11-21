/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import javafx.scene.shape.Line;

/**
 *
 * @author pipWolfe
 */
public interface SwarmInterface {

    void addLine(Line line);

    int xDim();

    int yDim();
    
    Queen getQueen();
    
}
