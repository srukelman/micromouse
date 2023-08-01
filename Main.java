public class Main{
    public static void main(String[] args){
        //GUI gui = new GUI();
        MazeSolver m = new MazeSolver(".\\mazes\\maze1.txt");
        m.solveMaze();
    }
}