import java.util.PriorityQueue;
import java.util.Comparator;

public class PQList2 implements SearchList {

	PriorityQueue<State> theQ;
    StateSpace puzz;



	public State remove() {
		return theQ.poll();
	}

	public void add(State o) {
		theQ.add(o);
	}

	public PQList2(final StateSpace puzz) {
	    this.puzz = puzz;
	    theQ = new PriorityQueue<State>(10, new Comparator<State>() {
		    public int compare(State s1, State s2) {
			int g1 = s1.getDepth();
			int g2 = s2.getDepth();
			
			int h1 = puzz.getHeuristicDistance(s1.getRep());
			int h2 = puzz.getHeuristicDistance(s2.getRep());

			return g1 + h1 - g2 - h2;
		    }
		});
	}

	public int size() {

		return theQ.size();
	}



}