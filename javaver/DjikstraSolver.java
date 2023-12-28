package javaver;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class DjikstraSolver extends MazeSolver{
    int[][] vertices;
    public DjikstraSolver(Maze maze, Cell start){
        super(maze, start);
        vertices = new int[maze.getHeight()][maze.getWidth()];
        for (int i = 0; i< maze.getHeight(); i++){
            for(int j = 0; j< maze.getWidth(); j++){
                vertices[i][j] = Integer.MAX_VALUE;
            }
        }
    }
    public DjikstraSolver(String FileName){
        super(FileName);
        vertices = new int[maze.getHeight()][maze.getWidth()];
        for (int i = 0; i< maze.getHeight(); i++){
            for(int j = 0; j< maze.getWidth(); j++){
                vertices[i][j] = Integer.MAX_VALUE;
            }
        }
    }
    public String[] solve(){
        ArrayList<String> solution = new ArrayList<String>();
        int currCost = 0;
        String s = "";
        File f = new File(".\\solutions\\solution0.txt");
        Queue<Cell> q = new LinkedList<Cell>();
        vertices[start.getY()][start.getX()] = currCost;
        q.add(start);
        while(!q.isEmpty()){
            int x = q.peek().getX();
            int y = q.peek().getY();
            if(q.peek().getValue() == 3 && path.isEmpty()){
                path.push(q.peek());
                System.out.println("found end");
            }
            //System.out.println("checking " + q.peek());
            s += "checking " + q.peek() + "\n";
            solution.add("checking " + q.peek());
            if(x > 0 && (maze.getCell(x-1, y).getValue()  == 0 || maze.getCell(x-1, y).getValue() == 3)&& maze.getCell(x-1,y).isVisited() == false){
                q.add(maze.getCell(x-1, y));
                solution.add("added " + maze.getCell(x-1, y));
                s += "added " + maze.getCell(x-1, y) + "\n";
            }
            if (y > 0 && (maze.getCell(x, y-1).getValue() == 0 || maze.getCell(x, y-1).getValue() == 3) && maze.getCell(x,y-1).isVisited() == false){
                q.add(maze.getCell(x, y-1));
                solution.add("added " + maze.getCell(x, y-1));
                s += "added " + maze.getCell(x, y-1) + "\n";
            }
            if(x < maze.getWidth() - 1 && (maze.getCell(x + 1, y).getValue() == 0 || maze.getCell(x + 1, y).getValue() == 3) && maze.getCell(x+1,y).isVisited() == false){
                q.add(maze.getCell(x + 1, y));
                solution.add("added " + maze.getCell(x + 1, y));
                s += "added " + maze.getCell(x+1, y) + "\n";
            }
            if(y < maze.getHeight() - 1 && (maze.getCell(x, y + 1).getValue() == 0 || maze.getCell(x, y + 1).getValue() == 3) && maze.getCell(x,y+1).isVisited() == false){
                q.add(maze.getCell(x, y+1));
                solution.add("added " + maze.getCell(x, y+1));
                s += "added " + maze.getCell(x, y +1) + "\n";
            }
            q.remove();
            currCost += 1;
        }
        while(vertices[path.peek().getY()][path.peek().getX()] != 0){
            int MinCost = Integer.MAX_VALUE;
            int x = path.peek().getX();
            int y = path.peek().getY();
            int minx = -1;
            int miny = -1;
            if(x > 0 && (vertices[y][x-1] != -1 && vertices[y][x-1] < MinCost)){
                MinCost = vertices[y][x-1];
                minx = x-1;
                miny = y;
            }
            if (y > 0 && (vertices[y-1][x] != -1 && vertices[y-1][x] < MinCost)){
                MinCost = vertices[y-1][x];
                minx = x;
                miny = y-1;
            }
            if (x < maze.getWidth() - 1 && (vertices[y][x+1] != -1 && vertices[y][x+1] < MinCost)){
                MinCost = vertices[y][x+1];
                minx = x+1;
                miny = y;
            }
            if(y < maze.getHeight() -1 && (vertices[y+1][x] != -1 && vertices[y+1][x] < MinCost)){
                MinCost = vertices[y+1][x];
                minx = x;
                miny = y+1;
            }
            path.push(maze.getCell(minx, miny));
            if(MinCost == Integer.MAX_VALUE){
                System.out.println("No path found");
                return null;
            }
            else if(MinCost == 0){
                break;
            }
        }
        while(!path.isEmpty()){
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
