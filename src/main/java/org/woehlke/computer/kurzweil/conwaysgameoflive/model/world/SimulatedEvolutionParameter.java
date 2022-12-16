package org.woehlke.computer.kurzweil.conwaysgameoflive.model.world;

import lombok.*;

import java.io.Serializable;

/**
 * &copy; 2006 - 2008 Thomas Woehlke.
 * @author Thomas Woehlke
 *
 * @see <a href="https://github.com/Computer-Kurzweil/conwaysgameoflife">Github Repository</a>
 * @see <a href="https://java.woehlke.org/conwaysgameoflife/">Maven Project Repository</a>
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class SimulatedEvolutionParameter implements Serializable {

    private static final long serialVersionUID = 242L;

    private int foodPerDay;
    private int foodPerDayGardenOfEden;
    private boolean gardenOfEdenEnabled;

    public void increaseFoodPerDay() {
        this.foodPerDay++;
    }

    public void decreaseFoodPerDay() {
        this.foodPerDay--;
    }

    public void toggleGardenOfEden() {
      this.gardenOfEdenEnabled = ! this.gardenOfEdenEnabled;
    }

}
