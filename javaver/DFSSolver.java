package javaver;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Stack;

public class DFSSolver extends MazeSolver{
    public DFSSolver(Maze maze, Cell start){
        super(maze, start);
    }
    public DFSSolver(String FileName){
        super(FileName);
    }
    public String[] solve(){
        Stack<Cell> q = new Stack<Cell>();
        ArrayList<String> solution = new ArrayList<String>();
        q.push(start);
        File f = new File(".\\solutions\\solution0.txt");
        String s = "";
        while(q.peek() != null){
            if(q.peek().getValue() == 3){
                path.push(q.peek());
                System.out.println("found end");
                break;
            }
            int x = q.peek().getX();
            int y = q.peek().getY();
            s += "checking " + q.peek() + "\n";
            solution.add("checking " + q.peek());
            q.pop();
            if(y < maze.getHeight() - 1 && (maze.getCell(x, y+1).getValue() == 0 || maze.getCell(x, y+1).getValue() == 3) && maze.getCell(x,y+1).isVisited() == false){
                q.push(maze.getCell(x, y+1));
                maze.getCell(x, y+1).setPrevCell(maze.getCell(x, y));
                maze.getCell(x, y+1).visit();
                solution.add("added " + maze.getCell(x, y+1));
                s += "added " + maze.getCell(x, y+1) + "\n";
            }
            if (x < maze.getWidth() -1 && (maze.getCell(x+1, y).getValue() == 0 || maze.getCell(x+1, y).getValue() == 3) && maze.getCell(x+1,y).isVisited() == false){
                q.push(maze.getCell(x+1, y));
                maze.getCell(x+1, y).setPrevCell(maze.getCell(x, y));
                maze.getCell(x+1, y).visit();
                solution.add("added " + maze.getCell(x+1, y));
                s += "added " + maze.getCell(x+1, y) + "\n";
            }
            if(y > 0 && (maze.getCell(x, y-1).getValue() == 0 || maze.getCell(x, y-1).getValue() == 3) && maze.getCell(x,y-1).isVisited() == false){
                q.push(maze.getCell(x, y-1));
                maze.getCell(x, y-1).setPrevCell(maze.getCell(x, y));
                maze.getCell(x, y-1).visit();
                solution.add("added " + maze.getCell(x, y-1));
                s += "added " + maze.getCell(x, y-1) + "\n";
            }
            if(x > 0 && (maze.getCell(x-1, y).getValue() == 0 || maze.getCell(x-1, y).getValue()==3) && maze.getCell(x-1,y).isVisited() == false){
                q.push(maze.getCell(x-1, y));
                maze.getCell(x-1, y).setPrevCell(maze.getCell(x, y));
                maze.getCell(x-1, y).visit();
                solution.add("added " + maze.getCell(x-1, y));
                s += "added " + maze.getCell(x-1, y) + "\n";
            }
            
        }
        while(path.peek().getPrevCell() != null){
            int x = path.peek().getPrevCell().getX();
            int y = path.peek().getPrevCell().getY();
            path.push(maze.getCell(x, y));
        }
        while(!path.isEmpty()){
            //System.out.println(solution.pop());
            Cell curr = path.pop();
            solution.add("solving " + curr);
            s += "solving " + curr + "\n";
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
        return solution.toArray(new String[solution.size()]);
    }
}
