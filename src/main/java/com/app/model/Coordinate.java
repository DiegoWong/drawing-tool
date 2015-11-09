package com.app.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Class used for create coordinate to be used in a Canvas
 */
@Getter
@Setter
@AllArgsConstructor
public class Coordinate implements Cloneable{
    private int x;
    private int y;

    public Coordinate (Coordinate coordinate){
        this.x = coordinate.getX();
        this.y = coordinate.getY();
    }

    public void moveLeft(){
        x -=1;
    }

    public void moveRight(){
        x +=1;
    }

    public void moveUp(){
        y -= 1;
    }

    public void moveDown(){
        y += 1;
    }
}
