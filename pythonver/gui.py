import pygame
from cell import Cell
from maze import Maze
from bfs_solver import BFS_Solver

COLORS = {
    1: (0, 0, 0), # black - wall
    0: (255, 255, 255), # white - empty
    2: (0, 255, 0), # green - start
    3: (255, 0, 0), # red - finish
    4: (0, 0, 255), # blue - path
    5: (255, 255, 0), # yellow - visited
    6: (255, 0, 255), # purple - added (not visited)
}
    

class View:

    def __init__(self, maze: list[list[Cell]]) -> None:
        self._maze = Maze(maze)
        self._running = True
        self._solution = BFS_Solver(self._maze).solve()
        self._index = 0
        self._solving = False
        print(self._solution)
        
    def run(self) -> None:
        pygame.init()
        X , Y = 800, 800
        self._screen = pygame.display.set_mode((X, Y), pygame.RESIZABLE)
        self._draw()
        pygame.display.set_caption('Maze Solver')
        clock = pygame.time.Clock()
        while self._running and (not self._solution or self._index < len(self._solution)):
            clock.tick(3)
            self._handle_events()
            if self._solving:
                self._update_maze()
                self._index += 1
                self._draw()
        pygame.quit()

    def _handle_events(self):
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                self._running = False
            elif event.type == pygame.VIDEORESIZE:
                self._resize_surface(event.size)
            elif event.type == pygame.KEYDOWN:
                if event.key == pygame.K_SPACE:
                    self._index = 0
                    if self._solution:
                        self._solving = True
                        print('solving')
                elif event.key == pygame.K_RETURN:
                    self._solving = False

    def _draw(self) -> None:
        self._screen.fill((150, 150, 150)) # gray background color
        self._draw_maze()
        pygame.display.flip()

    def _draw_maze(self) -> None:
        board_state = self._maze.get_maze()
        for row in board_state:
            for cell in row:
                self._draw_cell(cell)

    def _resize_surface(self, size: tuple[int, int]) -> None:
        pygame.display.set_mode(size, pygame.RESIZABLE)

    def _update_maze(self) -> None:
        up, cell = self._solution[self._index].split(' ', maxsplit=1)
        x, y = map(int, cell.split(',', maxsplit=1))
        cell = self._maze.get_cell(x, y)
        if up == 'added':
            cell.set_value(6)
        elif up == 'visited':
            cell.set_value(5)
        elif up == 'solving':
            cell.set_value(4)
        self._draw_cell(cell)
        pygame.display.flip()
        self._index += 1

    def _draw_cell(self, cell: Cell) -> None:
        left_margin = self._screen.get_width() // 5
        top_margin = self._screen.get_height() // 5
        cell_width = int(self._screen.get_width() // self._maze.get_width() * .6)
        cell_height = int(self._screen.get_height() // self._maze.get_height() * .6)
        x = cell.get_x() * cell_width + left_margin
        y = cell.get_y() * cell_height + top_margin

        color = COLORS[cell.get_value()]

        pygame.draw.rect(self._screen, color, (x, y, cell_width, cell_height), 100)

