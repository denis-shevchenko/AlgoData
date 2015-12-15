/**
 * 
 */
package examples;


import java.util.HashSet;
import java.util.Set;

/**
 * @author ps
 *
 */
public class LCS {
	
	char [] x,y;
	int n,m;
	int [][] lcs;
	
	public LCS (String sX, String sY){
		x = sX.toCharArray();
		y = sY.toCharArray();
		n = x.length;
		m = y.length;
		// we fill in an additional row and column
		lcs = new int[n+1][m+1];
		for (int i=1;i<=n;i++)
			for (int k = 1;k<=m;k++)
				if (x[i-1]==y[k-1]) lcs[i][k] = lcs[i-1][k-1]+1;
				else lcs[i][k]=Math.max(lcs[i][k-1],lcs[i-1][k]);
	}
	
	private String getLCSubSequence(){
		return getLCSubSequence(n, m);
	}
	
	private String getLCSubSequence(int i, int k){
		// returns a longest common subsequence of
		// x[0..i-1] an y[0..k-1]

		return null;
	}
	
	public String getEditString(){
		return getEditString(n, m);
	}

	private String getEditString( int i, int k){
		// generate the edit commands to convert Y to X
		return null;
	}

	public Set<String> getAllSubseqs(){
		Set<String> al = new HashSet<>();
		getLCSubSequences(al,"", n, m);
		return al;
	}

	public void getLCSubSequences(Set<String> list, String seq, int i, int k ){
	}
	
	
	static public void main(String[] argv){
		LCS lc = new LCS("dresen","spenden");
		System.out.println(lc.getLCSubSequence());
		System.out.println(lc.getAllSubseqs());
		System.out.println(lc.getEditString());
	}
}