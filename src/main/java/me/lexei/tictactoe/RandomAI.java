package me.lexei.tictactoe;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * AI that makes moves on random positions
 */
public class RandomAI extends Player {

    private static final ThreadLocalRandom random = ThreadLocalRandom.current();

    public RandomAI(final Board board, final char symbol) {
        super(board, symbol);
    }

    @Override
    public void move() {
        var board = getBoard();
        var emptyCells = new ArrayList<>(board.getEmptyCells());
        var randomPosition = emptyCells.get(random.nextInt(emptyCells.size()));
        board.setCell(randomPosition, getSymbol());
    }
}
