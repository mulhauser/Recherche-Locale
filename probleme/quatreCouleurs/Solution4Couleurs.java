package probleme.quatreCouleurs;

import java.util.ArrayList;
import java.util.List;

import generic.SolutionAbstract;

/**
 * solution au probleme de 4 couleurs
 * 
 * @author vthomas
 * 
 */
public class Solution4Couleurs extends SolutionAbstract {

	/**
	 * le tableau de couleurs qui associe a chaque zone une couleur
	 */
	int[] couleurs;

	/**
	 * construit une solution aleatoire
	 * 
	 * @param pb2
	 *            probleme associe
	 */
	public Solution4Couleurs(Probleme4Couleurs pb) {
		couleurs = new int[pb.nombreDeZones];
		for (int i = 0; i < couleurs.length; i++)
			couleurs[i] = (int) (Math.random() * 4);
	}

	/**
	 * construit une solution par copie
	 * 
	 * @param s
	 *            autre solution
	 */
	public Solution4Couleurs(Solution4Couleurs s) {
		couleurs = (int[]) s.couleurs.clone();
	}

	/**
	 * permet de modifier une solution en changeant la couleur d'une zone
	 * 
	 * @param n
	 *            zone dont on modifie la couleur
	 * @param c
	 *            nouvelle couleur
	 */
	public void modifieCouleur(int n, int c) {
		// si parametre pas bon
		if (c < 0 || c > 4 || n < 0 || n > couleurs.length - 1)
			throw new IllegalArgumentException("c:" + c + " n:" + n);

		// sinon,
		couleurs[n] = c;
	}

	/**
	 * permet de vérifier si deux zones possèdent la meme couleur
	 * 
	 * @param i
	 *            num de la zone 1
	 * @param j
	 *            num de la zone 2
	 * @return vrai si et seulement si la zone i et la zone j ont la meme
	 *         couleur
	 */
	boolean memeCouleur(int i, int j) {
		return couleurs[i] == couleurs[j];
	}

	/**
	 * methode qui surcharge l'obtention d'un voisinage
	 * <p>
	 * consiste simplement à retourner la liste avec une seule variation de
	 * couleur par rapport à la solution initiale.
	 * <p>
	 * soit au total 3 fois nombre de zones (on choisit une zone et une couleur
	 * différentes).
	 * 
	 * @return la liste des solutions voisines obtenues en changeant une couleur
	 *         dans une zone
	 */
	@Override
	public List<SolutionAbstract> retourneVoisinage() {

		List<SolutionAbstract> solutionsVoisines = new ArrayList<SolutionAbstract>();
		// construction des solutions voisines
		// pour chaque zone
		for (int i = 0; i < couleurs.length; i++) {
			int couleurZoneCourante = this.couleurs[i];
			// on peut modifier la couleur
			for (int nouvelleCouleur = 0; nouvelleCouleur < 4; nouvelleCouleur++) {
				// si il y a vraiment un changement
				if (nouvelleCouleur != couleurZoneCourante) {
					// on cree une nouvelle solution
					Solution4Couleurs nouvelleSolution;
					nouvelleSolution = new Solution4Couleurs(this);
					// on l'ajoute à l'ensemble des voisins
					nouvelleSolution.modifieCouleur(i, nouvelleCouleur);
					solutionsVoisines.add(nouvelleSolution);
				}
			}
		}
		return solutionsVoisines;
	}

}
