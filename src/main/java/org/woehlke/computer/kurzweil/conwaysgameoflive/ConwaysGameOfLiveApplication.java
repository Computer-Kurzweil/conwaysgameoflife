package org.woehlke.computer.kurzweil.conwaysgameoflive;

import lombok.extern.log4j.Log4j2;
import org.woehlke.computer.kurzweil.conwaysgameoflive.config.ComputerKurzweilProperties;
import org.woehlke.computer.kurzweil.conwaysgameoflive.view.ConwaysGameOfLiveTab;

/**
 * Class with main Method for Starting the Desktop Application.
 *
 * &copy; 2006 - 2008 Thomas Woehlke.
 * @author Thomas Woehlke
 *
 * @see ConwaysGameOfLiveTab
 *
 * @see <a href="https://thomas-woehlke.blogspot.com/2016/01/mandelbrot-set-drawn-by-turing-machine.html">Blog Article</a>
 * @see <a href="https://github.com/Computer-Kurzweil/simulated-evolution">Github Repository</a>
 * @see <a href="https://java.woehlke.org/simulated-evolution/">Maven Project Repository</a>
 */
@Log4j2
public class ConwaysGameOfLiveApplication {

    private final ConwaysGameOfLiveTab conwaysGameOfLiveTab;

    private ConwaysGameOfLiveApplication() {
        String configFileName = "application.yml";
        String jarFilePath = "target/conwaysgameoflife.jar";
        ComputerKurzweilProperties properties = ComputerKurzweilProperties.propertiesFactory(configFileName, jarFilePath);
        this.conwaysGameOfLiveTab = new ConwaysGameOfLiveTab(properties);

    }

    public void start(){
        conwaysGameOfLiveTab.start();
    }

    /**
     * Starting the Desktop Application
     * @param args CLI Parameter
     */
    public static void main(String[] args) {
        ConwaysGameOfLiveApplication application = new ConwaysGameOfLiveApplication();
        application.start();
    }
}
