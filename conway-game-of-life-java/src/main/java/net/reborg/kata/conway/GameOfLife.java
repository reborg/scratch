package net.reborg.kata.conway;

import java.util.Arrays;

public class GameOfLife
{
    private String currentGrid;

    public void initialise(String initialGrid) {
        currentGrid = initialGrid;
    }

    /**
     * Calculate the next generation in the game of life given
     * the actual internal state.
     * @return A printer friendly rendering of the next generation
     */
    public String tick() {

        String block = "**.\n" +
                       "**.\n" +
                       "...";

        String emptyGrid = "...\n" +
                           "...\n" +
                           "...";

        if(currentGrid.equals(block))
            return block;
        else if(currentGrid.equals(emptyGrid))
            return "...\n...\n...";
        else {
            String[] cells = currentGrid.split("");
            int[] counts = new int[cells.length];
            String[] nextGen = new String[cells.length];

            for (int i = 0; i < cells.length; i++) {
                counts[i] = countNeighbours(cells[i], currentGrid);
                nextGen[i] = decideIfDeadOrAlive(cells[i], counts[i]);
            }

            return Arrays.toString(nextGen);
        }

    }

    private String decideIfDeadOrAlive(String currentCell, int neighboursCount) {
        return "*";
    }

    private int countNeighbours(String currentCell, String currentGrid) {
        return 0;
    }
}
