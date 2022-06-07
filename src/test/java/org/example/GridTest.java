package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class GridTest {

    Grid uut = new Grid();

    @Test
    void whenCreateGrid_thenGridHasGivenSize() {
        // Act
        uut.createGrid(3, 5);
        // Assert
        assertThat(uut.getGridArray()).hasDimensions(3, 5);
    }

    @Test
    void whenCreateGrid_thenArrayShouldContainDeadCells() {
        // Act
        uut.createGrid(2, 10);
        // Assert
        assertThat(uut.getGridArray()[0]).contains(CellState.DEAD);
        assertThat(uut.getGridArray()[1]).contains(CellState.DEAD);
    }

    @Test
    void whenCreateGrid_thenArrayShouldNotContainLiveCells() {
        // Act
        uut.createGrid(2, 10);
        // Assert
        assertThat(uut.getGridArray()[0]).doesNotContain(CellState.ALIVE);
        assertThat(uut.getGridArray()[1]).doesNotContain(CellState.ALIVE);
    }

    @Test
    void givenValidPositionOnSquareGrid_whenGetCellState_thenReturnCellState() {
        // Arrange
        uut.createGrid(1, 1);
        uut.setLiveCell(0, 0);
        // Act
        CellState cellState = uut.getCellState(0, 0);
        // Assert
        assertThat(cellState).isEqualTo(CellState.ALIVE);
    }

    @Test
    void givenValidPositionOnHorizontallyLongerGrid_whenGetCellState_thenReturnCellState() {
        // Arrange
        uut.createGrid(3, 1);
        uut.setLiveCell(2, 0);
        // Act
        CellState cellState = uut.getCellState(2, 0);
        // Assert
        assertThat(cellState).isEqualTo(CellState.ALIVE);
    }

    @Test
    void givenValidPositionOnVerticallyLongerGrid_whenGetCellState_thenReturnCellState() {
        // Arrange
        uut.createGrid(1, 3);
        uut.setLiveCell(0, 2);
        // Act
        CellState cellState = uut.getCellState(0, 2);
        // Assert
        assertThat(cellState).isEqualTo(CellState.ALIVE);
    }

    @ParameterizedTest
    @CsvSource({"-1,-1", "-1,0", "0,-1"})
    void givenLowerOutOfBoundPosition_whenGetCellState_thenReturnDeadCellState(int x, int y) {
        // Arrange
        uut.createGrid(1, 1);
        // Act
        CellState cellState = uut.getCellState(x, y);
        // Assert
        assertThat(cellState).isEqualTo(CellState.DEAD);
    }

    @ParameterizedTest
    @CsvSource({"1,1", "1,0", "0,1"})
    void givenUpperOutOfBoundPositionOnSquareGrid_whenGetCellState_thenReturnDeadCellState(int x, int y) {
        // Arrange
        uut.createGrid(1, 1);
        // Act
        CellState cellState = uut.getCellState(x, y);
        // Assert
        assertThat(cellState).isEqualTo(CellState.DEAD);
    }

    @ParameterizedTest
    @CsvSource({"1,3", "3,0", "0,3"})
    void givenUpperOutOfBoundPositionOnVerticallyLongerGrid_whenGetCellState_thenReturnDeadCellState(int x, int y) {
        // Arrange
        uut.createGrid(1, 3);
        // Act
        CellState cellState = uut.getCellState(x, y);
        // Assert
        assertThat(cellState).isEqualTo(CellState.DEAD);
    }


    @ParameterizedTest
    @CsvSource({"3,1", "3,0", "0,3"})
    void givenUpperOutOfBoundPositionOnHorizontallyLongerGrid_whenGetCellState_thenReturnDeadCellState(int x, int y) {
        // Arrange
        uut.createGrid(3, 1);
        // Act
        CellState cellState = uut.getCellState(x, y);
        // Assert
        assertThat(cellState).isEqualTo(CellState.DEAD);
    }
}
