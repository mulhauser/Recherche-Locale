package algo;

import generic.SolutionAbstract;

/**
 * classe Abstraite qui a pour but de gérer le filtrage avec une liste taboue
 * 
 * @author vthomas
 *
 */
public abstract class TabouFiltreAbstract {

	/**
	 * methode qui retourne la liste des solutions à retenir
	 * 
	 * @param s
	 *            la solution a tester
	 * @return acceptation ou non
	 */
	public abstract boolean accepter(SolutionAbstract s);

	/**
	 * methode qui met à jour les criteres taboue en fonction de la nouvelle
	 * solution retenue
	 * 
	 * @param s
	 *            la solution retenue
	 * 
	 */
	public abstract void update(SolutionAbstract s);

	/**
	 * affiche de l'information sur le filtre
	 */
	public abstract void afficherInfo();

}
