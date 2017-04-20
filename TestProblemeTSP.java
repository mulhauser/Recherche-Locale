import probleme.TSP.AfficheTSP;
import probleme.TSP.SolutionTSP;
import probleme.TSP.TSP;

public class TestProblemeTSP {

	public static void main(String[] args)
	{
		TSP pb=new TSP(100,400);
		
		System.out.println(pb);
		SolutionTSP s=new SolutionTSP(pb);
		new AfficheTSP(pb,s);
	}
	
}
