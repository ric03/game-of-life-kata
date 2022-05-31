# Game of Life Kata

https://codingdojo.org/kata/GameOfLife/

### Rules

1. Any live cell with fewer than two live neighbours dies, as if caused by underpopulation.
2. Any live cell with more than three live neighbours dies, as if by overcrowding.
3. Any live cell with two or three live neighbours lives on to the next generation.
4. Any dead cell with exactly three live neighbours becomes a live cell.

## Mutation testing with Pitest

The following command will execute pitest

    $ gradle pitest

This will output a html report to `build/report/pitest/YYYYMMDDHHMI`.
Open the generated report in your browser.

Visit https://pitest.org/ for more information.
