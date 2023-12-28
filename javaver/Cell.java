package javaver;

public class Cell {
    private Cell prevCell;
    private int value, x, y;
    private boolean visited;
    /**
     * 
     * @param value takes int value of value
     * @param prevCell takes Cell previous cell
     */
    public Cell(int value, Cell prevCell){
        this.value = value;
        this.prevCell = prevCell;
        this.visited = false;
    }
    /**
     * <p>
     * deault Cell constructor, creates coordinate at 0,0
     */
    public Cell(){
        this.prevCell = null;
        this.value = 0;
        this.x = 0;
        this.y = 0;
        this.visited = false;
    }
    /**
     * 
     * @param x takes int x coordinate
     * @param y takes int y coordinate
     * @param value takes int value of value
     */
    public Cell(int x, int y, int value){
        this.prevCell = null;
        this.value = 0;
        this.x = x;
        this.y = y;
        this.visited = false;
    }
    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }
    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }
    public void setValue(int value){
        this.value = value;
    }
    public int getValue(){
        return this.value;
    }
    public void setPrevCell(Cell prev){
        this.prevCell = prev;
    }
    public Cell getPrevCell(){
        return this.prevCell;
    }
    public void visit(){
        this.visited = true;
    }
    public boolean isVisited(){
        return this.visited;
    }
    @Override
    public String toString(){
        return this.x + "," + this.y;
    }
}
