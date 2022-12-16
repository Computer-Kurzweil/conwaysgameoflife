package org.woehlke.computer.kurzweil.simulated.evolution.view.canvas.garden;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.woehlke.computer.kurzweil.simulated.evolution.view.ConwaysGameOfLiveTab;

import javax.swing.*;
import java.io.Serializable;

/**
 * &copy; 2006 - 2008 Thomas Woehlke.
 * @author Thomas Woehlke
 *
 * @see <a href="https://thomas-woehlke.blogspot.com/2016/01/mandelbrot-set-drawn-by-turing-machine.html">Blog Article</a>
 * @see <a href="https://github.com/Computer-Kurzweil/simulated-evolution">Github Repository</a>
 * @see <a href="https://java.woehlke.org/simulated-evolution/">Maven Project Repository</a>
 */
@Log4j2
@Getter
@ToString(callSuper = true)
@Deprecated
public class GardenOfEdenToggleButton extends JToggleButton implements Serializable {

    static final long serialVersionUID = 242L;

    @ToString.Exclude
    private final ConwaysGameOfLiveTab tab;
    private final String buttonToggleGardenOfEdenString;

    public GardenOfEdenToggleButton(ConwaysGameOfLiveTab tab) {
        super(
            tab.getComputerKurzweilProperties().getSimulatedevolution()
                .getGardenOfEden().getGardenOfEdenEnabledToggleButton()
        );
        this.tab = tab;
        this.buttonToggleGardenOfEdenString =
            tab.getComputerKurzweilProperties().getSimulatedevolution()
                .getGardenOfEden().getGardenOfEdenEnabledToggleButton();
    }
}
