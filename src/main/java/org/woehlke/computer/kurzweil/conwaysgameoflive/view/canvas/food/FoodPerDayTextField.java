package org.woehlke.computer.kurzweil.conwaysgameoflive.view.canvas.food;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.woehlke.computer.kurzweil.conwaysgameoflive.view.ConwaysGameOfLiveTab;

import javax.swing.*;
import java.io.Serializable;

/**
 * &copy; 2006 - 2008 Thomas Woehlke.
 * @author Thomas Woehlke
 *
 * @see <a href="https://github.com/Computer-Kurzweil/conwaysgameoflife">Github Repository</a>
 * @see <a href="https://java.woehlke.org/conwaysgameoflife/">Maven Project Repository</a>
 */
@Log4j2
@Getter
@ToString(callSuper = true)
@Deprecated
public class FoodPerDayTextField extends JTextField implements Serializable {

    static final long serialVersionUID = 242L;

    @ToString.Exclude
    private final ConwaysGameOfLiveTab tab;
    private final String foodPerDayTextFieldString;
    private final int foodPerDayTextFieldCols;

    public FoodPerDayTextField(ConwaysGameOfLiveTab tab) {
        super(
            tab.getComputerKurzweilProperties().getSimulatedevolution().getFood().getFoodPerDay()+"",
            tab.getComputerKurzweilProperties().getSimulatedevolution().getFood().getFoodPerDayFieldColumns()
        );
        this.tab = tab;
        this.foodPerDayTextFieldString = tab.getComputerKurzweilProperties().getSimulatedevolution().getFood().getFoodPerDay()+"";
        this.foodPerDayTextFieldCols = tab.getComputerKurzweilProperties().getSimulatedevolution().getFood().getFoodPerDayFieldColumns();
    }

    public void setFoodPerDay(int foodPerDay){
        this.setText(""+foodPerDay);
    }
}
