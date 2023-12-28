package javaver;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BFSSolver extends MazeSolver{
    public BFSSolver(Maze maze, Cell start){
        super(maze, start);
    }
    public BFSSolver(String FileName){
        super(FileName);
    }
    public String[] solve(){
        Queue<Cell> q = new LinkedList<Cell>();
        ArrayList<String> solution = new ArrayList<String>();
        q.add(start);
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
            //System.out.println("checking " + q.peek());
            s += "checking " + q.peek() + "\n";
            solution.add("checking " + q.peek());
            if(x > 0 && (maze.getCell(x-1, y).getValue()  == 0 || maze.getCell(x-1, y).getValue() == 3)&& maze.getCell(x-1,y).isVisited() == false){
                q.add(maze.getCell(x-1, y));
                maze.getCell(x-1, y).setPrevCell(maze.getCell(x, y));
                maze.getCell(x-1, y).visit();
                // System.out.println("added " + maze.getCell(x-1, y));
                solution.add("added " + maze.getCell(x-1, y));
                s += "added " + maze.getCell(x-1, y) + "\n";
            }
            if (y > 0 && (maze.getCell(x, y-1).getValue() == 0 || maze.getCell(x, y-1).getValue() == 3) && maze.getCell(x,y-1).isVisited() == false){
                q.add(maze.getCell(x, y-1));
                maze.getCell(x, y-1).setPrevCell(maze.getCell(x, y));
                maze.getCell(x, y-1).visit();
                // System.out.println("added " + maze.getCell(x, y-1));
                solution.add("added " + maze.getCell(x, y-1));
                s += "added " + maze.getCell(x, y-1) + "\n";
            }
            if(x < maze.getWidth() - 1 && (maze.getCell(x + 1, y).getValue() == 0 || maze.getCell(x + 1, y).getValue() == 3) && maze.getCell(x+1,y).isVisited() == false){
                q.add(maze.getCell(x + 1, y));
                maze.getCell(x + 1, y).setPrevCell(maze.getCell(x, y));
                maze.getCell(x + 1, y).visit();
                // System.out.println("added " + maze.getCell(x + 1, y));
                solution.add("added " + maze.getCell(x + 1, y));
                s += "added " + maze.getCell(x+1, y) + "\n";
            }
            if(y < maze.getHeight() - 1 && (maze.getCell(x, y + 1).getValue() == 0 || maze.getCell(x, y + 1).getValue() == 3) && maze.getCell(x,y+1).isVisited() == false){
                q.add(maze.getCell(x, y+1));
                maze.getCell(x, y+1).setPrevCell(maze.getCell(x, y));
                maze.getCell(x, y+1).visit();
                // System.out.println("added " + maze.getCell(x, y+1));
                solution.add("added " + maze.getCell(x, y+1));
                s += "added " + maze.getCell(x, y +1) + "\n";
            }
            q.remove();
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
