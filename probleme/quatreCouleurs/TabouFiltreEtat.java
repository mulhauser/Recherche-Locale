package probleme.quatreCouleurs;

import java.util.*;

import generic.SolutionAbstract;
import algo.TabouFiltreAbstract;

/**
 * permet de filtrer par état.
 * 
 * <p>
 * On ne revient pas sur un meme état on pourrait faire mieux avec une méthode
 * génerale... mais on se limite au probleme des 4 couleurs
 * 
 * @author vthomas
 * 
 */
public class TabouFiltreEtat extends TabouFiltreAbstract {

	/**
	 * la liste des solutions passees sur lesquelles on interdit de repasser
	 */
	private ArrayList<SolutionAbstract> taboue;

	/**
	 * construction d'un filtre
	 * 
	 * @param tailleMemoire
	 *            taille de la mémoire du filtre
	 */
	public TabouFiltreEtat(int tailleMemoire) {
		taboue = new ArrayList<SolutionAbstract>();
		initialiserVide(tailleMemoire);
	}

	/**
	 * initialise le filtre: au départ aucun état n'est interdit
	 * 
	 * @param tailleMemoire
	 *            taille de la memoire du filtre
	 */
	private void initialiserVide(int tailleMemoire) {
		for (int i = 0; i < tailleMemoire; i++) {
			taboue.add(null);
		}
	}

	@Override
	/**
	 * méthode accepter: une solution est acceptee si elle n'est pas présente
	 * dans la liste des solutions retenues
	 * 
	 * utilise un operateur d'égalite.
	 */
	public boolean accepter(SolutionAbstract s) {
		// on accepte si la solution n'existe pas deja
		for (SolutionAbstract presenteDansTaboue : this.taboue) {
			if (estEgal(presenteDansTaboue, s)) {
				return (false);
			}
		}
		return true;
	}

	/**
	 * operateur d'egalité return vrai si et seulement si les solutions sont les
	 * memes (si toutes les couleurs sont identiques)
	 * 
	 * @param s1
	 *            solution 1
	 * @param s2
	 *            solution 2
	 * @return vrai si les memes
	 */
	private boolean estEgal(SolutionAbstract s1, SolutionAbstract s2) {
		if (s1 == null)
			return false;

		Solution4Couleurs sol1 = (Solution4Couleurs) s1;
		Solution4Couleurs sol2 = (Solution4Couleurs) s2;
		for (int i = 0; i < sol1.couleurs.length; i++) {
			if (sol1.couleurs[i] != sol2.couleurs[i])
				return (false);
		}
		return (true);
	}

	@Override
	/**
	 * mettre a jour le filtre consiste à ajouter le dernier etat
	 */
	public void update(SolutionAbstract s) {
		taboue.add(s);
		taboue.remove(0);
	}

	/**
	 * affiche des informations du filtre
	 */
	public void afficherInfo() {
		int res = 0;
		for (int i = this.taboue.size() - 1; i > 0; i--) {
			if (this.taboue.get(i) == null) {
				res = i;
				break;
			}
		}

		System.out.println(this.taboue.size() - res);
	}

}
