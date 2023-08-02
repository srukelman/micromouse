import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class MazeSolver {
    private Maze maze;
    private Stack<Integer> solution = new Stack<Integer>();
    private Coordinate start;

    public MazeSolver(String filename){
        this.maze = new Maze(loadMaze(filename));
    }
    public MazeSolver(){
        this.maze = new Maze(loadMaze(".\\mazes\\maze0.txt"));
    }
    private int[][] loadMaze(String FileName){
        try{
            File f = new File(FileName);
            Scanner scan = new Scanner(f);
            int h = Integer.parseInt(scan.nextLine());
            int w = Integer.parseInt(scan.nextLine());
            int[][]arr = new int[h][w];
            for(int i = 0; i < h; i++){
                String[] temp = scan.nextLine().split(",");
                for(int j = 0; j < temp.length; j++){
                    arr[i][j] = Integer.parseInt(temp[j]);
                    if(arr[i][j] % 4 == 2){
                        this.start = new Coordinate(j, i);
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
        while(solution.peek() != null && solution.peek() != 2){
            int x = solution.peek();
            int j = x % 10;
            int i = x / 10;
            System.out.println(solution);
            solution.push(maze.getCell(j, i));
        }
        System.out.println(solution);
    }   
}
