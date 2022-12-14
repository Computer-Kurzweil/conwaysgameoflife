package org.woehlke.computer.kurzweil.conwaysgameoflive.model.census;

import lombok.*;
import lombok.extern.log4j.Log4j2;
import org.woehlke.computer.kurzweil.conwaysgameoflive.model.cell.LifeCycleStatus;

import java.io.Serializable;

/**
 * Holds Data how many Cells per LifeCycleStatus and how many Cells in the whole Population for this Generation.
 *
 * &copy; 2006 - 2008 Thomas Woehlke.
 * @author Thomas Woehlke
 *
 * @see <a href="https://github.com/Computer-Kurzweil/conwaysgameoflife">Github Repository</a>
 * @see <a href="https://java.woehlke.org/conwaysgameoflife/">Maven Project Repository</a>
 */
@Log4j2
@ToString
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class ConwaysGameOfLivePopulationCensus implements Serializable {

    static final long serialVersionUID = 242L;

   private int youngCells;
   private int youngAndFatCells;
   private int fullAgeCells;
   private int hungryCells;
   private int oldCells;
   private int deadCells;
   private int population;
   private long generationYoungest;
   private long generationOldest;
   private long worldIteration;

  /**
   * TODO write doc.
   */
  public void countStatusOfOneCell(LifeCycleStatus status) {
    population++;
    switch (status) {
      case YOUNG:
        youngCells++;
        break;
      case YOUNG_AND_FAT:
        youngAndFatCells++;
        break;
      case FULL_AGE:
        fullAgeCells++;
        break;
      case HUNGRY:
        hungryCells++;
        break;
      case OLD:
        oldCells++;
        break;
      case DEAD:
        deadCells++;
        break;
    }
  }

}
