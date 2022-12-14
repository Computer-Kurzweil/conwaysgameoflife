package org.woehlke.computer.kurzweil.conwaysgameoflive.model;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.woehlke.computer.kurzweil.conwaysgameoflive.config.ComputerKurzweilProperties;
import org.woehlke.computer.kurzweil.conwaysgameoflive.model.cell.Cell;
import org.woehlke.computer.kurzweil.conwaysgameoflive.model.census.ConwaysGameOfLivePopulationCensus;
import org.woehlke.computer.kurzweil.conwaysgameoflive.model.census.ConwaysGameOfLifePopulationCensusContainer;
import org.woehlke.computer.kurzweil.conwaysgameoflive.model.world.SimulatedEvolutionParameter;
import org.woehlke.computer.kurzweil.conwaysgameoflive.model.lattice.ConwaysGameOfLiveWorldLattice;
import org.woehlke.computer.kurzweil.conwaysgameoflive.model.world.WorldPoint;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * The World contains Water, Cells and Food.
 * It is the Data Model of the Simulation in a MVC Pattern.
 *
 * &copy; 2006 - 2008 Thomas Woehlke.
 * @author Thomas Woehlke
 *
 * @see Cell
 * @see ConwaysGameOfLiveWorldLattice
 *
 * @see <a href="https://github.com/Computer-Kurzweil/conwaysgameoflife">Github Repository</a>
 * @see <a href="https://java.woehlke.org/conwaysgameoflife/">Maven Project Repository</a>
 *
 * User: thomas
 * Date: 04.02.2006
 * Time: 19:06:20
 */
@Log4j2
@ToString(exclude = {"random"})
@EqualsAndHashCode(exclude = {"random"})
public class ConwaysGameOfLiveModel implements Serializable {

    static final long serialVersionUID = 242L;

    /**
     * List of the Simulated Bacteria Cells.
     */
    private List<Cell> cells;

    /**
     * Start with 20 Cells.
     */
    private final int INITIAL_POPULATION = 20;

    /**
     * Random Generator used for Bacteria Motion.
     */
    private Random random;

    /**
     * Definition of the World's Size in Pixel Width and Height.
     */
    @Getter
    private final WorldPoint worldDimensions;

    /**
     * Map of the World monitoring growth and eating food.
     */
    private final ConwaysGameOfLiveWorldLattice conwaysGameOfLiveWorldLattice;

    @Getter
    private final ConwaysGameOfLifePopulationCensusContainer conwaysgameoflifePopulationCensusContainer;

    @Getter
    private final SimulatedEvolutionParameter simulatedEvolutionParameter;

    @Getter
    private final ComputerKurzweilProperties computerKurzweilProperties;

    public ConwaysGameOfLiveModel(ComputerKurzweilProperties computerKurzweilProperties) {
        this.computerKurzweilProperties = computerKurzweilProperties;
        int scale = this.computerKurzweilProperties.getConwaysgameoflife().getView().getScale();
        int width = scale * this.computerKurzweilProperties.getConwaysgameoflife().getView().getWidth();
        int height = scale * this.computerKurzweilProperties.getConwaysgameoflife().getView().getHeight();
        this.worldDimensions = new WorldPoint(width,height);
        long seed = new Date().getTime();
        this.random = new Random(seed);
        this.conwaysGameOfLiveWorldLattice = new ConwaysGameOfLiveWorldLattice(
            this.worldDimensions, this.random
        );
        this.conwaysgameoflifePopulationCensusContainer = new ConwaysGameOfLifePopulationCensusContainer(
            computerKurzweilProperties
        );
        this.simulatedEvolutionParameter = new SimulatedEvolutionParameter();
        this.createPopulation();
    }

    /**
     * Create the initial Population of Bacteria Cells and give them their position in the World.
     */
    private void createPopulation() {
        ConwaysGameOfLivePopulationCensus populationCensus = new ConwaysGameOfLivePopulationCensus();
        cells = new ArrayList<Cell>();
        for (int i = 0; i < INITIAL_POPULATION; i++) {
            int x = random.nextInt(worldDimensions.getX());
            int y = random.nextInt(worldDimensions.getY());
            if (x < 0) {
                x *= -1;
            }
            if (y < 0) {
                y *= -1;
            }
            WorldPoint pos = new WorldPoint(x, y);
            Cell cell = new Cell(worldDimensions, pos, random);
            cells.add(cell);
            populationCensus.countStatusOfOneCell(cell.getLifeCycleStatus());
        }
        this.conwaysgameoflifePopulationCensusContainer.push(populationCensus);
    }

    /**
     * One Step of Time in the World in which the Population of Bacteria Cell perform Life:
     * Every Cell moves, eats, dies of hunger, and it has sex: splitting into two children with changed DNA.
     */
    public boolean letLivePopulation() {
        ConwaysGameOfLivePopulationCensus populationCensus = new ConwaysGameOfLivePopulationCensus();
        conwaysGameOfLiveWorldLattice.letFoodGrow();
        WorldPoint pos;
        List<Cell> children = new ArrayList<Cell>();
        List<Cell> died = new ArrayList<Cell>();
        for (Cell cell:cells) {
            cell.move();
            if(cell.died()){
                died.add(cell);
            } else {
                pos = cell.getPosition();
                int food = conwaysGameOfLiveWorldLattice.eat(pos);
                cell.eat(food);
                if (cell.isPregnant()) {
                    Cell child = cell.performReproductionByCellDivision();
                    children.add(child);
                }
            }
        }
        for(Cell dead:died){
            cells.remove(dead);
        }
        cells.addAll(children);
        for (Cell cell:cells) {
            populationCensus.countStatusOfOneCell(cell.getLifeCycleStatus());
        }
        this.conwaysgameoflifePopulationCensusContainer.push(populationCensus);
        return ! cells.isEmpty();
    }

    public List<Cell> getAllCells(){
        return cells;
    }

    public boolean hasFood(int x, int y) {
        return conwaysGameOfLiveWorldLattice.hasFood(x,y);
    }

    public boolean isPopulationAlive() {
        return !cells.isEmpty();
    }
}
