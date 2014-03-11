import java.util.Vector;
import java.util.HashMap;

public interface State{
    public String toString();
    public HashMap<Character, State> childStates();
    public boolean isGoal();
    public int getDepth();
    public State copy();
}
