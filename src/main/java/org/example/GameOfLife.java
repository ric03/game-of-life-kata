package org.example;

public class GameOfLife {

    CellState getCellStateForNextGeneration(Grid grid, int x, int y) {
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

    public Grid calculateNextGeneration(Grid grid) {
        Grid newGrid = new Grid(grid.getHorizontalLength(), grid.getVerticalLength());

        for (int x = 0; x < grid.getHorizontalLength(); x++) {
            for (int y = 0; y < grid.getVerticalLength(); y++) {
                CellState newCellState = this.getCellStateForNextGeneration(grid, x, y);
                newGrid.setState(x, y, newCellState);
            }
        }

        return newGrid;
    }
}
