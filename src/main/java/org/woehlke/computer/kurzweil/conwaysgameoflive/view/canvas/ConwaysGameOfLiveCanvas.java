package org.woehlke.computer.kurzweil.conwaysgameoflive.view.canvas;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.woehlke.computer.kurzweil.conwaysgameoflive.model.cell.Cell;
import org.woehlke.computer.kurzweil.conwaysgameoflive.model.world.WorldPoint;
import org.woehlke.computer.kurzweil.conwaysgameoflive.model.ConwaysGameOfLiveModel;

import javax.swing.*;
import java.awt.*;


/**
 * View for the World Data Model for Displaying Food and Bacteria Cells.
 *
 * &copy; 2006 - 2008 Thomas Woehlke.
 * @author Thomas Woehlke
 *
 * @see <a href="https://github.com/Computer-Kurzweil/conwaysgameoflife">Github Repository</a>
 * @see <a href="https://java.woehlke.org/conwaysgameoflife/">Maven Project Repository</a>
 *
 * Date: 05.02.2006
 * Time: 00:51:51
 */
@Log4j2
@Getter
public class ConwaysGameOfLiveCanvas extends JComponent {

    static final long serialVersionUID = 242L;

    /**
     * Reference to the Data Model.
     */
    private final ConwaysGameOfLiveModel tabModel;

    private final Color WATER = Color.BLACK;
    private final Color FOOD = Color.GREEN;

    public ConwaysGameOfLiveCanvas(ConwaysGameOfLiveModel tabModel) {
        this.tabModel = tabModel;
        this.setBackground(WATER);
        this.setSize(this.tabModel.getWorldDimensions().getX(), this.tabModel.getWorldDimensions().getY());
    }

    /**
     * Paint the World on the Canvas and show Food and Bacteria Cells.
     * @param g Graphics Context with the Tools for Painting.
     */
    public void paint(Graphics g) {
        super.paintComponent(g);
        int width = this.tabModel.getWorldDimensions().getX();
        int height = this.tabModel.getWorldDimensions().getY();
        //paint water
        g.setColor(WATER);
        g.fillRect(0,0,width,height);
        //paint food
        g.setColor(FOOD);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (tabModel.hasFood(x, y)) {
                    g.drawLine(x,y,x,y);
                }
            }
        }
        //paint all Cells
        for (Cell p:tabModel.getAllCells()) {
            WorldPoint[] square = p.getPosition().getNeighbourhood(this.tabModel.getWorldDimensions());
            g.setColor(p.getLifeCycleStatus().getColor());
            for(WorldPoint pixel:square){
                g.drawLine(pixel.getX(),pixel.getY(),pixel.getX(),pixel.getY());
            }
        }
    }

    public void update(Graphics g) {
        paint(g);
    }

}
