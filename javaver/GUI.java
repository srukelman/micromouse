package javaver;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.io.File;
import java.util.Scanner;
import java.util.Stack;
import java.util.concurrent.TimeUnit;

import javax.swing.*;


public class GUI extends JFrame implements ActionListener{
    private Board board;
    
    private int lineThickness = 2;
    private JButton saveButton, changeButton, clearButton, solveButton, loadButton;
    private JTextField widthField, heightField;
    private JLabel wLabel, hLabel;
    public GUI(){
        super("Maze Maker");
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());
        topPanel.add(wLabel = new JLabel("Width: "));
        widthField = new JTextField("");
        widthField.setColumns(5);
        topPanel.add(widthField);
        topPanel.add(hLabel = new JLabel("Height: "));
        heightField = new JTextField("");
        heightField.setColumns(5);
        topPanel.add(heightField);
        topPanel.add(changeButton = new JButton("Resize"));
        changeButton.addActionListener(this);
        add(topPanel, BorderLayout.NORTH);
        add(board = new Board(15, 15), BorderLayout.CENTER);
        JPanel botPanel = new JPanel();
        botPanel.setLayout(new FlowLayout());
        botPanel.add(clearButton = new JButton("Clear"));
        botPanel.add(saveButton = new JButton("Save"));
        botPanel.add(loadButton = new JButton("Load"));
        botPanel.add(solveButton = new JButton("Solve"));
        clearButton.addActionListener(this);
        saveButton.addActionListener(this);
        solveButton.addActionListener(this);
        loadButton.addActionListener(this);
        add(botPanel, BorderLayout.SOUTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(750, 750);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        Maze maze = board.getMaze();
        if(e.getSource() == saveButton){
            maze.writeMaze();
            return;
        }
        if(e.getSource() == clearButton){
            maze.clearMaze();
            board.setMaze(maze);
            board.repaint();
            return;
        }
        if(e.getSource() == solveButton){
            System.out.println("solving");
            File h = maze.writeMaze();
            MazeSolver m = new BFSSolver(h.getAbsolutePath());
            String[] solution = m.solve();
            System.out.println(solution);
            for (String s: solution){
                String[] temp = s.split(" ");
                String type = temp[0];
                int x = Integer.parseInt(temp[1].split(",")[0]);
                int y = Integer.parseInt(temp[1].split(",")[1]);
                switch(type){
                    case "added": maze.setCell(x, y, 5); break;
                    case "checking": maze.setCell(x, y, 6); break;
                    case "solving": maze.setCell(x, y, 4); break;
                }
                board.updateBoard();
                board.repaint();
                try{
                    Thread.sleep(100);
                }catch(Exception d){}
            }
            
            return;
        }
        if(e.getSource() == changeButton){
            try{
                int oldmax = Math.max(maze.getHeight(), maze.getWidth());
                int newWidth = Integer.parseInt(widthField.getText());
                int newHeight = Integer.parseInt(heightField.getText());
                int newmax = Math.max(newHeight, newWidth);
                maze.updateSize(newHeight, newWidth, false);
                board.setMaze(maze);
                board.setCellLength(board.getCellLength()*oldmax/newmax);
                board.repaint();
            }catch(Exception d){
                return;
            }
            return;
        }
        if(e.getSource() == loadButton){
            final JFileChooser fc = new JFileChooser(".\\mazes\\");
            int returnVal = fc.showOpenDialog(this);
            if(returnVal == JFileChooser.APPROVE_OPTION){
                File f = fc.getSelectedFile();
                Maze temp = new Maze(f.getAbsolutePath());
                board.setMaze(temp);
                board.repaint();
            }
        }
    }


    private class Board extends JPanel implements MouseListener{
        private Maze maze;
        private int cellLength;
        public Board(int cellWidth, int cellHeight){
            setSize(new Dimension(600, 600));
            this.maze = new Maze(cellWidth, cellHeight);
            addMouseListener(this);
            this.cellLength = getWidth()/cellWidth;
        }
        public final Runnable repaintRunnable = new Runnable(){
            @Override
            public void run(){
                System.out.println("repainting");
                repaint();
            }
        };
        public void updateBoard(){
            SwingUtilities.invokeLater(board.repaintRunnable);
        }
        public void paintComponent(Graphics g){
            System.out.println("painting");
            super.paintComponent(g);
            Graphics2D g2d= (Graphics2D) g;
            for(int i = 0; i < maze.getHeight(); i++){
                for(int j = 0; j < maze.getWidth(); j++){
                    Color color = Color.WHITE;
                    switch(maze.getCell(j, i).getValue()){
                        case 0: color = Color.WHITE; break;
                        case 1: color = Color.BLACK; break;
                        case 2: color = Color.GREEN; break;
                        case 3: color = Color.RED; break;
                        case 4: color = Color.BLUE; break;
                        case 5: color = Color.YELLOW; break;
                        case 6: color = Color.MAGENTA; break;
                    }
                    g2d.setPaint(color);
                    g2d.fill(new Rectangle2D.Double(75+ j*cellLength, i*cellLength, cellLength, cellLength));
                }
            }
            g2d.setPaint(Color.BLACK);
            g2d.setStroke(new BasicStroke(lineThickness));
            for(int i = 0; i < maze.getHeight()+1; i++){
                g2d.draw(new Line2D.Double(75, (i * cellLength), 75 + (maze.getWidth()*cellLength), i * cellLength));
            }
            for(int i = 0; i < maze.getWidth()+1; i++){
                g2d.draw(new Line2D.Double(75 + (i * cellLength), 0 , 75 + (i * cellLength), maze.getWidth()*cellLength));
            }
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            int xpos=(e.getX()-75)/cellLength;
            int ypos=e.getY()/cellLength;
            if(SwingUtilities.isLeftMouseButton(e)){
                //System.out.println(xpos+", "+ypos);
                maze.updateCell(xpos, ypos);
            }else if(SwingUtilities.isRightMouseButton(e)){
                //System.out.println(xpos+", "+ypos);
                maze.updateCell(xpos, ypos, -1);
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
        
        public Maze getMaze(){
            return this.maze;
        }
        public void setMaze(Maze maze){
            this.maze = maze;
        }
        public void setCellLength(int cellLength){
            this.cellLength = cellLength;
        }
        public int getCellLength(){
            return this.cellLength;
        }
    }
}