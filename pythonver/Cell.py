class Cell:
    def __init__(self, x : int, y : int, value : int, prev_cell : 'Cell', visited : bool):
        self._x = x
        self._y = y
        self._value = value
        self._prev_cell = prev_cell
        self._visited = visited

    def get_x(self) -> int:
        return self._x
    
    def get_y(self) -> int:
        return self._y
    
    def get_value(self) -> int:
        return self._value
    
    def get_prev_cell(self) -> 'Cell':
        return self._prev_cell
    
    def get_visited(self) -> bool:
        return self._visited
    
    def visit(self) -> None:
        self._visited = True

    def set_value(self, value : int) -> None:
        self._value = value

    def __str__(self) -> str:
        return f"{self._x},{self._y}"
    
    def set_prev_cell(self, prev_cell : 'Cell') -> None:
        self._prev_cell = prev_cell

    def copy(self):
        if self._prev_cell:
            return Cell(self._x, self._y, self._value, self._prev_cell.copy(), self._visited)
        else:
            return Cell(self._x, self._y, self._value, None, self._visited)