package algo;

import generic.AlgorithmeAbstract;
import generic.ProblemeAbstract;
import generic.SolutionAbstract;

import java.util.List;
import java.util.Random;

/**
 * classe recuit simulé
 * <p>
 * herite de Algrotihme, à redefinir
 * <p>
 * attribut temperature
 * <p>
 * iteration doit - recuperer le voisinage - choisir au hasard un point - tirer
 * un nombre aleatoire - faire évoluer la temperature
 *
 * @author vthomas
 */
public class RecuitSimule extends AlgorithmeAbstract {

    /**
     * variation de temperature par iteration
     */
    public double DELTA_TEMPERATURE = 0.99;

    /**
     * la temperature qui décroit au cours du temps;
     */
    double temperature;

    /**
     * valeur de la solution en cours
     */
    double valeurActuelleSolution = 0;

    /**
     * constructeur
     *
     * @param probleme         probleme à resoudre
     * @param solutionInitiale solution initiale fournie
     * @param temperature      temperature initiale
     */
    public RecuitSimule(ProblemeAbstract probleme, SolutionAbstract solutionInitiale, double temperature) {
        super(probleme, solutionInitiale);
        this.temperature = temperature;
    }

    @Override
    /**
     * lance une iteration.
     * <p>
     * A completer en utilisant les méthodes
     * <ul>
     * <li>choisirHasard
     * <li>estAccepte
     * </ul>
     */
    public boolean ameliorerSolution() {
        miseAJourTemperature();
        // a completer en utilisant les méthodes
        // * choisirHasard
        // * estAccepte


        // on limite le nombre de tirages non accepte a un nombre maximal (1000)

        // on ne s'arrete jamais

        //throw new Error("TODO"); // TODO  a completer
        boolean fin = false;
        int tour = 0;
        SolutionAbstract solution;
        while (!fin && tour < 1000) {
            solution = choisirHasard(solutionEnCours.retourneVoisinage());
            if (estAcceptee(solution)) {
                fin = true;
                solutionEnCours = solution;
                // Pour le probleme du TSP sinon on ne fait pas assez d'itération
                //tour++;
            } else {
                // Pour le probleme des couleurs
                //tour++;
            }
        }
        return !fin;
    }

    private SolutionAbstract choisirHasard(List<SolutionAbstract> voisins) {
        Random rand = new Random();
        int index = rand.nextInt(voisins.size());
        return voisins.get(index);
    }

    private void miseAJourTemperature() {
        this.temperature = this.temperature * DELTA_TEMPERATURE;
    }



    /**
     * méthode stochastique pour savoir si une solution est validée
     *
     * @param solution solution à comparer à la solution actuelle
     * @return booleen qui valide ou non
     */
    private boolean estAcceptee(SolutionAbstract solution) {

        // a completer en utilisant probaMetropolis

        //throw new Error("TODO"); // TODO
        boolean res = false;
        double rand = Math.random();

        if (problemeATraiter.evaluation(solution) - valeur() < 0)
            return true;

        double proba = probaMetropolis(problemeATraiter.evaluation(solution) - valeur());
        if (rand < proba)
            res = true;

        return res;
    }

    /**
     * retourne la proba d'acceptation en fonction de la difference de valeur et
     * de la temperature
     *
     * @param deltaValeur difference de valeur entre solution courante et la nouvelle
     *                    solution
     * @return probabilite d'accepter la nouvelle solution
     */
    private double probaMetropolis(double deltaValeur) {

        //throw new Error("TODO"); // TODO  a completer
        return Math.exp(-deltaValeur / this.temperature);
    }

    /**
     * par defaut on affiche aussi la temperature
     */
    public String log() {
        return super.log() + "; T;" + temperature;
    }

}
