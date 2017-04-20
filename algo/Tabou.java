package algo;

import generic.AlgorithmeAbstract;
import generic.ProblemeAbstract;
import generic.SolutionAbstract;

import java.util.List;

/**
 * recherche taboue se fonde sur une liste taboue mise à jour a chque nouvelle
 * acceptation
 * <p>
 * principe de la méthode pour chaque iteration
 * <ul>
 * <li>recuperer les voisins
 * <li>pour chaque solution, regarder si elle est valide grace à la liste taboue
 * <li>prendre la meilleure solution valide
 * <li>mettre à jour la liste taboue
 * </ul>
 * 
 * @author vthomas
 * 
 */
public class Tabou extends AlgorithmeAbstract {

	/**
	 * la liste taboue (definir une classe concrete)
	 */
	TabouFiltreAbstract tabou;

	/**
	 * constructeur
	 * 
	 * @param probleme
	 *            probleme à resoudre
	 * @param solutionInitiale
	 *            solution initiale
	 * @param taboue
	 *            liste taboue utilisee pour la recherche taboue
	 */
	public Tabou(ProblemeAbstract probleme, SolutionAbstract solutionInitiale, TabouFiltreAbstract taboue) {
		super(probleme, solutionInitiale);
		this.tabou = taboue;
	}

	@Override
	/**
	 * permet d'ameliorer la solution courante en utilisant une recherche taboue
	 */
	public boolean ameliorerSolution() {

		// a chaque iteration
		// on recupere les voisins et on filtre par le filtre tabou

		// on levera une Error en cas d'impossibilité (quand la liste taboue
		// bloque la recherche de la solution)

		// penser à utiliser
		// 1) la méthode filtrer qui retourne la liste des solutions à conserver
		// 2) la methode chercherMeilleureSolution qui retourne la
		// meilleure solution à partir d'une liste de solution

		//throw new Error("TODO"); // TODO  a completer

        boolean fin = false;
        List<SolutionAbstract> solution;
        SolutionAbstract sol;
        while(!fin){
            solution = solutionEnCours.retourneVoisinage();
            filtrer(solution);
            sol = chercherMeilleureSolution(solution);


        }

        return fin;
	}

	/**
	 * trouve la meilleure solution dans une liste de solutions
	 * 
	 * @param solutionsVoisines
	 *            solutions dans laquelle chercher la meilleure
	 * 
	 * @return meilleure solution
	 */
	private SolutionAbstract chercherMeilleureSolution(List<SolutionAbstract> solutionsVoisines) {

		//throw new Error("TODO"); // TODO  a completer etudiants
        SolutionAbstract solution = null;
        double max = 0.0;
        for(SolutionAbstract sol : solutionsVoisines){
            if(max < problemeATraiter.evaluation(sol)){
                max = problemeATraiter.evaluation(sol);
                solution = sol;
            }
        }
        return solution;
	}

	/**
	 * utilise le filtre taboue pour retirer les solutions non considérées dans
	 * la liste
	 * 
	 * @param solutionsVoisines
	 *            liste de solutions à filtrer, parametre modifié par la méthode
	 */

	private void filtrer(List<SolutionAbstract> solutionsVoisines) {

		//throw new Error("TODO"); // TODO  a completer
        List<SolutionAbstract> listeIntermediaire = solutionsVoisines;
        for(SolutionAbstract sol : listeIntermediaire){
            if(tabou.accepter(sol)){
                tabou.update(sol);
            }else{
                solutionsVoisines.remove(sol);
            }
        }
	}

}
