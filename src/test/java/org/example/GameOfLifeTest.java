package org.example;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GameOfLifeTest {

    GameOfLife uut = new GameOfLife();

    @Test
    void gameOfLife_shouldBeCreated() {
        // Assert
        assertThat(uut).isNotNull();
    }

    @Test
    void whenCalculateNextGeneration_thenReturnNewGrid() {
        // Arrange
        final var grid = new Grid(3, 3);

        // Act
        Grid nextGeneration = uut.calculateNextGeneration(grid);

        // Assert
        assertThat(nextGeneration)
                .isNotSameAs(grid)
                .isInstanceOf(Grid.class);
    }

    @Test
    void whenCalculateNextGeneration_with_thenReturnNewGridWithCorrectCellStates() {
        // Arrange
        final var grid = new Grid(3, 3);
        grid.setLiveCell(1, 0);
        grid.setLiveCell(1, 1);
        grid.setLiveCell(0, 1);

        // Act
        Grid nextGeneration = uut.calculateNextGeneration(grid);

        // Assert
        Grid expected = new Grid(3, 3);
        expected.setLiveCell(0, 0);
        expected.setLiveCell(1, 0);
        expected.setLiveCell(1, 1);
        expected.setLiveCell(0, 1);

        assertThat(nextGeneration).usingRecursiveComparison().isEqualTo(expected);
    }
}
