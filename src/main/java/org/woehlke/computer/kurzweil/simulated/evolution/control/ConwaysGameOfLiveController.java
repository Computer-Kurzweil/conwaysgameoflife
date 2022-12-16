package org.woehlke.computer.kurzweil.simulated.evolution.control;

import lombok.extern.log4j.Log4j2;
import org.woehlke.computer.kurzweil.simulated.evolution.view.ConwaysGameOfLiveTab;
import org.woehlke.computer.kurzweil.simulated.evolution.view.canvas.ConwaysGameOfLiveCanvas;
import org.woehlke.computer.kurzweil.simulated.evolution.model.ConwaysGameOfLiveModel;
import org.woehlke.computer.kurzweil.simulated.evolution.view.canvas.population.PopulationStatisticsElementsPanelLifeCycle;

import java.io.Serializable;

/**
 * The ControllerThread controls the Interactions between Model and View (MVC-Pattern).
 *
 * &copy; 2006 - 2013 Thomas Woehlke.
 * @author Thomas Woehlke
 *
 * @see <a href="https://thomas-woehlke.blogspot.com/2016/01/mandelbrot-set-drawn-by-turing-machine.html">Blog Article</a>
 * @see <a href="https://github.com/Computer-Kurzweil/simulated-evolution">Github Repository</a>
 * @see <a href="https://java.woehlke.org/simulated-evolution/">Maven Project Repository</a>
 *
 * Date: 05.02.2006
 * Time: 00:36:20
 */
@Log4j2
public class ConwaysGameOfLiveController extends Thread implements Runnable, Serializable {

    static final long serialVersionUID = 242L;

    /**
     * Data Model for the Simulation
     */
    private final ConwaysGameOfLiveModel conwaysGameOfLiveModel;

    /**
     * Canvas, where to paint in the GUI.
     */
    private final ConwaysGameOfLiveCanvas canvas;
    private final PopulationStatisticsElementsPanelLifeCycle panelLifeCycle;
    private final ConwaysGameOfLiveTab tab;

    /**
     * Time to Wait in ms.
     */
    private final int timeToWait;

    /**
     * Control for Threading
     */
    private Boolean mySemaphore;

    public ConwaysGameOfLiveController(
        ConwaysGameOfLiveModel conwaysGameOfLiveModel,
        ConwaysGameOfLiveCanvas canvas,
        PopulationStatisticsElementsPanelLifeCycle panelLifeCycle,
        ConwaysGameOfLiveTab conwaysGameOfLiveTab
    ) {
        this.conwaysGameOfLiveModel = conwaysGameOfLiveModel;
        this.canvas = canvas;
        this.panelLifeCycle = panelLifeCycle;
        this.tab = conwaysGameOfLiveTab;
        this.timeToWait = this.tab.getComputerKurzweilProperties().getSimulatedevolution()
            .getControl().getThreadSleepTime();
        mySemaphore = Boolean.TRUE;
    }

    public void run() {
        boolean doMyJob;
        do {
            synchronized (mySemaphore) {
                doMyJob = mySemaphore.booleanValue();
            }
            doMyJob = conwaysGameOfLiveModel.letLivePopulation();
            canvas.repaint();
            panelLifeCycle.update();
            panelLifeCycle.repaint();
            tab.repaint();
            try {
                sleep(timeToWait);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        while (doMyJob);
    }

    public void exit() {
        synchronized (mySemaphore) {
            mySemaphore = Boolean.FALSE;
        }
    }
}
