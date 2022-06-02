package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class GameOfLifeTest {

    private static final Position LEFT_UP = new Position(0, 0);
    private static final Position LEFT_CENTER = new Position(0, 1);
    private static final Position LEFT_BOTTOM = new Position(0, 2);
    private static final Position CENTER_TOP = new Position(1, 0);
    private static final Position CENTER = new Position(1, 1);
    private static final Position CENTER_BOTTOM = new Position(1, 2);
    private static final Position RIGHT_TOP = new Position(2, 0);
    private static final Position RIGHT_CENTER = new Position(2, 1);
    private static final Position RIGHT_BOTTOM = new Position(2, 2);

    GameOfLife uut = new GameOfLife();

    @Test
    void gameOfLife_shouldBeCreated() {
        // Assert
        assertThat(uut).isNotNull();
    }

    static Stream<Position> whenGetCellStateForNextGeneration_withFewerThanTwoLiveNeighbors_thenAnyLifeCellDies() {
        return Stream.of(
                LEFT_UP,
                LEFT_CENTER,
                LEFT_BOTTOM,
                CENTER_TOP,
                CENTER,
                CENTER_BOTTOM,
                RIGHT_TOP,
                RIGHT_CENTER,
                RIGHT_BOTTOM
        );
    }

    @ParameterizedTest
    @MethodSource
    void whenGetCellStateForNextGeneration_withFewerThanTwoLiveNeighbors_thenAnyLifeCellDies(Position positionOfSurroundingLiveCell) {
        // Arrange
        final var grid = new Grid(3, 3);
        grid.setLiveCell(positionOfSurroundingLiveCell.x(), positionOfSurroundingLiveCell.y());
        grid.setLiveCell(CENTER.x(), CENTER.y());

        // Act
        var cellStateInNextGeneration = uut.getCellStateForNextGeneration(grid, 1, 1);
        // Assert
        assertThat(cellStateInNextGeneration).isEqualTo(CellState.DEAD);
    }


    static Stream<List<Position>> whenGetCellStateForNextGeneration_withMoreThanThreeLiveNeighbors_thenCellDies() {
        return Stream.of(
                List.of(LEFT_UP, LEFT_CENTER, LEFT_BOTTOM, CENTER_TOP),
                List.of(RIGHT_TOP, RIGHT_CENTER, RIGHT_BOTTOM, CENTER_BOTTOM)
        );
    }

    @ParameterizedTest
    @MethodSource
    void whenGetCellStateForNextGeneration_withMoreThanThreeLiveNeighbors_thenCellDies(List<Position> positionsOfSurroundingLiveCells) {
        // Arrange
        final var grid = new Grid(3, 3);
        for (var pos : positionsOfSurroundingLiveCells) {
            grid.setLiveCell(pos.x(), pos.y());
        }
        grid.setLiveCell(CENTER.x(), CENTER.y());
        // Act
        var cellStateInNextGeneration = uut.getCellStateForNextGeneration(grid, 1, 1);
        // Assert
        assertThat(cellStateInNextGeneration).isEqualTo(CellState.DEAD);
    }

    static Stream<List<Position>> whenGetCellStateForNextGeneration_withTwoLiveNeighbors_thenCellLivesOn() {
        return Stream.of(
                List.of(LEFT_UP, LEFT_CENTER),
                List.of(LEFT_BOTTOM, CENTER_BOTTOM),
                List.of(RIGHT_BOTTOM, RIGHT_CENTER),
                List.of(RIGHT_TOP, CENTER_TOP),
                List.of(LEFT_UP, RIGHT_BOTTOM)
        );
    }

    @ParameterizedTest
    @MethodSource
    void whenGetCellStateForNextGeneration_withTwoLiveNeighbors_thenCellLivesOn(List<Position> positionsOfSurroundingLiveCells) {
        // Arrange
        final var grid = new Grid(3, 3);
        for (var pos : positionsOfSurroundingLiveCells) {
            grid.setLiveCell(pos.x(), pos.y());
        }
        grid.setLiveCell(CENTER.x(), CENTER.y());

        // Act
        var cellStateInNextGeneration = uut.getCellStateForNextGeneration(grid, 1, 1);
        // Assert
        assertThat(cellStateInNextGeneration).isEqualTo(CellState.ALIVE);
    }

    @Test
    void whenGetCellStateForNextGeneration_withThreeLiveNeighbors_thenCellLivesOn() {
        // Arrange
        final var grid = new Grid(3, 3);
        grid.setLiveCell(0, 0);
        grid.setLiveCell(0, 1);
        grid.setLiveCell(0, 2);
        grid.setLiveCell(1, 1);

        // Act
        var cellStateInNextGeneration = uut.getCellStateForNextGeneration(grid, 1, 1);
        // Assert
        assertThat(cellStateInNextGeneration).isEqualTo(CellState.ALIVE);
    }

    @Test
    void whenGetCellStateForNextGeneration_withExactlyThreeLiveNeighbors_thenDeadCellBecomesAlive() {
        // Arrange
        final var grid = new Grid(3, 3);
        grid.setLiveCell(0, 0);
        grid.setLiveCell(0, 1);
        grid.setLiveCell(0, 2);

        // Act
        var isCellAliveInNextGeneration = uut.getCellStateForNextGeneration(grid, 1, 1);
        // Assert
        assertThat(isCellAliveInNextGeneration).isEqualTo(CellState.ALIVE);
    }

    @Test
    void whenGetCellStateForNextGeneration_withTwoLiveNeighbors_thenDeadCellStaysDead() {
        // Arrange
        final var grid = new Grid(3, 3);
        grid.setLiveCell(0, 0);
        grid.setLiveCell(0, 1);

        // Act
        var cellStateInNextGeneration = uut.getCellStateForNextGeneration(grid, 1, 1);
        // Assert
        assertThat(cellStateInNextGeneration).isEqualTo(CellState.DEAD);
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
