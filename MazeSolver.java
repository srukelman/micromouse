import java.io.File;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class MazeSolver {
    private Maze maze;
    private Stack<Integer> solution = new Stack<Integer>();
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
            if(i < maze.getHeight() - 1){
                if(maze.getCell(j, i+1) == 0){
                    int x = ((i+1) * 10) + j;
                    q.add(x);
                    x = (i * 10) + j;
                    maze.setCell(j, i+1, x+11);
                }else if(maze.getCell(j, i+1) == 3){
                    int x = ((i+1)*10) + j;
                    solution.push(x);
                    x = (i * 10) + j;
                    maze.setCell(j, i+1, x+11);
                }
            }
            if(j < maze.getWidth() -1){
                if(maze.getCell(j+1, i) == 0){
                    int x = (i * 10) + j + 1;
                    q.add(x);
                    x = (i * 10) + j;
                    maze.setCell(j+1, i, x+11);
                }else if(maze.getCell(j + 1, i) == 3){
                    int x = (i*10) + j + 1;
                    solution.push(x);
                    x = (i * 10) + j;
                    maze.setCell(j + 1, i, x+11);
                }
            }
            if(i > 0){
                if(maze.getCell(j, i-1) == 0){
                    int x = ((i-1)*10) + j;
                    q.add(x);
                    x = (i * 10) + j;
                    maze.setCell(j, i-1, x+11);
                }else if(maze.getCell(j, i-1) == 3){
                    int x = ((i-1)*10) + j;
                    solution.push(x);
                    x = (i * 10) + j;
                    maze.setCell(j, i-1, x+11);
                }
            }
            if(j > 0){
                if(maze.getCell(j-1, i) == 0){
                    int x = (i * 10) + j - 1;
                    q.add(x);
                    x = (i * 10) + j;
                    maze.setCell(j-1, i, x+11);
                }else if(maze.getCell(j - 1, i) == 3){
                    int x = (i*10) + j - 1;
                    solution.push(x);
                    x = (i * 10) + j;
                    maze.setCell(j-1, i, x+11);
                }
            }
            q.remove();
            //System.out.println(q);

        }
        while(solution.peek() != null && solution.peek() != 2){
            int x = solution.peek() - 11;
            int j = x % 10;
            int i = x / 10;
            System.out.println(solution);
            solution.push(maze.getCell(j, i));
        }
        System.out.println(solution);
    }   

}
