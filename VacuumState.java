import java.util.HashMap;

public class VacuumState {

    // [row][col]
    // 0,0 0,1
    // 1,0 1,1
    public Spot[][] map;
    public int dirt;
    public int x;
    public int y;
    public depth;

    public class Spot {
        public static final char DIRT = 'D';
        public static final char ROBOT = 'S';
        public static final char WALL = '#';
        public static final char SPACE = '.';

        public boolean dirt;
        public boolean wall;
        public boolean robot;

        public Spot(boolean dirt, boolean wall, boolean robot) {
            this.dirt = dirt;
            this.wall = wall;
            this.robot = robot;
        }

        public Spot(char x) {
            this.dirt = false;
            this.wall = false;
            this.robot = false;
            switch (x) {
                case DIRT:
                    this.dirt = true;
                    break;
                case ROBOT:
                    this.robot = true;
                    break;
                case WALL:
                    this.wall = true;
                    break;
            }
        }

        public Spot copy() {
            return new Spot(this.dirt, this.wall, this.robot);
        }
    }

    public VacuumState(Spot[][] spotMap) {
        this.map = new Spot[spotMap.length][spotMap[0].length];
        for (int i=0; i<spotMap.length; i++) {
            for (int j=0; j<spotMap[i].length; j++) {
                this.map[i][j] = spotMap[i][j];
            }
        }
        this.setup();
    }

    private void setup() {
        for (int i=0; i<this.map.length; i++) {
            for (int j=0; j<this.map[i].length; j++) {
                Spot x = this.map [i][j];
                if (x.dirt) {
                    this.dirt++;
                }
                if (x.robot) {
                    this.x = i;
                    this.y = j;
                }
            }
        }
        this.depth=0;
    }

    public VacuumState(char[][] charMap) {
        this.map = new Spot[charMap.length][charMap[0].length];
        for (int i=0; i<charMap.length; i++) {
            for (int j=0; j<charMap[i].length; j++) {
                this.map[i][j] = new Spot(charMap[i][j]);
            }
        }
        this.setup();
    }

    public VacuumState copy() {
        VacuumState next = new VacuumState(this.map);
        next.depth = this.depth;
        return next;
    }

    public int getDepth() {
        return this.depth;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Spot[] col : this.map) {
            for (Spot x : col) {
                if (x.wall) {
                    sb.append(Spot.WALL);
                } else if (x.robot) {
                    sb.append(Spot.ROBOT);
                } else if (x.dirt) {
                    sb.append(Spot.DIRT);
                } else {
                    sb.append(Spot.SPACE);
                }
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    public boolean isGoal() {
        return this.dirt = 0;
    }

    public HashMap<Character, VacuumState> childStates() {
        HashMap<Character, VacuumState> children = new HashMap<Character, VacuumState>();
        VacuumState next;

        next = this.goUp();
        if (next != null) {
            children.put('u', next);
        }

        next = this.goDown();
        if (next != null) {
            children.put('d', next);
        }

        next = this.goLeft();
        if (next != null) {
            children.put('l', next);
        }

        next = this.goRight();
        if (next != null) {
            children.put('r', next);
        }

        next = this.clean();
        if (next != null) {
            children.put('c', next);
        }

        return children;
    }

    private VacuumState goUp() {
        if (this.map[this.x-1][this.y].wall) {
            return null;
        } else {
            VacuumState next = this.copy();
            next.map[this.x-1][this.y].robot = true;
            next.map[this.x][this.y].robot = false;
            return next;
        }
    }

    private VacuumState goDown() {
        if (this.map[this.x+1][this.y].wall) {
            return null;
        } else {
            VacuumState next = this.copy();
            next.map[this.x+1][this.y].robot = true;
            next.map[this.x][this.y].robot = false;
            return next;
        }
    }

    private VacuumState goLeft() {
        if (this.map[this.x][this.y-1].wall) {
            return null;
        } else {
            VacuumState next = this.copy();
            next.map[this.x][this.y-1].robot = true;
            next.map[this.x][this.y].robot = false;
            return next;
        }
    }

    private VacuumState goRight() {
        if (this.map[this.x][this.y+1].wall) {
            return null;
        } else {
            VacuumState next = this.copy();
            next.map[this.x][this.y+1].robot = true;
            next.map[this.x][this.y].robot = false;
            return next;
        }
    }

    private VacuumState clean() {
        if (this.map[this.x][this.y].dirt) {
            VacuumState next = this.copy();
            this.map[this.x][this.y].dirt = false;
            this.dirt--;
            return next;
        } else {
            return null;
        }
    }

}
