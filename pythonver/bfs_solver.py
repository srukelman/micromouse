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
        q.append(self._start)
        sol.append('added ' + str(self._start[0]) + ',' + str(self._start[1]))
        while q:
            curr = q.popleft()
            x, y = curr
            curr = self._maze.get_cell(x, y)
            if curr.get_value() == 3:
                self._finish = curr
                break
            elif not curr.get_visited():
                curr.visit()
                sol.append('visited ' + str(curr))
                self._steps.append(curr)
                for cell in self._get_neighbors(curr):
                    sol.append('added ' + str(cell))
                    q.append((cell.get_x(), cell.get_y()))
        
        if not self._finish:
            return None
        else:
            path = self._get_path()
            for cell in path:
                sol.append('solving ' + str(cell))
        return sol
        
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
            curr = self._maze.get_cell(x-1, y)
            if curr.get_value() != 1 and not curr.get_visited():
                neighbors.append(curr)
                curr.set_prev_cell(cell)
        if x < self._maze.get_width() - 1:
            curr = self._maze.get_cell(x+1, y)
            if curr.get_value() != 1 and not curr.get_visited():
                neighbors.append(curr)
                curr.set_prev_cell(cell)
        if y > 0:
            curr = self._maze.get_cell(x, y-1)
            if curr.get_value() != 1 and not curr.get_visited():
                neighbors.append(curr)
                curr.set_prev_cell(cell)
        if y < self._maze.get_height() - 1:
            curr = self._maze.get_cell(x, y+1)
            if curr.get_value() != 1 and not curr.get_visited():
                neighbors.append(curr)
                curr.set_prev_cell(cell)
        return neighbors

