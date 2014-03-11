import java.util.Scanner;
import java.util.Vector;
import java.util.HashMap;

public class SearchBase {

    private static int PRINT_HOW_OFTEN = 10;
    public static boolean debug = false;


    public static void main(String[] args)  {
        String search_type = args[0];
        int depth_limit = Integer.parseInt(args[1]);
        PRINT_HOW_OFTEN = Integer.parseInt(args[2]);
        System.out.println("Depth Limit "+depth_limit);
        new SearchBase().process(search_type, depth_limit);
    }

    private void process(String search_type, int depth_limit) {

        char[][] startChar = {
            {'X', 'X', 'X', 'X', 'X', 'X', 'X'},
            {'X', '.', '.', '.', '.', 'S', 'X'},
            {'X', '.', 'X', 'X', 'X', '.', 'X'},
            {'X', '.', '.', 'X', '.', '.', 'X'},
            {'X', 'X', '.', '.', '.', 'X', 'X'},
            {'X', 'D', '.', 'X', 'X', 'X', 'X'},
            {'X', 'X', 'X', 'X', 'X', 'X', 'X'},
        };

        int NEXT_DEPTH = Integer.MAX_VALUE;
        // if (search_type.equals("BFS")) {
        //     NEXT_DEPTH = bestFirstSearch(depth_limit, new VacuumState(startChar));
        // } else if (search_type.equals("DFS")) {
        //     NEXT_DEPTH = vectorSearch(depth_limit, new VacuumState(startChar));
        // } else if (search_type.equals("DFID")) {
        //     NEXT_DEPTH = DFID(depth_limit, new VacuumState(startChar));
        // } else if (search_type.equals("A")) {
        if (search_type.equals("A")) {
            NEXT_DEPTH = aStar(new VacuumState(startChar));
        } else if (search_type.equals("IDA")) {
            NEXT_DEPTH = IDAStar(new VacuumState(startChar));
        }
        System.out.println("The goal was found: "+p.getValue());
        System.out.println("Suggested next depth is "+NEXT_DEPTH);
    }

    public boolean search(int limit, State ssp, Heuristic<State> heur, SearchList open) {
        // Uses deep copy of ssp
        ssp = ssp.copy();
        System.out.println("Starting search with limit "+ limit);
        open.add(ssp);
        int count = 0;
        if (open.size()==0) {
            System.out.println("open list empty at "+count);
            return false;
        }
        State current = open.remove();
        count++;
        if (count % PRINT_HOW_OFTEN == 0) {
            System.out.println("Search limit "+limit+" at Node # "+
                count+" Open list length:"+open.size()+" Current Node "+
                current.toString()+"  Depth: "+current.getDepth() +
                "  h(n): " + heur.evaluate(current));
        }
        if (current.isGoal()) {
            System.out.println(count+"> found goal at "+current.toString()+" at depth"+current.getDepth());
            // current.printPath();
            return true;
        }

        if (current.getDepth() <= limit) {
            HashMap<Character, State> kids = current.childStates();
            // Vector<State> kids = current.childStates();

            for (State v : kids.values()) {
                // TODO repeated paths
                // if (!current.getPath().contains(v))
                open.add(v);
            }
        }
    }

    // public int vectorSearch(int limit, State ssp) {
    //     return search(limit,ssp,new VectorAsList());
    // }

    // public int bestFirstSearch(CarryBoolean done, int limit, State ssp) {

    //     return search(done,limit,ssp,new PQasList());
    // }

    // public int DFID(CarryBoolean done, int loop_limit, State ssp) {

    //     int limit = 0;
    //     while (!done.getValue() && (limit < loop_limit)) {
    //         limit = search(done, limit, ssp, new VectorAsList());
    //         System.out.println("Completed search with depth: " + limit);
    //         if (done.getValue())
    //         System.out.println("Found the solution");
    //     }

    //     return limit;
    // }

    public int aStar(State ssp) {
        return search(Integer.MAX_VALUE, ssp, new PQList2(ssp));
    }

    public int IDAStar(State ssp) {
        String start = ssp.getStart();
        int fLimit = ssp.getHeuristicDistance(start);
        while (!done.getValue()) {
            IDAList list = new IDAList(ssp, fLimit);
            search(done, Integer.MAX_VALUE, ssp, list);
            fLimit = list.nextFLimit;
        }
        return fLimit;
    }
}
