import probleme.quatreCouleurs.Afficher4Couleurs;
import probleme.quatreCouleurs.Probleme4Couleurs;
import probleme.quatreCouleurs.Solution4Couleurs;

public class TestProbleme4Couleurs {

	public static void main(String[] args)
	{
		Probleme4Couleurs pb=new Probleme4Couleurs(20);
		
		System.out.println(pb);
		Solution4Couleurs s=new Solution4Couleurs(pb);
		new Afficher4Couleurs(pb,s);
	}
	
}
