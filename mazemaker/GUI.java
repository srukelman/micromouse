import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.jar.JarEntry;

import javax.swing.*;
import javax.swing.event.*;


public class GUI extends JFrame implements ActionListener{
    private Board board;
    private int lineThickness = 2;
    private JButton startButton, endButton, openButton, closedButton, saveButton;
    static final char OPEN = 'o', CLOSED = 'c', START = 's', END = 'e';
    private char mode = 'o';
    public GUI(){
        super("Maze Maker");
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());
        topPanel.add(openButton = new JButton("Open"));
        topPanel.add(closedButton = new JButton("Closed"));
        topPanel.add(startButton = new JButton("Start"));
        topPanel.add(endButton = new JButton("Finish"));
        add(topPanel, BorderLayout.NORTH);
        add(board = new Board(15, 15), BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(750, 750);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e){}


    private class Board extends JPanel implements MouseListener{
        private Maze maze;
        private int pixWidth, pixHeight;
        private int cellLength;
        public Board(int cellWidth, int cellHeight){
            setSize(new Dimension(600, 600));
            this.maze = new Maze(cellWidth, cellHeight);
            addMouseListener(this);
            this.cellLength = getWidth()/cellWidth;
        }

        public void paintComponent(Graphics g){
            super.paintComponent(g);
            Graphics2D g2d= (Graphics2D) g;
            for(int i = 0; i < maze.getHeight(); i++){
                for(int j = 0; j < maze.getWidth(); j++){
                    Color color = Color.WHITE;
                    switch(maze.getCell(j, i)){
                        case 0: color = Color.WHITE; break;
                        case 1: color = Color.BLACK; break;
                        case 2: color = Color.GREEN; break;
                        case 3: color = Color.RED; break;
                    }
                    g2d.setPaint(color);
                    g2d.fill(new Rectangle2D.Double(j*cellLength, i*cellLength, cellLength, cellLength));
                }
            }
            g2d.setPaint(Color.BLACK);
            g2d.setStroke(new BasicStroke(lineThickness));
            for(int i = 0; i < maze.getHeight()-1; i++){
                g2d.draw(new Line2D.Double(0, (i+1) * cellLength, maze.getWidth()*cellLength, (i+1) * cellLength));
            }
            for(int i = 0; i < maze.getWidth()-1; i++){
                g2d.draw(new Line2D.Double((i+1) * cellLength, 0 , (i+1) * cellLength, maze.getWidth()*cellLength));
            }
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            int xpos=e.getX()/cellLength;
            int ypos=e.getY()/cellLength;
            if(SwingUtilities.isLeftMouseButton(e)){
                System.out.println(xpos+", "+ypos);
                maze.updateCell(xpos, ypos);
            }
            repaint();
        }
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