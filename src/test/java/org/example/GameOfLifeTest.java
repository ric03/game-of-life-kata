package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GameOfLifeTest {

    GameOfLife uut = new GameOfLife();

    @BeforeEach
    void setup() {
        uut.createGrid(3, 3);
    }

    @Test
    void gameOfLife_shouldBeCreated() {
        // Assert
        assertThat(uut).isNotNull();
    }

    /**
     * 1. Any live cell with fewer than two live neighbours dies, as if caused by underpopulation.
     */
    @Test
    void whenUpdateCellWithFewerThanTwoLiveNeighbors_thenAnyLifeCellDies() {
        // Arrange
        uut.setLiveCell(1, 0);
        uut.setLiveCell(1, 1);

        // Act
        var isCellAliveInNextGeneration = uut.getCellStateForNextGeneration(1, 1);
        // Assert
        assertThat(isCellAliveInNextGeneration).isEqualTo(CellState.DEAD);
    }

    /**
     * Any live cell with more than three live neighbours dies, as if by overcrowding.
     */
    @Test
    void whenUpdateCellWithMoreThanThreeLiveNeighbors_thenCellDies() {
        // Arrange
        uut.setLiveCell(0, 0);
        uut.setLiveCell(0, 1);
        uut.setLiveCell(0, 2);
        uut.setLiveCell(1, 0);
        uut.setLiveCell(1, 1);

        // Act
        var isCellAliveInNextGeneration = uut.getCellStateForNextGeneration(1, 1);
        // Assert
        assertThat(isCellAliveInNextGeneration).isEqualTo(CellState.DEAD);
    }
}