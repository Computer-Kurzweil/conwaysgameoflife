package org.woehlke.computer.kurzweil.conwaysgameoflive.view.canvas.population;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.woehlke.computer.kurzweil.conwaysgameoflive.config.ComputerKurzweilProperties;
import org.woehlke.computer.kurzweil.conwaysgameoflive.model.census.ConwaysGameOfLifePopulationCensusContainer;
import org.woehlke.computer.kurzweil.conwaysgameoflive.view.ConwaysGameOfLiveTab;
import org.woehlke.computer.kurzweil.conwaysgameoflive.view.layouts.FlowLayoutCenter;
import org.woehlke.computer.kurzweil.conwaysgameoflive.view.tabs.SubTabImpl;
import org.woehlke.computer.kurzweil.conwaysgameoflive.model.census.ConwaysGameOfLivePopulationCensus;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.*;
import java.io.Serializable;

import static org.woehlke.computer.kurzweil.conwaysgameoflive.model.cell.LifeCycleStatus.*;

/**
 * Display how many Cells per LifeCycleStatus and how many Cells in the whole Population for this Generation.
 *
 * &copy; 2006 - 2008 Thomas Woehlke.
 * @author Thomas Woehlke
 *
 * @see <a href="https://github.com/Computer-Kurzweil/conwaysgameoflife">Github Repository</a>
 * @see <a href="https://java.woehlke.org/conwaysgameoflife/">Maven Project Repository</a>
 */
@Log4j2
@Getter
@ToString(callSuper = true,exclude = {"tab","border","layout","layoutSubPanel"})
@EqualsAndHashCode(callSuper=true,exclude = {"tab","border","layout","layoutSubPanel"})
public class PopulationStatisticsElementsPanelLifeCycle extends SubTabImpl implements Serializable {

    static final long serialVersionUID = 242L;

    private final PopulationStatisticsElement youngCellsElement;
    private final PopulationStatisticsElement youngAndFatCellsElement;
    private final PopulationStatisticsElement fullAgeCellsElement;
    private final PopulationStatisticsElement hungryCellsElement;
    private final PopulationStatisticsElement oldCellsElement;
    private final PopulationStatisticsElement wholeGeneration;

    private final String borderLabel;

    private final int initialPopulation;
    private final String youngCellsLabel;
    private final String youngAndFatCellsLabel;
    private final String fullAgeCellsLabel;
    private final String hungryCellsLabel;
    private final String oldCellsLabel;
    private final String wholeGenerationLabel;

    private final CompoundBorder border;
    private final FlowLayoutCenter layout;
    private final FlowLayout layoutSubPanel;

    private final ConwaysGameOfLiveTab tab;

    private final ConwaysGameOfLifePopulationCensusContainer censusContainer;

    public PopulationStatisticsElementsPanelLifeCycle(
        ConwaysGameOfLiveTab tab,
        ConwaysGameOfLifePopulationCensusContainer censusContainer
    ) {
        super(
            tab.getComputerKurzweilProperties().getConwaysgameoflife().getPopulation().getPanelPopulationStatistics(),
            tab.getComputerKurzweilProperties()
        );
        this.tab = tab;
        this.censusContainer = censusContainer;
        this.layoutSubPanel = new FlowLayout();
        this.setLayout(this.layoutSubPanel);
        this.borderLabel = this.tab.getComputerKurzweilProperties()
            .getConwaysgameoflife().getPopulation().getPanelPopulationStatistics();
        this.layout = new FlowLayoutCenter();
        this.border = this.getDoubleBorder(this.borderLabel);
        this.setLayout(this.layout);
        this.setBorder(this.border);
        ComputerKurzweilProperties.Conwaysgameoflife.Population cfg =
            this.tab.getComputerKurzweilProperties().getConwaysgameoflife().getPopulation();
        initialPopulation = cfg.getInitialPopulation();
        youngCellsLabel = cfg.getYoungCellsLabel();
        youngAndFatCellsLabel = cfg.getYoungAndFatCellsLabel();
        fullAgeCellsLabel = cfg.getFullAgeCellsLabel();
        hungryCellsLabel = cfg.getHungryCellsLabel();
        oldCellsLabel = cfg.getOldCellsLabel();
        wholeGenerationLabel = cfg.getPopulationLabel();
        youngCellsElement = new PopulationStatisticsElement(youngCellsLabel,YOUNG);
        youngAndFatCellsElement = new PopulationStatisticsElement(youngAndFatCellsLabel,YOUNG_AND_FAT);
        fullAgeCellsElement = new PopulationStatisticsElement(fullAgeCellsLabel,FULL_AGE);
        hungryCellsElement = new PopulationStatisticsElement(hungryCellsLabel,HUNGRY);
        oldCellsElement = new PopulationStatisticsElement(oldCellsLabel,OLD);
        wholeGeneration = new PopulationStatisticsElement(wholeGenerationLabel,POPULATION);
        this.add(youngCellsElement);
        this.add(youngAndFatCellsElement);
        this.add(fullAgeCellsElement);
        this.add(hungryCellsElement);
        this.add(oldCellsElement);
        this.add(wholeGeneration);
        update();
    }

    public void update() {
        ConwaysGameOfLivePopulationCensus population = this.censusContainer.peek();
        youngCellsElement.setText(population.getYoungCells());
        youngAndFatCellsElement.setText(population.getYoungAndFatCells());
        fullAgeCellsElement.setText(population.getFullAgeCells());
        hungryCellsElement.setText(population.getHungryCells());
        oldCellsElement.setText(population.getOldCells());
        wholeGeneration.setText(population.getPopulation());
    }

    private CompoundBorder getDoubleBorder(String label){
        int left = this.getProperties().getAllinone().getView().getBorderPaddingX();
        int right = this.getProperties().getAllinone().getView().getBorderPaddingX();
        int top = this.getProperties().getAllinone().getView().getBorderPaddingY();
        int bottom = this.getProperties().getAllinone().getView().getBorderPaddingY();
        return BorderFactory.createCompoundBorder(
            BorderFactory.createEmptyBorder(left,right,top,bottom),
            BorderFactory.createEmptyBorder(left,right,top,bottom)
        );
    }
}
