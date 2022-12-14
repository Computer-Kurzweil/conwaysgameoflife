package org.woehlke.computer.kurzweil.conwaysgameoflive.view.canvas.garden;

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
public class GardenOfEdenPanelRow extends SubTabImpl implements Serializable {

    static final long serialVersionUID = 242L;

    @ToString.Exclude
    private final ConwaysGameOfLiveTab tab;
    @ToString.Exclude
    private final ConwaysGameOfLiveModel tabModel;
    private final GardenOfEdenCheckBox gardenOfEdenEnabled;
    private final GardenOfEdenToggleButton buttonToggleGardenOfEden;
    //private final GardenOfEdenPanel gardenOfEdenPanel;

    public GardenOfEdenPanelRow(ConwaysGameOfLiveTab tab) {
        super("Garden of Eden",tab.getComputerKurzweilProperties());
        this.tab = tab;
        this.tabModel = this.tab.getConwaysGameOfLiveModel();
        this.gardenOfEdenEnabled = new GardenOfEdenCheckBox(this.tab);
        this.buttonToggleGardenOfEden = new GardenOfEdenToggleButton(this.tab);
        /*
        this.gardenOfEdenPanel = new GardenOfEdenPanel(this.tabCtx);
        this.gardenOfEdenPanel.add(this.gardenOfEdenEnabled);
        this.gardenOfEdenPanel.add(this.buttonToggleGardenOfEden);
        this.add( this.gardenOfEdenPanel);
        */
        this.add(this.gardenOfEdenEnabled);
        this.add(this.buttonToggleGardenOfEden);
    }

    public void setSelected(boolean selected) {
        this.tabModel.getSimulatedEvolutionParameter().setGardenOfEdenEnabled(selected);
        update();
    }

    public void toggleGardenOfEden() {
        this.tabModel.getSimulatedEvolutionParameter().toggleGardenOfEden();
        update();
    }

    public void addActionListener(TabPanel myTabPanel) {
        this.buttonToggleGardenOfEden.addActionListener(myTabPanel);
    }

    public void update() {
        boolean enabled = tabModel.getSimulatedEvolutionParameter().isGardenOfEdenEnabled();
        this.buttonToggleGardenOfEden.setSelected(enabled);
        this.gardenOfEdenEnabled.setSelected(enabled);
    }
}
