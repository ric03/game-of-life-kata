package org.example;

import org.junit.jupiter.api.BeforeEach;
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

    @BeforeEach
    void setup() {
        uut.grid.createGrid(3, 3);
    }

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
        uut.grid.setLiveCell(positionOfSurroundingLiveCell.getX(), positionOfSurroundingLiveCell.getY());
        uut.grid.setLiveCell(1, 1);

        // Act
        var isCellAliveInNextGeneration = uut.getCellStateForNextGeneration(1, 1);
        // Assert
        assertThat(isCellAliveInNextGeneration).isEqualTo(CellState.DEAD);
    }


    static Stream<List<Position>> whenGetCellStateForNextGeneration_withMoreThanThreeLiveNeighbors_thenCellDies() {
        return Stream.of(
                List.of(LEFT_UP, LEFT_CENTER, LEFT_BOTTOM, CENTER_TOP),
                List.of(RIGHT_TOP, RIGHT_CENTER, RIGHT_BOTTOM, CENTER_BOTTOM)
        );
    }

    @ParameterizedTest
    @MethodSource
    void whenGetCellStateForNextGeneration_withMoreThanThreeLiveNeighbors_thenCellDies(List<Position> positionsOfsurroundingLiveCells) {
        // Arrange
        for (var pos : positionsOfsurroundingLiveCells) {
            uut.grid.setLiveCell(pos.getX(), pos.getY());
        }
        uut.grid.setLiveCell(1, 1);
        // Act
        var isCellAliveInNextGeneration = uut.getCellStateForNextGeneration(1, 1);
        // Assert
        assertThat(isCellAliveInNextGeneration).isEqualTo(CellState.DEAD);
    }

    static Stream<List<Position>> whenGetCellStateForNextGeneration_withTwoLiveNeighbors_thenCellLivesOn() {
        return Stream.of(
                List.of(LEFT_UP, LEFT_CENTER),
                List.of(LEFT_BOTTOM, CENTER_BOTTOM),
                List.of(RIGHT_BOTTOM, RIGHT_CENTER),
                List.of(RIGHT_TOP, CENTER_TOP)
        );
    }

    @ParameterizedTest
    @MethodSource
    void whenGetCellStateForNextGeneration_withTwoLiveNeighbors_thenCellLivesOn(List<Position> positionsOfSurroundingLiveCells) {
        // Arrange
        for (var pos : positionsOfSurroundingLiveCells) {
            uut.grid.setLiveCell(pos.getX(), pos.getY());
        }
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
