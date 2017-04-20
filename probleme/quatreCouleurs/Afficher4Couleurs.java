package probleme.quatreCouleurs;

import generic.AlgorithmeAbstract;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import algo.Greedy;
import algo.RecuitSimule;
import algo.Tabou;
import algo.TabouFiltreAbstract;

/**
 * permet d'afficher un probleme 4 couleurs
 * 
 * @author vthomas
 * 
 */

@SuppressWarnings("serial")
public class Afficher4Couleurs extends JPanel {

	// tableau de couleur
	public static final Color[] CONSTANTES_COULEURS = { Color.yellow,
			Color.blue, Color.green, Color.red };
	// taille des carrés
	static final int TAILLE_CASE = 30;

	// le probleme à afficher
	Probleme4Couleurs problemeAAfficher;
	// la solution
	Solution4Couleurs solutionCourante;

	// lien vers les algorithmes
	AlgorithmeAbstract greedy;
	AlgorithmeAbstract recuitSimule;
	AlgorithmeAbstract taboue;
	AlgorithmeAbstract taboueAttribut;

	/**
	 * constructeur
	 * 
	 * @param pb2
	 *            solution associée au probleme
	 */
	public Afficher4Couleurs(Probleme4Couleurs probleme,
			Solution4Couleurs solutionAfficher) {
		this.solutionCourante = solutionAfficher;
		this.problemeAAfficher = probleme;

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
		JButton boutonTabou = creerBoutonTaboueAttribut();
		panelBoutons.add(boutonTabou, BorderLayout.SOUTH);
		contenu.add(panelBoutons, BorderLayout.SOUTH);

		// construction JFrame
		JFrame f = new JFrame();
		f.setContentPane(contenu);
		this.setPreferredSize(new Dimension(
				problemeAAfficher.zonesAColorier.length * TAILLE_CASE,
				problemeAAfficher.zonesAColorier[0].length * TAILLE_CASE));
		f.setVisible(true);
		f.pack();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// ajoute un Listener
		this.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// recupere la zone cliquée
				int i = arg0.getX() / TAILLE_CASE;
				int j = arg0.getY() / TAILLE_CASE;
				// recupere le numero de zone
				int n = problemeAAfficher.zonesAColorier[i][j];
				// modifie la valeur de la solution
				solutionCourante.modifieCouleur(n,
						(solutionCourante.couleurs[n] + 1) % 4);
				System.out.println(problemeAAfficher
						.evaluation(solutionCourante));
				repaint();
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
	}

	private JButton creerBoutonRecuit() {

		final int nbIteration = 100;
		JButton boutonRecuit = new JButton("recuit simule (*" + nbIteration
				+ ")");
		this.recuitSimule = new RecuitSimule(problemeAAfficher,
				solutionCourante, 1000);
		boutonRecuit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				for (int i = 0; i < nbIteration; i++) {
					recuitSimule.ameliorerSolution();
				}
				solutionCourante = (Solution4Couleurs) recuitSimule
						.getSolutionEnCours();
				System.out.println(recuitSimule.log());
				repaint();
			}
		});
		return boutonRecuit;
	}

	private JButton creerBoutonTaboue() {
		JButton boutonTabou = new JButton("taboue etat");
		TabouFiltreEtat filtre = new TabouFiltreEtat(1000);
		this.taboue = new Tabou(problemeAAfficher, solutionCourante, filtre);
		boutonTabou.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				taboue.ameliorerSolution();
				solutionCourante = (Solution4Couleurs) taboue
						.getSolutionEnCours();
				System.out.println(taboue.log());
				repaint();
			}
		});
		return boutonTabou;
	}
	
	private JButton creerBoutonTaboueAttribut() {
		JButton boutonTabouAttribut = new JButton("taboue attribut");
		TabouFiltreAbstract filtre = new TabouFiltreAttribut(100);
		this.taboueAttribut = new Tabou(problemeAAfficher, solutionCourante, filtre);
		boutonTabouAttribut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				taboueAttribut.ameliorerSolution();
				solutionCourante = (Solution4Couleurs) taboueAttribut
						.getSolutionEnCours();
				System.out.println(taboueAttribut.log());
				repaint();
			}
		});
		return boutonTabouAttribut;
	}

	private JButton creerBoutonGreedy() {
		// creation de l'algorithme
		this.greedy = new Greedy(problemeAAfficher, solutionCourante);

		// creation du bouton
		JButton boutonGreedy = new JButton("greedy");
		boutonGreedy.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				greedy.ameliorerSolution();
				solutionCourante = (Solution4Couleurs) greedy
						.getSolutionEnCours();
				System.out.println(greedy.log());
				repaint();
			}
		});
		return boutonGreedy;
	}

	/**
	 * surcharge de la méthode paint
	 * 
	 * 
	 */
	public void paint(Graphics g) {
		g.drawRect(0, 0, problemeAAfficher.zonesAColorier.length * TAILLE_CASE,
				problemeAAfficher.zonesAColorier[0].length * TAILLE_CASE);

		// affichage des couleurs

		for (int i = 0; i < problemeAAfficher.zonesAColorier.length; i++) {
			for (int j = 0; j < problemeAAfficher.zonesAColorier[0].length; j++) {
				g.setColor(CONSTANTES_COULEURS[solutionCourante.couleurs[problemeAAfficher.zonesAColorier[i][j]]]);
				g.fillRect(i * TAILLE_CASE, j * TAILLE_CASE, TAILLE_CASE,
						TAILLE_CASE);
			}
		}

		g.setColor(Color.black);
		// parcours du tableau
		for (int i = 0; i < problemeAAfficher.zonesAColorier.length; i++) {
			for (int j = 0; j < problemeAAfficher.zonesAColorier[0].length; j++) {
				// test voisins du haut
				if (j != problemeAAfficher.zonesAColorier[0].length - 1)
					if (problemeAAfficher.zonesAColorier[i][j] != problemeAAfficher.zonesAColorier[i][j + 1]) {
						// trace trait horizontal
						g.drawLine(i * TAILLE_CASE, (j + 1) * TAILLE_CASE,
								(i + 1) * TAILLE_CASE, (j + 1) * TAILLE_CASE);
					}
				// test voisins de droite
				if (i != problemeAAfficher.zonesAColorier.length - 1)
					if (problemeAAfficher.zonesAColorier[i][j] != problemeAAfficher.zonesAColorier[i + 1][j]) {
						// trace trait horizontal
						g.drawLine((i + 1) * TAILLE_CASE, (j) * TAILLE_CASE,
								(i + 1) * TAILLE_CASE, (j + 1) * TAILLE_CASE);
					}

			}
		}
	}

}
