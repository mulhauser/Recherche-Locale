package probleme.quatreCouleurs;

import java.util.*;

import generic.SolutionAbstract;
import algo.TabouFiltreAbstract;

/**
 * permet de filtrer les solutions a considerer selon un attribut
 * <p>
 * Ici, pour le probleme des 4 couleurs il s'agit de numero de zones dont on
 * interdit le changement
 * 
 * @author vthomas
 *
 */
public class TabouFiltreAttribut extends TabouFiltreAbstract {

	/**
	 * la dernière solution qui a été acceptée
	 */
	public Solution4Couleurs derniereAcceptee = null;

	/**
	 * on interdit de toucher aux n dernieres zones modifiées dans le passé
	 */
	public ArrayList<Integer> zonesRefusees;

	/**
	 * constructeur d'une liste taboue (basée sur des nums de zone)
	 * 
	 * @param taille
	 *            taille de la liste (taille de la mémoire du passé)
	 */
	public TabouFiltreAttribut(int taille) {
		zonesRefusees = new ArrayList<Integer>();
		for (int i = 0; i < taille; i++) {
			zonesRefusees.add(-1);
		}
	}

	@Override
	/**
	 * permet de vérifier si on accepte la solution s
	 * <p>
	 * En fonction de
	 * <li>la solution s
	 * <li>l'ancienne solution (pour determiner les différences)
	 * <li>la liste des zones interdites
	 * <p>
	 * 
	 * @param s
	 *            solution a tester
	 * 
	 * @return true si et seulement si la solution est acceptable selon la liste
	 *         taboue
	 */
	public boolean accepter(SolutionAbstract s) {
		// si on etait bloquée la fois précédente, alors on accepte celle-ci
		if (this.derniereAcceptee == null) {
			return (true);
		}

		// sinon il faut utiliser le filtre pour savoir si la solution est
		// acceptee

		// vous utiliserez la méthode chercherDifferente qui retourne la zone
		// modifiee depuis la solution précédente

		throw new Error("TODO"); // TODO  a completer

	}

	@Override
	/**
	 * a pour objectif de mettre à jour le filtre
	 * <p>
	 * Pour ce filtre, il s'agit d'ajouter la zone modifiée dans les zones
	 * interdites
	 */
	public void update(SolutionAbstract s) {
		// penser à mettre à jour la derniere solution acceptee pour la
		// prochaine utilisation du filtre

		throw new Error("TODO"); // TODO  a completer

	}

	/**
	 * on chercher la zone dont la couleur a été modifiée par rapport à la
	 * derniere solution acceptee
	 * 
	 * @param solution
	 *            la solution dont on cherche la modification
	 * 
	 * @return le numero de la zone qui a été modifiée
	 */
	private int chercherDifferente(Solution4Couleurs solution) {
		int numcouleur = -1;
		// si la couleur de la zone a été modifiee
		for (int i = 0; i < solution.couleurs.length; i++) {
			if (solution.couleurs[i] != this.derniereAcceptee.couleurs[i]) {
				// c'est que c'est la zone recherchee
				numcouleur = i;
				break;
			}
		}
		return numcouleur;
	}

	@Override
	public void afficherInfo() {
		int res = 0;
		for (int i = this.zonesRefusees.size() - 1; i > 0; i--) {
			if (this.zonesRefusees.get(i).equals(-1)) {
				res = i;
				break;
			}
		}
		System.out.println("taboue attributive");
		System.out.println(this.zonesRefusees.size() - res);
	}

}
