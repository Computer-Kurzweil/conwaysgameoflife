package org.woehlke.computer.kurzweil.conwaysgameoflive.model.census;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.woehlke.computer.kurzweil.conwaysgameoflive.config.ComputerKurzweilProperties;

import java.io.Serializable;
import java.util.Stack;

/**
 * Holds Data how many Cells per LifeCycleStatus and how many Cells in the whole Population for a Stack of Generations
 *
 * &copy; 2006 - 2008 Thomas Woehlke.
 * @author Thomas Woehlke
 *
 * @see <a href="https://github.com/Computer-Kurzweil/conwaysgameoflife">Github Repository</a>
 * @see <a href="https://java.woehlke.org/conwaysgameoflife/">Maven Project Repository</a>
 */
@Log4j2
@ToString(callSuper = true, exclude={"statistics"})
@EqualsAndHashCode(exclude={"statistics"})
public class ConwaysGameOfLifePopulationCensusContainer implements Serializable {

    static final long serialVersionUID = 242L;

    private final int queueMaxLength;

    private final Stack<ConwaysGameOfLivePopulationCensus> statistics =
        new Stack<>();

    private volatile ConwaysGameOfLivePopulationCensus currentPopulationCensus;

    @Getter
    private volatile long worldIteration;

    public ConwaysGameOfLifePopulationCensusContainer(
        ComputerKurzweilProperties p
    ) {
        this.queueMaxLength = p.getSimulatedevolution().getControl().getQueueMaxLength();
        this.worldIteration = 0L;
    }

    public synchronized void push(ConwaysGameOfLivePopulationCensus populationCensus) {
        this.currentPopulationCensus = populationCensus;
        this.worldIteration++;
        populationCensus.setWorldIteration(worldIteration);
        statistics.push(populationCensus);
        if (statistics.size() > queueMaxLength) {
            statistics.removeElementAt(0);
        }
        log.info(worldIteration + " : " + populationCensus.toString());
    }

    public synchronized ConwaysGameOfLivePopulationCensus peek() {
        if(null == this.currentPopulationCensus) {
            ConwaysGameOfLivePopulationCensus populationCensus = new ConwaysGameOfLivePopulationCensus();
            populationCensus.setWorldIteration(worldIteration);
            statistics.push(populationCensus);
        }
        return this.currentPopulationCensus;
    }
}
