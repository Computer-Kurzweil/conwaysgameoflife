package org.woehlke.computer.kurzweil.conwaysgameoflive.view.widgets;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.woehlke.computer.kurzweil.conwaysgameoflive.config.ComputerKurzweilProperties;
import org.woehlke.computer.kurzweil.conwaysgameoflive.view.layouts.FlowLayoutCenter;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
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
public class PanelWithTitleBorder extends JPanel implements Serializable {

    private static final long serialVersionUID = 242L;

    private final FlowLayoutCenter panelStartStopButtonsLayout;
    private final CompoundBorder panelStartStopButtonsBorder;

    public PanelWithTitleBorder(String label, ComputerKurzweilProperties p) {
        panelStartStopButtonsLayout = new FlowLayoutCenter();
        int top = p.getAllinone().getView().getBorderPaddingY();
        int left = p.getAllinone().getView().getBorderPaddingX();
        int bottom = p.getAllinone().getView().getBorderPaddingY();
        int right = p.getAllinone().getView().getBorderPaddingX();
        panelStartStopButtonsBorder = BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(label),
            BorderFactory.createEmptyBorder(top,left,bottom,right)
        );
        this.setLayout(panelStartStopButtonsLayout);
        this.setBorder(panelStartStopButtonsBorder);
    }
}
