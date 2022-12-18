package agh.ics.opp.elements;

import agh.ics.opp.Vector2d;

public class Plant extends AbstractMapElement{
    private final Vector2d position;
    private final int energy;

    public Plant(Vector2d position, int energy){
        this.position = position;
        this.energy = energy;
    }

    public int getEnergy(){
        return this.energy;
    }

    @Override
    public Vector2d getPosition(){
        return position;
    }

    @Override
    public String getImagePath() {
        return null;
    }

    @Override
    public String getLabelName() {
        return null;
    }

    @Override
    public String toString() {
        return "φ";
    }
}
