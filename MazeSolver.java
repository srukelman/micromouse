import java.io.File;
import java.util.LinkedList;
import java.util.Scanner;

public abstract class MazeSolver {
    protected Maze maze;
    protected LinkedList<Cell> path;
    protected Cell start;

    public MazeSolver(Maze maze, Cell start) {
        this.maze = maze;
        this.path = new LinkedList<Cell>();
        this.start = start;
    }

    public MazeSolver(String FileName){
        this.maze = new Maze(loadMaze(FileName));
        this.path = new LinkedList<Cell>();
    }
    public Cell[][] loadMaze(String FileName){
        try{
            File f = new File(FileName);
            Scanner scan = new Scanner(f);
            int h = Integer.parseInt(scan.nextLine());
            int w = Integer.parseInt(scan.nextLine());
            Cell[][]arr = new Cell[h][w];
            for(int i = 0; i < h; i++){
                String[] temp = scan.nextLine().split(",");
                for(int j = 0; j < temp.length; j++){
                    arr[i][j] = new Cell(j, i, 0);
                    arr[i][j].setValue(Integer.parseInt(temp[j]));
                    if(arr[i][j].getValue() % 4 == 2){
                        this.start = new Cell(j, i, 2);
                    }
                }
            }
            scan.close();
            return arr;
            
        }catch(Exception e){
            System.out.println("maze could not be loaded " +e);
            System.out.println(e);
            return new Cell[1][1];
        }
    }
    abstract public String[] solve();
}
