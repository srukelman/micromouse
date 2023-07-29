import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

public class Maze {
    private int[][] maze;
    public Maze(int width, int height){
        this.maze = new int[height][width];
    }
    public Maze(int[][] maze){
        this.maze = maze;
    }

    /**
     * <p>
     * exports the contents of the maze to a txt file
     * @return True = wrote maze correctly False = something went wrong
     */
    public boolean writeMaze(){
        int count = 0;
        File myObj = new File(".\\mazes\\maze" + count + ".txt");
        try{
            while(!myObj.createNewFile()){
                count++;
                myObj = new File(".\\mazes\\maze" + count + ".txt");
            }
            FileWriter fw = new FileWriter(myObj);
            fw.write(getHeight()+ "\n" + getWidth()+ "\n");
            for(int i = 0; i < getHeight(); i++){
                String s = "";
                for(int j = 0; j < getWidth(); j++){
                    s += "" + getCell(j, i)+",";
                }
                s+="\n";
                fw.write(s);
            }
            fw.close();
            return true;
        }catch(IOException e){
            System.out.println("error");
            return false;
        }
        
    }

    /**
     * <p>
     * Will increment the value of the cell (cells start at 0) (0=open, 1=closed, 2=start, 3=end)
     * @param x the x-value of the cell
     * @param y the y-value of the cell
     */
    public void updateCell(int x, int y){
        if(x <= maze[0].length && y<= maze.length){
            //0: open; 1: closed; 2: start; 3: end
            maze[y][x] = (maze[y][x] + 1)  % 4;
        }
    }

    /**
     * <p>
     * Will add the passed value to the value of the cell (cells start at 0) (0=open, 1=closed, 2=start, 3=end)
     * @param x the x-value of the cell
     * @param y the y-value of the cell
     * @param val the value to be added to the value of the cell (0=open, 1=closed, 2=start, 3=end) (uses % operator to adjust values to fit this range)
     */
    public void updateCell(int x, int y, int val){
        if(x <= maze[0].length && y<= maze.length){
            //0: open; 1: closed; 2: start; 3: end
            maze[y][x] = (maze[y][x] + val) % 4;
            while(maze[y][x] < 0){
                maze[y][x] += 4;
            }
        }
    }

    /**
     * @param x the x-value of the cell
     * @param y the y-value of the cell
     * @return the value of the cell specified (0=open, 1=closed, 2=start, 3=end)
     */
    public int getCell(int x, int y){
        return maze[y][x];
    }

    /**
     * @return the width of the maze in cells
     */
    public int getWidth(){
        return maze[0].length;
    }
    /**
     * @return the height of the maze in cells
     */
    public int getHeight(){
        return maze.length;
    }

    /**
     * Creates a new maze given new height and width parameters
     * Can initialize a blank maze or refill the old values
     * @param newHeight the number of cells in the height of the new maze
     * @param newWidth the number of cells in the width of the new maze
     * @param blank false creates a new blank array
     */
    public void updateSize(int newHeight, int newWidth, boolean blank){
        if(blank){
            this.maze = new int[newHeight][newWidth];
        }else{
            int[][] temp = new int[newHeight][newWidth];
            int h = (newHeight < getHeight())?newHeight:getHeight();
            int w = (newWidth < getWidth())?newWidth:getWidth();
            for(int i = 0; i < h; i++){
                for(int j= 0; j < w; j++){
                    temp[i][j] = maze[i][j];
                }
            }
            this.maze = temp;
        }
    }
     /**
      * <p>
     * Creates a new blank maze given new height and width parameters
     * @param newHeight the number of cells in the height of the new maze
     * @param newWidth the number of cells in the width of the new maze
     */
    public void updateSize(int newHeight, int newWidth){
        this.maze = new int[newHeight][newWidth];
    }

    /**
     * <p>
     * Clears the maze, replacing all cells with empty cells
     */
    public void clearMaze(){
        this.maze = new int[getHeight()][getWidth()];
    }

}
