package agh.ics.opp.simulation.gui;

import agh.ics.opp.simulation.map.elements.IMapElement;
import agh.ics.opp.simulation.map.IWorldMap;
import agh.ics.opp.simulation.types.Vector2d;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.io.FileNotFoundException;


public class MapRenderer{
    private final GridPane rootPane;
    private final Vector2d lowerLeft;
    private final Vector2d upperRight;
    private final IWorldMap map;
    private final int FIELD_GROW = 50;

    public MapRenderer(GridPane rootPane, IWorldMap map) throws FileNotFoundException {
        this.rootPane = rootPane;
        this.lowerLeft = map.getLowerLeft();
        this.upperRight = map.getUpperRight();
        this.map = map;
    }

    public void render(){
        Platform.runLater(() -> {

            rootPane.getColumnConstraints().clear();
            rootPane.getRowConstraints().clear();
            Node gr = rootPane.getChildren().get(0);

            rootPane.getChildren().clear();
            rootPane.add(gr,0,0);
            rootPane.setGridLinesVisible(true);

            rootPane.getColumnConstraints().add(new ColumnConstraints(FIELD_GROW));
            Label label = new Label("y/x");
            rootPane.add(label, 0,0);
            GridPane.setHalignment(label, HPos.CENTER);
            GridPane.setValignment(label, VPos.CENTER);
            renderHeader();

            for (int row = upperRight.y; row >= lowerLeft.y; row--) {
                label = new Label(String.valueOf(upperRight.y-row+lowerLeft.y));
                rootPane.add(label, 0,row-lowerLeft.y+1);
                GridPane.setHalignment(label, HPos.CENTER);
                GridPane.setValignment(label, VPos.CENTER);
                renderRow(row);
            }
        });
    }

    private void renderHeader(){
        rootPane.getRowConstraints().add(new RowConstraints(FIELD_GROW));
        for (int col = lowerLeft.x; col <= upperRight.x; col++) {
            rootPane.getColumnConstraints().add(new ColumnConstraints(FIELD_GROW));
            Label label = new Label(String.valueOf(col));
            GridPane.setHalignment(label, HPos.CENTER);
            rootPane.add(label, col-lowerLeft.x+1, 0);
        }
    }

    private void renderRow(int row){
        rootPane.getRowConstraints().add(new RowConstraints(FIELD_GROW));
        for (int col = lowerLeft.x; col <= upperRight.x; col++){
            GuiElementBox elementBox = new GuiElementBox((IMapElement) this.map.objectAt(new Vector2d(col, row)));
            elementBox.renderElement(rootPane, col - lowerLeft.x + 1, upperRight.y - row + 1);
        }
    }
}