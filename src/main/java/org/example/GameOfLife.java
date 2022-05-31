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

    public Grid calculateNextGeneration() {
        Grid newGrid = new Grid();
        newGrid.createGrid(this.grid.gridArray.length, this.grid.gridArray[0].length);

        for(int x = 0; x < this.grid.gridArray.length; x++) {
            for(int y = 0; y < this.grid.gridArray[0].length; y++) {
                newGrid.gridArray[x][y] = this.getCellStateForNextGeneration(x, y);
            }
        }

        return newGrid;
    }
}
