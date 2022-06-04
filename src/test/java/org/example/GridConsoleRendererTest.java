package org.example;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GridConsoleRendererTest {

    GridConsoleRenderer uut = new GridConsoleRenderer();

    @Test
    void givenGrid_whenRender_thenReturnProperlyFormattedMultilineString() {
        // Arrange
        final var grid = new Grid(3, 2);
        grid.setLiveCell(0,0);
        grid.setLiveCell(1,1);
        // Act
        var renderedString = uut.render(grid);
        // Assert
        String expectedString = """
                *..
                .*.
                """.stripIndent().stripLeading().stripTrailing();
        assertThat(renderedString).isEqualTo(expectedString);
    }
}
