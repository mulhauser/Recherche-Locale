package probleme.TSP;

import java.util.ArrayList;

import generic.*;

/**
 * probleme du TSP
 * 
 * @author vthomas
 * 
 */
public class TSP implements ProblemeAbstract {

	/**
	 * nombre de villes du TSP
	 */
	public int nombreVilles;

	/**
	 * taille de la grille dans laquelle sont les villes
	 */
	public int taillePlan;

	/**
	 * liste des villes
	 */
	public ArrayList<Ville> listeVilles;

	/**
	 * créé un probleme de TSP
	 * 
	 * @param n
	 *            nombre de noeuds
	 * @param d
	 *            dimension de la grille
	 */
	public TSP(int n, int d) {
		this.nombreVilles = n;
		this.taillePlan = d;

		this.listeVilles = new ArrayList<Ville>();
		for (int i = 0; i < n; i++)
			this.listeVilles.add(new Ville(d, i));
	}

	@Override
	/**
	 * permet d'évaluer une solution
	 */
	public double evaluation(SolutionAbstract s) {
		// teste la solution
		SolutionTSP solutionTSP = (SolutionTSP) s;
		return solutionTSP.calculerDistanceChemin();
	}

}
