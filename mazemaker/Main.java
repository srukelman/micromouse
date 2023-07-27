import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;
import javax.swing.event.*;


public class Main extends JFrame implements ActionListener{


    @Override
    public void actionPerformed(ActionEvent e){}


    private class Board extends JPanel implements MouseListener{
        private Maze maze;
        private int pixWidth, pixHeight;

        public Board(int cellWidth, int cellHeight){
            this.maze = new Maze(cellWidth, cellHeight);
            addMouseListener(this);
        }


        @Override
        public void mouseClicked(MouseEvent e) {}
        @Override
        public void mousePressed(MouseEvent e) {}
        @Override
        public void mouseReleased(MouseEvent e) {}
        @Override
        public void mouseEntered(MouseEvent e) {}
        @Override
        public void mouseExited(MouseEvent e) {}

    }
}