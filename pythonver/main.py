from gui import View
from maze import Maze

if __name__ == '__main__':
    View(Maze.load_maze('mazes/maze52.txt')).run()