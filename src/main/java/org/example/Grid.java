package org.example;

import java.util.Arrays;

public class Grid {

    CellState[][] gridArray;

    void createGrid(int x, int y) {
        gridArray = new CellState[x][y];

        for (int _x = 0; _x < x; _x++) {
            Arrays.fill(gridArray[_x], CellState.DEAD);
        }
    }

    void setLiveCell(int x, int y) {
        gridArray[x][y] = CellState.ALIVE;
    }

    CellState getCellState(int x, int y) {
        return gridArray[x][y];
    }
}
