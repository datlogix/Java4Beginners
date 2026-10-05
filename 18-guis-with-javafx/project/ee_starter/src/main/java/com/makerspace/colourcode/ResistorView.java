package com.makerspace.colourcode;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

/**
 * A resistor drawn from shapes in the scene graph. Because the bands are OBJECTS,
 * changing a band only needs its fill changing: no repainting.
 */
public class ResistorView extends Pane {
    private final Rectangle[] bands = new Rectangle[4];

    public ResistorView() {
        setPrefSize(520, 160);
        Line wire = new Line(20, 80, 500, 80);
        wire.setStrokeWidth(6);
        wire.setStroke(Color.GRAY);
        Rectangle body = new Rectangle(110, 40, 300, 80);
        body.setArcWidth(50);
        body.setArcHeight(50);
        body.setFill(Color.rgb(225, 195, 150));
        getChildren().addAll(wire, body);
        // TODO: create the four band Rectangles (three close together on the left, the
        //       tolerance band set apart on the right), store them in bands[], and add them
    }

    /** Shows these four colours. */
    public void setBands(BandColour[] colours) {
        // TODO: set each band's fill to colours[i].getColour()
    }
}
