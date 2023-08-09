public class Cell {
    private Cell prevCell;
    private int value, x, y;
    /**
     * 
     * @param value takes int value of value
     * @param prevCell takes Cell previous cell
     */
    public Cell(int value, Cell prevCell){
        this.value = value;
        this.prevCell = prevCell;
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
    }
    public void getX(){
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
}
