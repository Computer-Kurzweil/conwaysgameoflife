package org.woehlke.computer.kurzweil.conwaysgameoflive.view;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.woehlke.computer.kurzweil.conwaysgameoflive.config.ComputerKurzweilProperties;
import org.woehlke.computer.kurzweil.conwaysgameoflive.control.ConwaysGameOfLiveController;
import org.woehlke.computer.kurzweil.conwaysgameoflive.model.ConwaysGameOfLiveModel;
import org.woehlke.computer.kurzweil.conwaysgameoflive.model.world.SimulatedEvolutionParameter;
import org.woehlke.computer.kurzweil.conwaysgameoflive.view.canvas.ConwaysGameOfLiveCanvas;
import org.woehlke.computer.kurzweil.conwaysgameoflive.view.canvas.population.PopulationStatisticsElementsPanelLifeCycle;

import javax.accessibility.Accessible;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.ImageObserver;
import java.io.Serializable;

import static javax.swing.SwingConstants.CENTER;

/**
 * This Frame is the Container for running the Simulation.
 * It containes a World Data Model, a Controller Thread and a WorldCanvas View.
 *
 * &copy; 2006 - 2008 Thomas Woehlke.
 * @author Thomas Woehlke
 *
 * @see ConwaysGameOfLiveModel
 * @see ConwaysGameOfLiveController
 * @see ConwaysGameOfLiveCanvas
 * @see PopulationStatisticsElementsPanelLifeCycle
 *
 * @see ComputerKurzweilProperties
 * @see SimulatedEvolutionParameter
 *
 * @see javax.swing.JFrame
 * @see java.awt.image.ImageObserver
 * @see java.awt.event.WindowListener
 *
 * @see <a href="https://github.com/Computer-Kurzweil/conwaysgameoflife">Github Repository</a>
 * @see <a href="https://java.woehlke.org/conwaysgameoflife/">Maven Project Repository</a>
 *
 * Date: 04.02.2006
 * Time: 18:47:46
 */
@Log4j2
@Getter
public class ConwaysGameOfLiveTab extends JFrame implements MenuContainer,
    WindowListener, ActionListener, Serializable, ImageObserver, Accessible {

    static final long serialVersionUID = 242L;

    private final static String TITLE = "Conways Game of Life";

    private final static int EXIT_STATUS = 0;

    private final static String APPLET_POSITION = "Center";

    private final static int HEIGHT_OF_TITLE = 30;

    private final static int HEIGHT_OF_STATISTICS = 60;

    private final SimulatedEvolutionParameter simulatedEvolutionParameter;

    private final ComputerKurzweilProperties computerKurzweilProperties;

    /**
     * Subtitle Label for DesktopApp and Title Label for Applet.
     */
    private final JLabel subTitleLabel;

    /**
     * Copyright Label
     */
    private final JLabel copyrightLabel;

    /**
     * ControllerThread for Interachtions between Model and View (MVC-Pattern).
     */
    private final ConwaysGameOfLiveController conwaysGameOfLiveController;

    /**
     * The View for the World. Food and Cells are painted to the Canvas.
     */
    private final ConwaysGameOfLiveCanvas canvas;

    /**
     * Data Model for the Simulation. The World contains the Bacteria Cells and the Food.
     */
    private final ConwaysGameOfLiveModel conwaysGameOfLiveModel;

    /**
     * Display how many Cells per LifeCycleStatus and how many Cells in the whole Population for this Generation.
     */
    private final PopulationStatisticsElementsPanelLifeCycle panelLifeCycle;


    private volatile Rectangle rectangleBounds;
    private volatile Dimension dimensionSize;

    public ConwaysGameOfLiveTab(ComputerKurzweilProperties computerKurzweilProperties) {
        super(TITLE);
        this.computerKurzweilProperties = computerKurzweilProperties;
        this.conwaysGameOfLiveModel = new ConwaysGameOfLiveModel(
            computerKurzweilProperties
        );
        this.canvas = new ConwaysGameOfLiveCanvas(this.conwaysGameOfLiveModel);
        this.panelLifeCycle = new PopulationStatisticsElementsPanelLifeCycle(
            this,
            this.conwaysGameOfLiveModel.getConwaysgameoflifePopulationCensusContainer()
        );
        this.conwaysGameOfLiveController = new ConwaysGameOfLiveController(
            this.conwaysGameOfLiveModel, this.canvas, this.panelLifeCycle, this
        );
        String subTitle =  computerKurzweilProperties.getConwaysgameoflife().getView().getSubtitle();
        String copyright =  computerKurzweilProperties.getConwaysgameoflife().getView().getCopyright();
        this.subTitleLabel = new JLabel(subTitle, CENTER);
        this.copyrightLabel = new JLabel(copyright, CENTER);
        this.simulatedEvolutionParameter = new SimulatedEvolutionParameter();
        //this.simulatedEvolutionApplet = new SimulatedEvolutionApplet(this);
        //this.simulatedEvolutionApplet.init();
        //this.add(APPLET_POSITION, simulatedEvolutionApplet);
        this.setLayout(new BorderLayout());
        this.add(this.subTitleLabel, BorderLayout.NORTH);
        this.add(this.canvas, BorderLayout.CENTER);
        this.add(this.panelLifeCycle, BorderLayout.SOUTH);
        addWindowListener(this);
        pack();
        showMe();
    }

    public void showMeInit() {
        pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width =  this.conwaysGameOfLiveModel.getWorldDimensions().getX();
        double height = this.conwaysGameOfLiveModel.getWorldDimensions().getY() + 60;
        double startX = (screenSize.getWidth() - width) / 2d;
        double startY = (screenSize.getHeight() - height) / 2d;
        int myheight = Double.valueOf(height).intValue();
        int mywidth = Double.valueOf(width).intValue();
        int mystartX = Double.valueOf(startX).intValue();
        int mystartY = Double.valueOf(startY).intValue();
        this.rectangleBounds = new Rectangle(mystartX, mystartY, mywidth, myheight);
        this.dimensionSize = new Dimension(mywidth, myheight);
        this.setBounds(this.rectangleBounds);
        this.setSize(this.dimensionSize);
        this.setPreferredSize(this.dimensionSize);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        toFront();
    }

    /**
     * Things to do, to show the Application Window again.
     */

    public void showMe(){
        setMyBounds();
        setVisible(true);
        toFront();
    }

    public void start() {
        conwaysGameOfLiveController.start();
    }

    public void windowOpened(WindowEvent e) {
        showMe();
    }

    private void setMyBounds(){
        int height = this.conwaysGameOfLiveModel.getWorldDimensions().getY();
        int width = this.conwaysGameOfLiveModel.getWorldDimensions().getX();
        int TITLE_HEIGHT = HEIGHT_OF_TITLE + HEIGHT_OF_STATISTICS;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double startX = (screenSize.getWidth() - height) / 2d;
        double startY = (screenSize.getHeight() - width) / 2d;
        int myheight = Double.valueOf(height).intValue() + TITLE_HEIGHT;
        int mywidth = Double.valueOf(width).intValue();
        int mystartX = Double.valueOf(startX).intValue();
        int mystartY = Double.valueOf(startY).intValue();
        setBounds(mystartX, mystartY, mywidth, myheight);
    }

    public void windowClosing(WindowEvent e) {
        System.exit(EXIT_STATUS);
    }

    public void windowClosed(WindowEvent e) {
        System.exit(EXIT_STATUS);
    }

    public void windowIconified(WindowEvent e) {

    }

    public void windowDeiconified(WindowEvent e) {
        showMe();
    }

    public void windowActivated(WindowEvent e) {
        toFront();
    }

    public void windowDeactivated(WindowEvent e) {
    }

    public void update(){}

    public void actionPerformed(ActionEvent actionEvent) {

    }
}
