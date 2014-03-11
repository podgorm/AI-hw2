import java.util.Vector;

public class VacuumWorld implements StateSpace<VacuumState> {

    // [row][col]
    public VacuumState startState;

    public VacuumWorld (VacuumState state) {
        this.startState = state;
    }

    public VacuumState getStart() {
        return this.startState;
    }

    public Vector<VacuumState> getKids(VacuumState current) {
    }

    public boolean isGoal(VacuumState rep) {
    }

    public int getHeuristicDistance(VacuumState stateRep) {
    }
}
