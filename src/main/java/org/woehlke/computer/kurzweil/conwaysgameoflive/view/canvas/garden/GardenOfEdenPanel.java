package org.woehlke.computer.kurzweil.conwaysgameoflive.view.canvas.garden;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.woehlke.computer.kurzweil.conwaysgameoflive.view.ConwaysGameOfLiveTab;

import javax.swing.*;
import java.awt.*;
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
public class GardenOfEdenPanel extends JPanel implements Serializable {

    static final long serialVersionUID = 242L;

    @ToString.Exclude
    private final ConwaysGameOfLiveTab tab;
    private final String gardenOfEdenPanelBorderLabel;
    //private final CompoundBorder gardenOfEdenPanelBorder;

    public GardenOfEdenPanel(ConwaysGameOfLiveTab tab) {
        super(new FlowLayout());
        this.tab = tab;
        this.gardenOfEdenPanelBorderLabel =
            tab.getComputerKurzweilProperties().getSimulatedevolution().getGardenOfEden().getPanelGardenOfEden();
        //this.gardenOfEdenPanelBorder = this.tabCtx.getCtx().getBorder(gardenOfEdenPanelBorderLabel);
        //this.setBorder(gardenOfEdenPanelBorder);
    }
}
