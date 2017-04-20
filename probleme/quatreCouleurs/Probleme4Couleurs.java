package probleme.quatreCouleurs;

import generic.ProblemeAbstract;
import generic.SolutionAbstract;

import java.util.ArrayList;

/**
 * classe qui permet d'implementer le probleme des 4 couleurs
 * 
 * - le constructeur construit un probleme particulier - les solutions doivent
 * etre de la classe Solution4Couleurs - le voisinage consiste à retourner
 * toutes les solutions qui ont une seule zone de couleur différent
 * 
 * 
 * @author vthomas
 * 
 */

public class Probleme4Couleurs implements ProblemeAbstract {

	/**
	 * permet de définir les zones à colorier
	 */
	public int[][] zonesAColorier;

	/**
	 * nombre de zones différentes
	 */
	public int nombreDeZones;

	/**
	 * matric adjacence
	 * <p>
	 * permet de savoir quelle zone est contigue a quelle zone
	 */
	boolean[][] matriceAdjacence;

	/**
	 * construit un probleme par defaut
	 */
	public Probleme4Couleurs() {
		// le probleme est constitué de zone
		// une zone est identifiée par un ensemble de cellules contigues de meme
		// numero

		int[][] zonesInitiales = { { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }, { 1, 3, 3, 3, 6, 6, 5, 7, 9, 8, 1 },
				{ 1, 2, 2, 3, 5, 6, 7, 7, 7, 8, 1 }, { 1, 5, 4, 4, 5, 6, 1, 4, 4, 4, 1 },
				{ 1, 5, 2, 2, 2, 6, 1, 1, 3, 8, 1 }, { 1, 0, 0, 0, 2, 7, 7, 7, 3, 2, 1 },
				{ 1, 0, 1, 0, 3, 3, 4, 4, 4, 2, 1 }, { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 } };
		this.zonesAColorier = zonesInitiales;

		// appelle la méthode qui construit des zones
		// rend unique chaque numero
		nombreDeZones = this.rendreUniqueZonesConnexes();

		// cree la matrice adjacence
		this.creerMatriceAdjacence();
	}

	/**
	 * construit un probleme aleatoire de taille n
	 * 
	 * @param n
	 *            taille de la zone
	 */
	public Probleme4Couleurs(int n) {
		this.zonesAColorier = new int[n][n];

		// remplit aleatoirement
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				zonesAColorier[i][j] = (int) (Math.random() * 5);

		// appelle la méthode qui construit des zones
		// rend unique chaque numero
		this.nombreDeZones = this.rendreUniqueZonesConnexes();

		// cree la matrice adjacence
		this.creerMatriceAdjacence();
	}

	/**
	 * donne un numero unique à chaque zone constituée d'etiquettes de meme
	 * valeur
	 * 
	 * @return le nombre de zones
	 */
	private int rendreUniqueZonesConnexes() {
		// tag des zones
		int[][] etiquettes = new int[zonesAColorier.length][zonesAColorier[0].length];
		for (int i = 0; i < etiquettes.length; i++)
			for (int j = 0; j < etiquettes[0].length; j++)
				etiquettes[i][j] = -1;

		// numero de tag
		int num = -1;

		// parcours du tableau de zone
		for (int i = 0; i < zonesAColorier.length; i++)
			for (int j = 0; j < zonesAColorier[0].length; j++) {
				// si ce n'est pas une zone déja taggée
				if (nonTagge(etiquettes, i, j)) {
					Point p = new Point(i, j);
					num = propagerEtiquettageZone(etiquettes, num, p);
				}
			}

		// on change pour stocker les étiquettes
		zonesAColorier = etiquettes;
		// on retourne le nombre de zones
		return (num + 1);
	}

	/**
	 * methode privee pour agreger les cases avec la meme etiquette
	 * 
	 * @param etiquettes
	 *            les étiquettes a remplir
	 * @param num
	 *            le nombre zones deja existantes
	 * @param p
	 *            le point considere
	 * @return le nombre de zone aprés avoir agrege
	 */
	private int propagerEtiquettageZone(int[][] etiquettes, int num, Point p) {
		{
			// on incremente l'etiquette de zone
			num++;
			etiquettes[p.x][p.y] = num;
			// on declenche une propagation
			ArrayList<Point> pointsAPropager = new ArrayList<Point>();
			pointsAPropager.add(p);

			// tant qu'il y a un point à propager
			while (!pointsAPropager.isEmpty()) {

				Point pointATraiter = pointsAPropager.get(0);
				int couleurDepart = zonesAColorier[pointATraiter.x][pointATraiter.y];

				// regarde chacun des voisins
				int[] voisinsx = { 1, 0, -1, 0 };
				int[] voisinsy = { 0, 1, 0, -1 };
				for (int v = 0; v < voisinsx.length; v++) {
					int nx = pointATraiter.x + voisinsx[v];
					int ny = pointATraiter.y + voisinsy[v];

					if (estDansLaZone(nx, ny)) {
						// si c'est la meme couleur et non tagge
						int couleurVoisin = zonesAColorier[nx][ny];
						if (couleurVoisin == couleurDepart && nonTagge(etiquettes, nx, ny)) {
							// on le tague et on l'ajoute au point futur
							etiquettes[nx][ny] = num;
							pointsAPropager.add(new Point(nx, ny));
						}
					}
					// sinon on laisse le point
				}
				// on retire premier point
				pointsAPropager.remove(0);

			} // fin while// on a fini de taggercette zone
		}
		return num;
	}

	private boolean estDansLaZone(int nx, int ny) {
		return nx > -1 && ny > -1 && nx < zonesAColorier.length && ny < zonesAColorier[0].length;
	}

	private boolean nonTagge(int[][] etiquettes, int nx, int ny) {
		return etiquettes[nx][ny] == -1;
	}

	/**
	 *  permet d'afficher un probleme
	 */
	public String toString() {
		String res = "";
		for (int i = 0; i < zonesAColorier.length; i++) {
			for (int j = 0; j < zonesAColorier[0].length; j++) {
				if (zonesAColorier[i][j] < 10)
					res += "0";
				res += zonesAColorier[i][j];
				res += " ";
			}
			res += "\n";
		}
		return (res);
	}

	/**
	 *  methode qui crée la matrice d'adjacence
	 */
	private void creerMatriceAdjacence() {
		// on cree un tableau pour stocker matrice d'adjacence
		matriceAdjacence = new boolean[nombreDeZones][nombreDeZones];
		// on parcourt le tableau
		for (int i = 0; i < zonesAColorier.length; i++) {
			for (int j = 0; j < zonesAColorier[0].length; j++) {
				// on stocker le num de la zone ij
				int dep = zonesAColorier[i][j];
				// on regarde ses voisins
				int[] voisinX = { 1, 0, -1, 0 };
				int[] voisinY = { 0, 1, 0, -1 };
				// pour chaque voisin
				for (int v = 0; v < voisinX.length; v++) {
					int nx = i + voisinX[v];
					int ny = j + voisinY[v];
					// si nx et ny dans le cadre
					if (estDansLaZone(nx, ny))
						// on cree un lien entre les zones
						matriceAdjacence[dep][zonesAColorier[nx][ny]] = true;
				}

			}
		}
	}

	/**
	 * permet d'évaluer une solution
	 */
	@Override
	public double evaluation(SolutionAbstract s) {

		// teste que la solution est du bon type
		if (!(s instanceof Solution4Couleurs)) {
			throw new IllegalArgumentException("mauvais types");
		}

		// on peut recuperer entoute securite
		Solution4Couleurs solutionAEvaluer = (Solution4Couleurs) s;
		int nombreContraintesBrisees = 0;
		// parcours tous les couples de zone
		for (int i = 0; i < nombreDeZones; i++)
			for (int j = i + 1; j < nombreDeZones; j++) {
				boolean memeCouleur = solutionAEvaluer.memeCouleur(i, j);
				boolean sontAdjacents = matriceAdjacence[i][j];
				if (sontAdjacents && memeCouleur)
					nombreContraintesBrisees++;
			}
		return (nombreContraintesBrisees);
	}
}
