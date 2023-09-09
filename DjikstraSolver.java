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
            
        }
        return solution.toArray(new String[solution.size()]);
    }
}
