import java.io.File;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class MazeSolver {
    private Maze maze;
    private int start = 0;

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
                        this.start = (i*10) + j;
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
        Queue<Integer> q = new LinkedList<>();
        q.add(this.start);
        while(q.peek() != null){
            int i = q.peek()/10;
            int j = q.peek()%10;
            if(i > 0){
                if(maze.getCell(i-1, j) == 0){
                    int x = ((i-1)*10) + j;
                    q.add(x);
                    x = (i * 10) + j;
                    maze.setCell(i-1, j, x-11);
                }
            }
            if(i < maze.getHeight() - 1){
                if(maze.getCell(i+1, j) == 0){
                    int x = ((i+1) * 10) + j;
                    q.add(x);
                    x = (i * 10) + j;
                    maze.setCell(i+1, j, x-11);
                }
            }
            if(j > 0){
                if(maze.getCell(i, j-1) == 0){
                    int x = (i * 10) + j - 1;
                    q.add(x);
                    x = (i * 10) + j;
                    maze.setCell(i, j-1, x-11);
                }
            }
            if(j < maze.getWidth() -1){
                if(maze.getCell(i, j+1) == 0){
                    int x = (i * 10) + j + 1;
                    q.add(x);
                    x = (i * 10) + j;
                    maze.setCell(i, j+1, x-11);
                }
            }

        }
    }

}
