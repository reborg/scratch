package net.reborg.kata.conway.tests;

import net.reborg.kata.conway.GameOfLife;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class GameOfLifeTest {

    @Test
    public void shouldNotPopulateAnEmptyGrid() {
        String expected = "...\n...\n...";
        String emptyGrid = "...\n...\n...";
        GameOfLife gameOfLife = new GameOfLife();
        gameOfLife.initialise(emptyGrid);
        assertThat("No new life should appear", gameOfLife.tick(), is(expected));
    }

    @Test
    public void aBlockStaysAliveTheNextGeneration() {
        String block = "**.\n" +
                       "**.\n" +
                       "...";
        GameOfLife gameOfLife = new GameOfLife();
        gameOfLife.initialise(block);
        assertThat("A block is a form of still life", gameOfLife.tick(), is(block));
    }

    @Test
    public void aLiveCellWithExactlyTwoLiveNeighbourShouldRemainAliveInTheNextGeneration() {
        String oscillator = ".*.\n" +
                            ".*.\n" +
                            ".*.";

        String expected =   "...\n" +
                            "***\n" +
                            "...";

        GameOfLife gameOfLife = new GameOfLife();
        gameOfLife.initialise(oscillator);
        assertThat("Middle cell keeps living", gameOfLife.tick(), is(expected));
    }
}
