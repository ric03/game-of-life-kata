package org.example;

public class GridConsoleRenderer {

    public String render(Grid grid) {
        StringBuilder sb = new StringBuilder();

        for (int y = 0; y < grid.getVerticalLength(); y++) {
            for (int x = 0; x < grid.getHorizontalLength(); x++) {
                sb.append(grid.getCellState(x, y).getSign());
            }
            sb.append("\n");
        }

        return sb.toString().stripTrailing();
    }

}
