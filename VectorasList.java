import java.util.Vector;


public class VectorasList implements SearchList {

	Vector<State> theQ;
	
	public State remove() {
		return theQ.remove(theQ.size()-1);
	}
	
	public void add(State o) {
		theQ.add(o);
	}
	
	public VectorasList() {
		theQ = new Vector<State>();
	}

	public int size() {
		
		return theQ.size();
	}
	

	
}
