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
        System.out.println("maze loaded " + maze.getHeight() + "x" + maze.getWidth());
    }
    public MazeSolver(){
        this.maze = new Maze(loadMaze(".\\mazes\\maze0.txt"));
        System.out.println("maze loaded " + maze.getHeight() + "x" + maze.getWidth());
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
                    arr[i][j] = new Cell(j, i, 0);
                    arr[i][j].setValue(Integer.parseInt(temp[j]));
                    if(arr[i][j].getValue() % 4 == 2){
                        this.start = new Cell(j, i, 2);
                    }
                }
            }
            return arr;
            
        }catch(Exception e){
            System.out.println("maze could not be loaded " +e);
            System.out.println(e);
            return new Cell[1][1];
        }  
    }
    public void solveMaze(){
        System.out.println(maze);
        Queue<Cell> q = new LinkedList<>();
        q.add(start);
        while(q.peek() != null){
            if(q.peek().getValue() == 3){
                solution.push(q.peek());
                System.out.println("found end");
                break;
            }
            int x = q.peek().getX();
            int y = q.peek().getY();
            System.out.println("checking " + q.peek());
            if(x > 0 && maze.getCell(x-1, y).getValue()  % 2 != 0 && maze.getCell(x-1,y).isVisited() == false){
                q.add(maze.getCell(x-1, y));
                maze.getCell(x-1, y).setPrevCell(q.peek());
                maze.getCell(x-1, y).visit();
                System.out.println("added " + maze.getCell(x-1, y));
            }
            if (y > 0 && maze.getCell(x, y-1).getValue() % 2 != 0 && maze.getCell(x,y-1).isVisited() == false){
                q.add(maze.getCell(x, y-1));
                maze.getCell(x, y-1).setPrevCell(q.peek());
                maze.getCell(x, y-1).visit();
                System.out.println("added " + maze.getCell(x, y-1));
            }
            if(x < maze.getWidth() - 1 && maze.getCell(x + 1, y).getValue() % 2 != 0 && maze.getCell(x+1,y).isVisited() == false){
                q.add(maze.getCell(x + 1, y));
                maze.getCell(x + 1, y).setPrevCell(q.peek());
                maze.getCell(x + 1, y).visit();
                System.out.println("added " + maze.getCell(x + 1, y));
            }
            if(y < maze.getHeight() - 1 && maze.getCell(x, y+1).getValue() % 2 != 0 && maze.getCell(x,y+1).isVisited() == false){
                q.add(maze.getCell(x, y+1));
                maze.getCell(x, y+1).setPrevCell(q.peek());
                maze.getCell(x, y+1).visit();
                System.out.println("added " + maze.getCell(x, y+1));
            }
            q.remove();
        }
        while(solution.peek().getPrevCell() != null){
            solution.push(solution.peek().getPrevCell());
        }
        System.out.println(solution);
    }   
}
