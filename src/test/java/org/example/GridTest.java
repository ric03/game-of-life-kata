package org.example;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GridTest {

    Grid uut = new Grid();

    @Test
    void whenCreateGrid_thenGridHasGivenSize() {
        // Act
        uut.createGrid(3, 5);
        // Assert
        assertThat(uut.gridArray).hasDimensions(3, 5);
    }

    @Test
    void whenCreateGrid_thenArrayShouldContainDeadCells() {
        // Act
        uut.createGrid(2, 10);
        // Assert
        assertThat(uut.gridArray[0]).contains(CellState.DEAD);
        assertThat(uut.gridArray[1]).contains(CellState.DEAD);
    }

    @Test
    void whenCreateGrid_thenArrayShouldNotContainLiveCells() {
        // Act
        uut.createGrid(2, 10);
        // Assert
        assertThat(uut.gridArray[1]).doesNotContain(CellState.ALIVE);
    }

    @Test
    void whenGetCellState_withValidPosition_thenReturnCellState() {
        // Arrange
        uut.createGrid(1, 1);
        uut.setLiveCell(0, 0);
        // Act
        CellState cellState = uut.getCellState(0, 0);
        // Assert
        assertThat(cellState).isEqualTo(CellState.ALIVE);
    }

    @Test
    void whenGetCellState_withOutOfBoundPosition_thenReturnDead() {
        // Act
        CellState cellState = uut.getCellState(-1, -1);
        // Assert
        assertThat(cellState).isEqualTo(CellState.DEAD);
    }
}
