import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;


public class GUI extends JFrame implements ActionListener{
    private Board board;
    
    private int lineThickness = 2;
    private JButton saveButton, changeButton, clearButton;
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
        clearButton.addActionListener(this);
        saveButton.addActionListener(this);
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
        if(e.getSource() == changeButton){
            try{
                int oldmax = Math.max(maze.getHeight(), maze.getWidth());
                int newWidth = Integer.parseInt(widthField.getText());
                int newHeight = Integer.parseInt(heightField.getText());
                int newmax = Math.max(newHeight, newWidth);
                maze.updateSize(newHeight, newWidth, true);
                board.setMaze(maze);
                board.setCellLength(board.getCellLength()*oldmax/newmax);
                board.repaint();
            }catch(Exception d){
                return;
            }
            return;
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

        public void paintComponent(Graphics g){
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
                System.out.println(xpos+", "+ypos);
                maze.updateCell(xpos, ypos);
            }else if(SwingUtilities.isRightMouseButton(e)){
                System.out.println(xpos+", "+ypos);
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