package generic;

import java.util.List;

/**
 * solution abstraite depend du probleme traité
 *
 */

public abstract class SolutionAbstract {

	/**
	 * permet de construire l'ensemble des solutions voisines de la colution
	 * courante
	 * 
	 * @return ensemble solutions voisines de this
	 */
	public abstract List<SolutionAbstract> retourneVoisinage();

}
