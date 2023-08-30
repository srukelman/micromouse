import java.io.File;
import java.io.FileWriter;
import java.util.LinkedList;
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
    public MazeSolver(Maze maze){
        this.maze = maze;
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
            scan.close();
            return arr;
            
        }catch(Exception e){
            System.out.println("maze could not be loaded " +e);
            System.out.println(e);
            return new Cell[1][1];
        }  
    }
    public File solveMaze(){
        Queue<Cell> q = new LinkedList<>();
        q.add(start);
        File f = new File(".\\solutions\\solution0.txt");
        String s = "";
        while(q.peek() != null){
            if(q.peek().getValue() == 3){
                solution.push(q.peek());
                System.out.println("found end");
                break;
            }
            int x = q.peek().getX();
            int y = q.peek().getY();
            System.out.println("checking " + q.peek());
            s += "checking " + q.peek() + "\n";
            if(x > 0 && (maze.getCell(x-1, y).getValue()  == 0 || maze.getCell(x-1, y).getValue() == 3)&& maze.getCell(x-1,y).isVisited() == false){
                q.add(maze.getCell(x-1, y));
                maze.getCell(x-1, y).setPrevCell(maze.getCell(x, y));
                maze.getCell(x-1, y).visit();
                System.out.println("added " + maze.getCell(x-1, y));
                s += "added " + maze.getCell(x-1, y) + "\n";
            }
            if (y > 0 && (maze.getCell(x, y-1).getValue() == 0 || maze.getCell(x, y-1).getValue() == 3) && maze.getCell(x,y-1).isVisited() == false){
                q.add(maze.getCell(x, y-1));
                maze.getCell(x, y-1).setPrevCell(maze.getCell(x, y));
                maze.getCell(x, y-1).visit();
                System.out.println("added " + maze.getCell(x, y-1));
                s += "added " + maze.getCell(x, y-1) + "\n";
            }
            if(x < maze.getWidth() - 1 && (maze.getCell(x + 1, y).getValue() == 0 || maze.getCell(x + 1, y).getValue() == 3) && maze.getCell(x+1,y).isVisited() == false){
                q.add(maze.getCell(x + 1, y));
                maze.getCell(x + 1, y).setPrevCell(maze.getCell(x, y));
                maze.getCell(x + 1, y).visit();
                System.out.println("added " + maze.getCell(x + 1, y));
                s += "added " + maze.getCell(x + 1, y) + "\n";
            }
            if(y < maze.getHeight() - 1 && (maze.getCell(x, y + 1).getValue() == 0 || maze.getCell(x, y + 1).getValue() == 3) && maze.getCell(x,y+1).isVisited() == false){
                q.add(maze.getCell(x, y+1));
                maze.getCell(x, y+1).setPrevCell(maze.getCell(x, y));
                maze.getCell(x, y+1).visit();
                System.out.println("added " + maze.getCell(x, y+1));
                s += "added " + maze.getCell(x, y+1) + "\n";
            }
            q.remove();
        }
        System.out.println(q);
        while(solution.peek().getPrevCell() != null){
            int x = solution.peek().getPrevCell().getX();
            int y = solution.peek().getPrevCell().getY();
            solution.push(maze.getCell(x, y));
        }
        System.out.println(solution);
        while(!solution.empty()){
            //System.out.println(solution.pop());
            s += "solving " + solution.pop() + "\n";
        }
        try{
            int count = 0;
            while(!f.createNewFile()){
                count++;
                f = new File(".\\solutions\\solution" + count + ".txt");
            }
            FileWriter fw = new FileWriter(f);
            fw.write(s);
            fw.close();
        }catch(Exception e){
            System.out.println("error writing to file");
        }
        return f;
    }   
}
