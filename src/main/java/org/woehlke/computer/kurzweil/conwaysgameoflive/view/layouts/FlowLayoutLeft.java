package org.woehlke.computer.kurzweil.conwaysgameoflive.view.layouts;

import java.awt.*;
import java.io.Serializable;

/**
 * &copy; 2006 - 2008 Thomas Woehlke.
 * @author Thomas Woehlke
 *
 * @see <a href="https://github.com/Computer-Kurzweil/conwaysgameoflife">Github Repository</a>
 * @see <a href="https://java.woehlke.org/conwaysgameoflife/">Maven Project Repository</a>
 */
public class FlowLayoutLeft extends FlowLayout implements Serializable {

    static final long serialVersionUID = 242L;

    public FlowLayoutLeft() {
        setAlignment(FlowLayout.LEFT);
    }
}
