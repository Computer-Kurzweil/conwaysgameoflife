package org.woehlke.computer.kurzweil.conwaysgameoflive.view.tabs;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.Serializable;


/**
 * &copy; 2006 - 2008 Thomas Woehlke.
 * @author Thomas Woehlke
 *
 * @see <a href="https://github.com/Computer-Kurzweil/conwaysgameoflife">Github Repository</a>
 * @see <a href="https://java.woehlke.org/conwaysgameoflife/">Maven Project Repository</a>
 */
@Log4j2
@Getter
@ToString
@EqualsAndHashCode(callSuper=false)
public abstract class TabPanel extends JPanel implements ActionListener, Serializable {
    static final long serialVersionUID = 242L;
}
