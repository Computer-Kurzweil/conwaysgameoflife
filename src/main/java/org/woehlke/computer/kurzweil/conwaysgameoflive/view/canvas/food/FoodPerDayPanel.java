package org.woehlke.computer.kurzweil.conwaysgameoflive.view.canvas.food;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.woehlke.computer.kurzweil.conwaysgameoflive.view.ConwaysGameOfLiveTab;
import org.woehlke.computer.kurzweil.conwaysgameoflive.view.tabs.SubTabImpl;
import org.woehlke.computer.kurzweil.conwaysgameoflive.view.tabs.TabPanel;
import org.woehlke.computer.kurzweil.conwaysgameoflive.model.ConwaysGameOfLiveModel;

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
public class FoodPerDayPanel extends SubTabImpl implements Serializable {

    static final long serialVersionUID = 242L;

    @ToString.Exclude
    private final ConwaysGameOfLiveTab tab;
    private final String foodPerDayBorderLabel;
    private final FoodPerDayLabel foodPerDayLabel;
    private final FoodPerDayTextField foodPerDayTextField;
    private final FoodPerDayIncreaseButton foodPerDayIncreaseButton;
    private final FoodPerDayDecreaseButton foodPerDayDecreaseButton;
    private final ConwaysGameOfLiveModel tabModel;

    public FoodPerDayPanel(ConwaysGameOfLiveTab tab) {
        super(
            tab.getComputerKurzweilProperties().getSimulatedevolution().getFood().getFoodPerDayLabel(),
            tab.getComputerKurzweilProperties()
        );
        this.tab = tab;
        this.tabModel = this.tab.getConwaysGameOfLiveModel();
        this.foodPerDayLabel = new FoodPerDayLabel(this.tab);
        this.foodPerDayTextField = new FoodPerDayTextField(this.tab);
        this.foodPerDayIncreaseButton = new FoodPerDayIncreaseButton(this.tab);
        this.foodPerDayDecreaseButton = new FoodPerDayDecreaseButton(this.tab);
        this.foodPerDayBorderLabel =
            tab.getComputerKurzweilProperties().getSimulatedevolution().getFood().getFoodPerDayLabel();
        //this.foodPerDayBorder = this.tabCtx.getCtx().getBorder(this.foodPerDayBorderLabel);
        //this.setBorder(this.foodPerDayBorder);
        this.add(this.foodPerDayLabel);
        this.add(this.foodPerDayTextField);
        this.add(this.foodPerDayIncreaseButton);
        this.add(this.foodPerDayDecreaseButton);
    }

    public void addActionListener(TabPanel myTabPanel) {
        this.foodPerDayIncreaseButton.addActionListener(myTabPanel);
        this.foodPerDayDecreaseButton.addActionListener(myTabPanel);
    }

    public void update() {
        //int foodPerDay = tabModel.getSimulatedEvolutionParameter().getFoodPerDay();
        //this.foodPerDayTextField.setFoodPerDay(foodPerDay);
    }
}
