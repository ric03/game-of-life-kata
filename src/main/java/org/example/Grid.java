package org.example;

import java.util.Arrays;

public class Grid {

    CellState[][] gridArray;

    Grid() {
    }

    Grid(int x, int y) {
        this.createGrid(x, y);
    }

    void createGrid(int x, int y) {
        gridArray = new CellState[x][y];

        for (int i = 0; i < x; i++) {
            Arrays.fill(gridArray[i], CellState.DEAD);
        }
    }

    void setLiveCell(int x, int y) {
        gridArray[x][y] = CellState.ALIVE;
    }

    CellState getCellState(int x, int y) {
        int xLength = this.getHorizontalLength();
        int yLength = this.getVerticalLength();

        if (x < 0 || y < 0 || x >= xLength || y >= yLength) {
            return CellState.DEAD;
        }
        return gridArray[x][y];
    }

    public int getVerticalLength() {
        return gridArray[0].length;
    }

    public int getHorizontalLength() {
        return gridArray.length;
    }
}
