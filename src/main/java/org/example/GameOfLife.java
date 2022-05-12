package org.example;

public class GameOfLife {

    Grid grid = new Grid();

    CellState getCellStateForNextGeneration(int x, int y) {
        var countOfSurroundingLiveCells = 0;

        if (grid.getCellState(x - 1, y - 1) == CellState.ALIVE) {
            countOfSurroundingLiveCells++;
        }
        if (grid.getCellState(x, y - 1) == CellState.ALIVE) {
            countOfSurroundingLiveCells++;
        }
        if (grid.getCellState(x + 1, y - 1) == CellState.ALIVE) {
            countOfSurroundingLiveCells++;
        }
        if (grid.getCellState(x - 1, y) == CellState.ALIVE) {
            countOfSurroundingLiveCells++;
        }
        if (grid.getCellState(x + 1, y) == CellState.ALIVE) {
            countOfSurroundingLiveCells++;
        }
        if (grid.getCellState(x - 1, y + 1) == CellState.ALIVE) {
            countOfSurroundingLiveCells++;
        }
        if (grid.getCellState(x, y + 1) == CellState.ALIVE) {
            countOfSurroundingLiveCells++;
        }
        if (grid.getCellState(x + 1, y + 1) == CellState.ALIVE) {
            countOfSurroundingLiveCells++;
        }

        if (countOfSurroundingLiveCells == 2 && grid.getCellState(x, y) == CellState.DEAD) {
            return CellState.DEAD;
        }
        if (countOfSurroundingLiveCells < 2 || countOfSurroundingLiveCells > 3) {
            return CellState.DEAD;
        } else {
            return CellState.ALIVE;
        }
    }
}
