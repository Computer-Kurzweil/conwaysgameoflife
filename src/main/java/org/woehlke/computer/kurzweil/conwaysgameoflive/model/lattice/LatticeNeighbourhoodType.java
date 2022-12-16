package org.woehlke.computer.kurzweil.conwaysgameoflive.model.lattice;

/**
 * &copy; 2006 - 2008 Thomas Woehlke.
 * @author Thomas Woehlke
 *
 * @see <a href="https://github.com/Computer-Kurzweil/conwaysgameoflife">Github Repository</a>
 * @see <a href="https://java.woehlke.org/conwaysgameoflife/">Maven Project Repository</a>
 *
 * @see <a href="https://en.wikipedia.org/wiki/Von_Neumann_neighborhood">wikipedia: Von Neumann neighborhood</a>
 * @see <a href="https://en.wikipedia.org/wiki/Moore_neighborhood">wikipedia: Moore neighborhood</a>
 */
public enum LatticeNeighbourhoodType {

    /**
     * @see <a href="https://en.wikipedia.org/wiki/Von_Neumann_neighborhood">wikipedia: Von_Neumann_neighborhood</a>
     */
    VON_NEUMANN_NEIGHBORHOOD,

    /**
     * @see <a href="https://en.wikipedia.org/wiki/Moore_neighborhood">wikipedia: Moore_neighborhood</a>
     */
    MOORE_NEIGHBORHOOD,

    WOEHLKE_NEIGHBORHOOD;
}
