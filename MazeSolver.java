import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class MazeSolver {
    private Maze maze;
    private Stack<Cell> solution = new Stack<Cell>();
    private Cell start;
    public MazeSolver(String filename){
        this.maze = new Maze(loadMaze(filename));
    }
    public MazeSolver(){
        this.maze = new Maze(loadMaze(".\\mazes\\maze0.txt"));
    }
    private Cell[][] loadMaze(String FileName){
        try{
            File f = new File(FileName);
            Scanner scan = new Scanner(f);
            int h = Integer.parseInt(scan.nextLine());
            int w = Integer.parseInt(scan.nextLine());
            Cell[][]arr = new Cell[h][w];
            for(int i = 0; i < h; i++){
                String[] temp = scan.nextLine().split(",");
                for(int j = 0; j < temp.length; j++){
                    arr[i][j].setValue(Integer.parseInt(temp[j]));
                    if(arr[i][j].getValue() % 4 == 2){
                        this.start = new Cell(j, i);
                    }
                }
            }
            return arr;
            
        }catch(Exception e){
            System.out.println("error or sumtthin");
            System.out.println(e);
            return new int[1][1];
        }  
    }
    public void solveMaze(){
        Queue<List<Integer>> q = new LinkedList<>();
        while(q.peek() != null){
            

        }
        maze.writeMaze();
        while(solution.peek().getPrevCell() != null){
            solution.push(solution.peek().getPrevCell());
        }
        System.out.println(solution);
    }   
}
