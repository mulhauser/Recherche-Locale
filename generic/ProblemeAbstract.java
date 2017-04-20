package generic;

/**
 * permet de définir un probleme de manière abstraite
 * <p>
 * un probleme est défini par
 * <li>sa définition de voisinnage
 * <li>sa fonction d'évaluation
 * <p>
 * 
 * @author vthomas
 *
 */
public interface ProblemeAbstract {

	/**
	 * méthode qui retourne une évaluation de la solution donnée
	 * 
	 * @param s
	 *            la solution fournie
	 * @return l'évaluation de cette solution
	 */
	public abstract double evaluation(SolutionAbstract s);

}
