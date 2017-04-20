package probleme.TSP;

/**
 * represente une ville dans le probleme du voyageur de commerce
 * 
 * @author vthomas
 *
 */
public class Ville {

	/**
	 * position de la ville
	 */
	public int x, y;

	/**
	 * id de la ville
	 */
	public int idVille;

	/**
	 * construit un point au hasard à partir des dimensions dans laquelle situer
	 * les villes
	 * 
	 * @param d
	 *            dimension de l'espace dans lequel poser les villes
	 * @param n
	 *            id de la ville
	 */
	public Ville(int d, int n) {
		this.x = (int) (Math.random() * d);
		this.y = (int) (Math.random() * d);
		this.idVille = n;
	}

	/**
	 * retourne distance entre deux villes
	 * 
	 * @param v2
	 *            ville à partir de laquelle on souhaite calculer la distance
	 * @return distance entre this et v2
	 */
	public double calculerDistance(Ville v2) {
		double dx = this.x - v2.x;
		double dy = this.y - v2.y;

		return (Math.sqrt(dx * dx + dy * dy));
	}

	public String toString() {
		return ("" + idVille);
	}

}
