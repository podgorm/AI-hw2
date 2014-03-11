import java.util.Vector;


public class IDAList implements SearchList {

	Vector<State> theQ;
    
    int fLimit;
    StateSpace ssp;

    int nextFLimit;
	
	public State remove() {
		return theQ.remove(theQ.size()-1);
	}
	
	public void add(State o) {
	    int fValue = o.getDepth() + ssp.getHeuristicDistance(o.getRep());
	    if (fValue <= fLimit) {
		theQ.add(o);
	    } else if (fValue < nextFLimit){
		nextFLimit = fValue;
	    } 
	    //System.out.println(o.getRep() + "\tfValue: " + fValue +
	    //			   "\tfLimit: " + fLimit +
	    //		       "\tnextFLimit: " + nextFLimit);
	
	}
	
    public IDAList(StateSpace ssp, int fLimit) {
		theQ = new Vector<State>();
		this.ssp = ssp;
		this.fLimit = fLimit;
		nextFLimit = Integer.MAX_VALUE;
	}

	  

	public int size() {
		
		return theQ.size();
	}
	

	
}