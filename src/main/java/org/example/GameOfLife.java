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
        Grid newGrid = new Grid();
        newGrid.createGrid(grid.gridArray.length, grid.gridArray[0].length);

        for(int x = 0; x < grid.gridArray.length; x++) {
            for(int y = 0; y < grid.gridArray[0].length; y++) {
                newGrid.gridArray[x][y] = this.getCellStateForNextGeneration(grid, x, y);
            }
        }

        return newGrid;
    }
}
