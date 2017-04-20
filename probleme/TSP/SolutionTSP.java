package probleme.TSP;

import generic.SolutionAbstract;

import java.util.ArrayList;
import java.util.List;

/**
 * une solution au TSP
 * <p>
 * correspond à une permutation sur l'ensemble des villes
 * 
 * @author vthomas
 *
 */
public class SolutionTSP extends SolutionAbstract {

	/**
	 * la solution est une liste de villes
	 */
	public ArrayList<Ville> listeVilles;

	/**
	 * lien vers le probleme à résoudre
	 */
	TSP probleme;

	/**
	 * constructeur solution de base juste le trajet
	 * 
	 * @param tsp
	 *            probleme donne
	 */
	public SolutionTSP(TSP tsp) {
		probleme = tsp;
		// initialise solution en prenant villes dans l'ordre
		listeVilles = (ArrayList<Ville>) tsp.listeVilles.clone();
	}

	/**
	 * constructeur par copie
	 * 
	 * @param s
	 *            solution dont on construit une copie
	 */
	public SolutionTSP(SolutionTSP s) {
		// clone la suite
		listeVilles = (ArrayList<Ville>) (s.listeVilles.clone());
	}

	/**
	 * methode qui calcule la longueur du chemin de la solution
	 * 
	 * @return la distance du chemin obtenue en suivant la solution
	 */
	public double calculerDistanceChemin() {
		double res = 0;
		// vérifie la bonne solution
		assert (!villesToutesDifferentesInvariant()) : "solution non conforme";

		// calcul des evaluations
		for (int i = 0; i < listeVilles.size() - 1; i++) {
			res = res + listeVilles.get(i).calculerDistance(listeVilles.get(i + 1));
		}
		res = res + listeVilles.get(0).calculerDistance(listeVilles.get(listeVilles.size() - 1));
		return (res);
	}

	/**
	 * methode de verification de solution.
	 * <p>
	 * verifie que les villes n'ont pas été dupliquées (on passe pas deux fois
	 * par la meme ville)
	 * 
	 * @return true si solution valide
	 */
	public boolean villesToutesDifferentesInvariant() {
		// taille
		assert listeVilles.size() == probleme.listeVilles.size() : "pas bonne taille de solution";

		// pas de duplication de villes
		for (int i = 0; i < listeVilles.size(); i++)
			for (int j = i + 1; j < listeVilles.size(); j++)
				assert listeVilles.get(i) != listeVilles.get(j);
		return (true);

	}

	/**
	 * permet d'inverser deux villes dans la suite de villes. Permet de définir
	 * l'opérateur de voisinage
	 * 
	 * @param i
	 *            indice ville 1 a inverser
	 * @param j
	 *            indice ville 2 a inverser
	 */
	public void inverse(int i, int j) {
		Ville temp = listeVilles.get(i);
		listeVilles.set(i, listeVilles.get(j));
		listeVilles.set(j, temp);
	}

	/**
	 * tostring
	 */
	public String toString() {
		String res = "";
		res += listeVilles;
		return (res);
	}

	@Override
	/**
	 * retourne les solutions voisines de la solution courante.
	 * <p>
	 * il s'agit des chemins construits en inversant deux villes de la solution
	 * courante
	 */
	public List<SolutionAbstract> retourneVoisinage() {

		throw new Error("TODO"); // TODO  a construire

	}

}
