package me.lexei.tictactoe;

import java.util.Optional;

public class MediumAI extends RandomAI {

    public static final String LABEL = "medium";

    public MediumAI(final Board board, final char symbol) {
        super(board, symbol);
    }

    /**
     * Makes moves using the following logic
     * 1) If it already has two in a row and can win with one further move, it does so.
     * 2) If its opponent can win with one move, it plays the move necessary to block this.
     * 3) Otherwise, it makes a random move.
     */
    @Override
    public void move() {
        System.out.printf("Making move level \"%s\"\n", LABEL);
        var board = getBoard();
        var symbol = getSymbol();
        var opponentSymbol = getOpponentSymbol();
        getWinningPosition(symbol)
                .ifPresentOrElse(position -> board.setCell(position, symbol),
                        () -> getWinningPosition(opponentSymbol)
                                .ifPresentOrElse(position -> board.setCell(position, symbol),
                                        super::move));
    }

    private Optional<Position> getWinningPosition(final char symbol) {
        var copiedBoard = new Board(getBoard());
        for (var position : getBoard().getEmptyCells()) {
            copiedBoard.setCell(position, symbol);
            if (copiedBoard.hasThreeInRow(symbol)) {
                return Optional.of(position);
            }
            copiedBoard.setCell(position, Board.EMPTY_CELL);
        }
        return Optional.empty();
    }
}
