import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileWriter;

public class Maze {
    private Cell[][] maze;
    public Maze(int width, int height){
        this.maze = new Cell[height][width];
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                maze[i][j] = new Cell(j, i, 0);
            }
        }
    }
    public Maze(Cell[][] maze){
        this.maze = maze;
    }
    public Maze(String FileName){
        this.maze = loadMaze(FileName);
    }
    public Cell[][] loadMaze(String FileName){
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
    /**
     * <p>
     * exports the contents of the maze to a txt file
     * @return True = wrote maze correctly False = something went wrong
     */
    public File writeMaze(){
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
                    s += "" + getCell(j, i).getValue()+",";
                }
                s+="\n";
                fw.write(s);
            }
            fw.close();
            return myObj;
        }catch(IOException e){
            System.out.println("error");
            return myObj;
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
            maze[y][x].setValue((maze[y][x].getValue() + 1)  % 4);
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
            maze[y][x].setValue((maze[y][x].getValue() + val) % 4);
            while(maze[y][x].getValue() < 0){
                maze[y][x].setValue(maze[y][x].getValue() + 4);
            }
        }
    }

    /**
     * <p>
     * Will add the passed value to the value of the cell (cells start at 0) (0=open, 1=closed, 2=start, 3=end)
     * @param x the x-value of the cell
     * @param y the y-value of the cell
     * @param val the new value of the cell (0=open, 1=closed, 2=start, 3=end (negatives are visited by solver)) (uses % operator to adjust values to fit this range)
     */
    public void setCell(int x, int y, int val){
        if(x <= maze[0].length && y<= maze.length){
            //0: open; 1: closed; 2: start; 3: end
            maze[y][x].setValue(val);
        }
    }

    /**
     * @param x the x-value of the cell
     * @param y the y-value of the cell
     * @return the value of the cell specified (0=open, 1=closed, 2=start, 3=end)
     */
    public Cell getCell(int x, int y){
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
            this.maze = new Cell[newHeight][newWidth];
        }else{
            Cell[][] temp = new Cell[newHeight][newWidth];
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
        this.maze = new Cell[newHeight][newWidth];
    }

    /**
     * <p>
     * Clears the maze, replacing all cells with empty cells
     */
    public void clearMaze(){
        this.maze = new Cell[getHeight()][getWidth()];
    }

    @Override
    public String toString(){
        String s = "";
        for(int i = 0; i < getHeight(); i++){
                
                for(int j = 0; j < getWidth(); j++){
                    s += "" + getCell(j, i).getValue()+",";
                }
                s+="\n";
        }
        return s;
    }

}
