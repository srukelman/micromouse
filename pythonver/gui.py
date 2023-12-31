import pygame
from cell import Cell
from maze import Maze

class View:
    def __init__(self, maze: list[list[Cell]]) -> None:
        self._maze = Maze(maze)
        self._running = True
    
    def run(self) -> None:
        pygame.init()
        X , Y = 800, 800
        self._screen = pygame.display.set_mode((X, Y), pygame.RESIZABLE)
        self._draw()
        pygame.display.set_caption('Maze Solver')
        clock = pygame.time.Clock()
        while self._running:
            clock.tick(10)
        pygame.quit()

    def _handle_events(self):
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                self._running = False
            elif event.type == pygame.VIDEORESIZE:
                self._resize_surface(event.size)
            elif event.type == pygame.KEYDOWN:
                if event.key == pygame.K_LEFT:
                    return self._board.update_faller('<')
                elif event.key == pygame.K_RIGHT:
                    return self._board.update_faller('>')
                elif event.key == pygame.K_SPACE:
                    self._board.update_faller('R')

    def _draw(self) -> None:
        self._screen.fill((150, 150, 150)) # gray background color
        self._draw_maze()
        pygame.display.flip()

    def _draw_maze(self) -> None:
        board_state = self._maze.get_maze()
        for row in board_state:
            for cell in row:
                self._draw_cell(cell)

    def _draw_cell(self, cell: Cell) -> None:
        left_margin = self._screen.get_width() // 5
        top_margin = self._screen.get_height() // 5
        cell_width = self._screen.get_width() // int(self._maze.get_width() * .9)
        cell_height = self._screen.get_height() // int(self._maze.get_height() * .9)
        x = cell.get_x() * cell_width + left_margin
        y = cell.get_y() * cell_height + top_margin

        pygame.draw.rect(self._screen, (0, 0, 0), (x, y, cell_width, cell_height), 1)

