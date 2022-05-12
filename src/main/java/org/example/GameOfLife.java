package org.example;

public class GameOfLife {

    CellState[][] grid;

    void createGrid(int x, int y) {
        this.grid = new CellState[x][y];
    }

    void setLiveCell(int x, int y) {
        this.grid[x][y] = CellState.ALIVE;
    }

    CellState getCellState(int x, int y) {
        return grid[x][y];
    }

    CellState getCellStateForNextGeneration(int x, int y) {
        var countOfSurroundingLiveCells = 0;

        if (getCellState(x-1, y-1) == CellState.ALIVE) { countOfSurroundingLiveCells++; }
        if (getCellState(x, y-1) == CellState.ALIVE) { countOfSurroundingLiveCells++; }
        if (getCellState(x+1, y-1) == CellState.ALIVE) { countOfSurroundingLiveCells++; }
        if (getCellState(x-1, y) == CellState.ALIVE) { countOfSurroundingLiveCells++; }
        if (getCellState(x+1, y) == CellState.ALIVE) { countOfSurroundingLiveCells++; }
        if (getCellState(x-1, y+1) == CellState.ALIVE) { countOfSurroundingLiveCells++; }
        if (getCellState(x, y+1) == CellState.ALIVE) { countOfSurroundingLiveCells++; }
        if (getCellState(x+1, y+1) == CellState.ALIVE) { countOfSurroundingLiveCells++; }

        if(countOfSurroundingLiveCells < 2 || countOfSurroundingLiveCells > 3) {
            return CellState.DEAD;
        } else {
            return CellState.ALIVE;
        }
    }
}
