from typing import Any
from maze import Maze
from cell import Cell
from collections import deque

class BFS_Solver:

    def __init__(self, maze : Maze):
        self._maze = maze
        self._start = maze.get_start()
        self._finish = None
        self._path = []
        self._steps = []

    def solve(self) -> list[str]:
        q = deque()
        sol = []
        q.add(self._start)
        sol.append('added ' + str(self._start))
        while q:
            curr = q.popleft()
            if curr.get_value() == 3:
                self._finish = curr
                break
            elif not curr.get_visited():
                curr.visit()
                sol.append('visited ' + str(curr))
                self._steps.append(curr)
                for cell in self._get_neighbors(curr):
                    sol.append('added ' + str(cell))
                    q.append(cell)
        
        if not self._finish:
            return None
        else:
            path = self._get_path()
            for cell in path:
                sol.append('solving ' + str(cell))
        
    def _get_path(self) -> list[Cell]:
        path = []
        curr = self._finish
        while curr:
            path.append(curr)
            curr = curr.get_prev_cell()
        return path[::-1]

    def _get_neighbors(self, cell : Cell) -> list[Cell]:
        x = cell.get_x()
        y = cell.get_y()
        neighbors = []
        if x > 0:
            neighbors.append(self._maze.get_cell(x-1, y))
        if x < self._maze.get_width() - 1:
            neighbors.append(self._maze.get_cell(x+1, y))
        if y > 0:
            neighbors.append(self._maze.get_cell(x, y-1))
        if y < self._maze.get_height() - 1:
            neighbors.append(self._maze.get_cell(x, y+1))
        return neighbors

