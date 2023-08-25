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
                        this.start = new Cell(j, i, 2);
                    }
                }
            }
            return arr;
            
        }catch(Exception e){
            System.out.println("error or sumtthin");
            System.out.println(e);
            return new Cell[1][1];
        }  
    }
    public void solveMaze(){
        Queue<Cell> q = new LinkedList<>();
        int goalX = -1;
        int goalY = -1;
        while(q.peek() != null){
            if(q.peek().getValue() == 3){
                goalX = q.peek().getX();
                goalY = q.peek().getY();
                break;
            }
            int x = q.peek().getX();
            int y = q.peek().getY();
            q.peek().visit();
            if(maze.getCell(x-1, y).getValue() == 0){
                q.add(maze.getCell(x-1, y));
                maze.getCell(x-1, y).setPrevCell(q.peek());
            }
            if (maze.getCell(x, y-1).getValue() == 0){
                q.add(maze.getCell(x, y-1));
                maze.getCell(x, y-1).setPrevCell(q.peek());
            }
            if(maze.getCell(x + 1, y).getValue() == 0){
                q.add(maze.getCell(x + 1, y));
                maze.getCell(x + 1, y).setPrevCell(q.peek());
            }
            if(maze.getCell(x, y+1).getValue() == 0){
                q.add(maze.getCell(x, y+1));
                maze.getCell(x, y+1).setPrevCell(q.peek());
            }
            q.remove();
        }
        maze.writeMaze();
        while(solution.peek().getPrevCell() != null){
            solution.push(solution.peek().getPrevCell());
        }
        System.out.println(solution);
    }   
}
