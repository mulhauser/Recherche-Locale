package algo;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import generic.AlgorithmeAbstract;
import generic.ProblemeAbstract;
import generic.SolutionAbstract;
import probleme.quatreCouleurs.Solution4Couleurs;

/**
 * classe representant un algorithme de type greedy
 * 
 * @author vthomas
 *
 */
public class Greedy extends AlgorithmeAbstract {

	/**
	 * constructeur d'un algorithme greedy
	 * 
	 * @param probleme
	 *            le probleme a resoudre
	 * @param initiale
	 *            la solution initiale a modifier
	 */
	public Greedy(ProblemeAbstract probleme, SolutionAbstract initiale) {
		super(probleme, initiale);
	}

	/**
	 * permet d'améliorer la solution selon une approche gloutonne
	 */
	public boolean ameliorerSolution() {

		//throw new Error("TODO"); // TODO  a ecrire etudiant

		List<SolutionAbstract> voisins = solutionEnCours.retourneVoisinage();
		boolean res = true;
		double max = problemeATraiter.evaluation(solutionEnCours);
		for(SolutionAbstract a : voisins){
		    double solV = problemeATraiter.evaluation(a);
            if(max > solV){
                solutionEnCours = a;
                res = false;
            }
        }
        return res;
		// on s'arrete si la solution ne s'ameliore plus
	}

}
