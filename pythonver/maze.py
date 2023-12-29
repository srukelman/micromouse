from cell import Cell
from  os.path import isfile

class Maze:
    def __init__(self, maze : list[list[Cell]]):
        self._maze = maze
        self._h = len(maze)
        self._w = len(maze[0])
        self._start = self._find_start()

    def load_maze(fname: str) -> list[list[Cell]]:
        try:
            f = open(fname, 'r')
        except FileNotFoundError:
            print(f'File {fname} not found.')
            return None
        lines = [line.strip() for line in f.readlines()]
        h = int(lines[0])
        w = int(lines[1])
        maze = [[Cell(x, y, int(lines[y+2][x]), None, False) for x in range(w)] for y in range(h)]
        return maze
    
    def _find_start(self) -> tuple[int]:
        for row in self._maze:
            for cell in row:
                if cell.get_value() == 2:
                    return (cell.get_x(), cell.get_y())

    def get_start(self) -> tuple[int]:
        return self._start

    def write_maze(self) -> None:
        count = 0
        while isfile(f'maze{count}.txt'):
            count += 1
        outstr = f'{self._h}\n{self._w}\n'
        outstr += str(self)
    
    def __str__(self) -> str:
        outstr = ''
        for row in self._maze:
            outstr += ','.join([str(cell.get_value()) for cell in row])
            outstr += '\n'
        return outstr
    
    def get_cell(self, x : int, y : int) -> Cell:
        return self._maze[y][x]
    
    def get_width(self) -> int:
        return self._w
    
    def get_height(self) -> int:
        return self._h
    
    def update_size(self, new_w : int, new_h : int, blank : bool) -> None:
        self._w = new_w
        self._h = new_h
        maze = [[Cell(x, y, 0, None, False) for x in range(new_w)] for y in range(new_h)]
        if not blank:
            for row in self._maze:
                for cell in row:
                    maze[cell.get_y()][cell.get_x()] = cell
        self._maze = maze


    
