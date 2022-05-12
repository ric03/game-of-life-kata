package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GameOfLifeTest {

    GameOfLife uut = new GameOfLife();

    @BeforeEach
    void setup() {
        uut.grid.createGrid(3, 3);
    }

    @Test
    void gameOfLife_shouldBeCreated() {
        // Assert
        assertThat(uut).isNotNull();
    }

    @Test
    void whenGetCellStateForNextGeneration_withFewerThanTwoLiveNeighbors_thenAnyLifeCellDies() {
        // Arrange
        uut.grid.setLiveCell(1, 0);
        uut.grid.setLiveCell(1, 1);

        // Act
        var isCellAliveInNextGeneration = uut.getCellStateForNextGeneration(1, 1);
        // Assert
        assertThat(isCellAliveInNextGeneration).isEqualTo(CellState.DEAD);
    }

    @Test
    void whenGetCellStateForNextGeneration_withMoreThanThreeLiveNeighbors_thenCellDies() {
        // Arrange
        uut.grid.setLiveCell(0, 0);
        uut.grid.setLiveCell(0, 1);
        uut.grid.setLiveCell(0, 2);
        uut.grid.setLiveCell(1, 0);
        uut.grid.setLiveCell(1, 1);

        // Act
        var isCellAliveInNextGeneration = uut.getCellStateForNextGeneration(1, 1);
        // Assert
        assertThat(isCellAliveInNextGeneration).isEqualTo(CellState.DEAD);
    }

    @Test
    void whenGetCellStateForNextGeneration_withTwoLiveNeighbors_thenCellLivesOn() {
        // Arrange
        uut.grid.setLiveCell(0, 0);
        uut.grid.setLiveCell(0, 1);
        uut.grid.setLiveCell(1, 1);

        // Act
        var isCellAliveInNextGeneration = uut.getCellStateForNextGeneration(1, 1);
        // Assert
        assertThat(isCellAliveInNextGeneration).isEqualTo(CellState.ALIVE);
    }

    @Test
    void whenGetCellStateForNextGeneration_withThreeLiveNeighbors_thenCellLivesOn() {
        // Arrange
        uut.grid.setLiveCell(0, 0);
        uut.grid.setLiveCell(0, 1);
        uut.grid.setLiveCell(0, 2);
        uut.grid.setLiveCell(1, 1);

        // Act
        var isCellAliveInNextGeneration = uut.getCellStateForNextGeneration(1, 1);
        // Assert
        assertThat(isCellAliveInNextGeneration).isEqualTo(CellState.ALIVE);
    }

    @Test
    void whenGetCellStateForNextGeneration_withExactlyThreeLiveNeighbors_thenDeadCellBecomesAlive() {
        // Arrange
        uut.grid.setLiveCell(0, 0);
        uut.grid.setLiveCell(0, 1);
        uut.grid.setLiveCell(0, 2);

        // Act
        var isCellAliveInNextGeneration = uut.getCellStateForNextGeneration(1, 1);
        // Assert
        assertThat(isCellAliveInNextGeneration).isEqualTo(CellState.ALIVE);
    }

    @Test
    void whenGetCellStateForNextGeneration_withTwoLiveNeighbors_thenDeadCellStaysDead() {
        // Arrange
        uut.grid.setLiveCell(0, 0);
        uut.grid.setLiveCell(0, 1);

        // Act
        var isCellAliveInNextGeneration = uut.getCellStateForNextGeneration(1, 1);
        // Assert
        assertThat(isCellAliveInNextGeneration).isEqualTo(CellState.DEAD);
    }
}
