package probleme.TSP;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import generic.AlgorithmeAbstract;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import algo.Greedy;
import algo.RecuitSimule;

@SuppressWarnings("serial")
public class AfficheTSP extends JPanel {

	/**
	 * permet de gerer l'affichage
	 */
	private static final int DECALAGE = 100;

	/**
	 * le probleme à afficher
	 */
	TSP problemeAAfficher;

	/**
	 * la solution courante a afficher
	 */
	SolutionTSP solutionCourante;

	/**
	 * lien vers les algorithmes utilisés par le controleur
	 */
	AlgorithmeAbstract greedy;
	RecuitSimule recuitSimule;
	AlgorithmeAbstract taboue;
	AlgorithmeAbstract taboueAttribut;

	/**
	 * construit un afficheur de TSP
	 * 
	 * @param probleme
	 *            probleme TSP
	 * @param solutionInitiale
	 *            solution de depart
	 */
	public AfficheTSP(TSP probleme, SolutionTSP solutionInitiale) {
		this.problemeAAfficher = probleme;
		this.solutionCourante = solutionInitiale;

		// Jpanel
		JPanel contenu = new JPanel();
		contenu.setLayout(new BorderLayout());
		contenu.add(this, BorderLayout.CENTER);

		// JButton pour greedy
		JPanel panelBoutons = new JPanel();
		panelBoutons.setLayout(new BorderLayout());
		JButton boutonGreedy = creerBoutonGreedy();
		panelBoutons.add(boutonGreedy, BorderLayout.NORTH);
		JButton boutonRecuit = creerBoutonRecuit();
		panelBoutons.add(boutonRecuit, BorderLayout.CENTER);
		contenu.add(panelBoutons, BorderLayout.SOUTH);

		// construction JFrame
		JFrame f = new JFrame();
		f.setContentPane(contenu);
		int tailleFenetre = problemeAAfficher.taillePlan + 2 * DECALAGE;
		Dimension dimensions = new Dimension(tailleFenetre, tailleFenetre);
		this.setPreferredSize(dimensions);
		f.setVisible(true);
		f.pack();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * creer le bouton pour l'algo greedy
	 * 
	 * @return bouton algo greedy
	 */
	private JButton creerBoutonGreedy() {
		// creation de l'algorithme
		this.greedy = new Greedy(problemeAAfficher, solutionCourante);
		// creation du bouton
		JButton boutonGreedy = new JButton("greedy");
		boutonGreedy.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				greedy.ameliorerSolution();
				solutionCourante = (SolutionTSP) greedy.getSolutionEnCours();
				System.out.println(greedy.log());
				repaint();
			}
		});
		return boutonGreedy;
	}

	/**
	 * creer bouton qui lance algo recuit
	 * 
	 * @return bouton
	 */
	private JButton creerBoutonRecuit() {
		final int nbIteration = 100;
		String nombouton = "recuit simule (*" + nbIteration + ")";
		JButton boutonRecuit = new JButton(nombouton);
		this.recuitSimule = new RecuitSimule(problemeAAfficher, solutionCourante, 10000);
		this.recuitSimule.DELTA_TEMPERATURE = 0.999;
		boutonRecuit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				for (int i = 0; i < nbIteration; i++) {
					recuitSimule.ameliorerSolution();
				}
				solutionCourante = (SolutionTSP) recuitSimule.getSolutionEnCours();
				System.out.println(recuitSimule.log());
				repaint();
			}
		});
		return boutonRecuit;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		// on dessine le chemin
		this.dessinChemins(g);
		// on dessine les ville
		this.dessinVilles(g);

	}

	/**
	 * represente le dessin du chemin correspondant à la solution courante
	 * 
	 * @param g
	 *            graphics sur lequel dessiner
	 */
	private void dessinChemins(Graphics g) {
		g.setColor(Color.blue);
		ArrayList<Ville> villesSolution = this.solutionCourante.listeVilles;
		Ville depart = villesSolution.get(villesSolution.size() - 1);
		// trace les arcs en suivant les villes
		for (int i = 0; i < villesSolution.size(); i++) {
			Ville arrivee = villesSolution.get(i);
			g.drawLine(depart.x + DECALAGE, depart.y + DECALAGE, arrivee.x + DECALAGE, arrivee.y + DECALAGE);
			depart = arrivee;
		}
	}

	/**
	 * dessine les villes associées au probleme
	 * 
	 * @param g
	 *            graphics sur lequel dessiner
	 */
	private void dessinVilles(Graphics g) {
		g.setColor(Color.black);
		for (int i = 0; i < problemeAAfficher.nombreVilles; i++) {
			Ville v = problemeAAfficher.listeVilles.get(i);
			int TAILLEVILLE = 4;
			int DEMI = TAILLEVILLE / 2;
			g.fillOval(v.x - DEMI + DECALAGE, v.y - DEMI + DECALAGE, TAILLEVILLE, TAILLEVILLE);
		}
	}

}
