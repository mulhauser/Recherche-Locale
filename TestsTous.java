import generic.AlgorithmeAbstract;
import probleme.quatreCouleurs.Probleme4Couleurs;
import probleme.quatreCouleurs.Solution4Couleurs;

/**
 * permet de tester tous les algorithmes
 * @author vthomas
 *
 */
public class TestsTous {
	
	public static void main(String[] args)
	{
		Probleme4Couleurs pb=new Probleme4Couleurs();
		Solution4Couleurs s=new Solution4Couleurs(pb);
		
		String[] algo={"greedy","recuit"/*,"taboue"*/};
		
		//pour chaque algo
		for (int i=0;i<algo.length;i++)
		{
			//affiche
			System.out.println("\n\n\n\n *********************\n"+algo[i]+"\n\n");
			//recupetrer algorithme
			AlgorithmeAbstract algorithme=AlgorithmeAbstract.getAlgo(algo[i], pb, s);
			
			//permet de gerer la fin
			boolean fin=false;
			//lancer 1000 iretations
			for (int it=0;it<1000&&!fin;it++)
			{
				fin=algorithme.ameliorerSolution();
				System.out.println(it+"; "+algorithme.log());
			}
			
		}
	}
	
}
