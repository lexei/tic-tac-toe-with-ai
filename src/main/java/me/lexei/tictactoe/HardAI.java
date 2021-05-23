package me.lexei.tictactoe;

/**
 * Hard AI moves based on minimax algorithm
 */
public class HardAI extends Player {

    private static final int WIN_SCORE = 1;
    private static final int LOSE_SCORE = -1;
    private static final int DRAW_SCORE = 0;

    public static final String LABEL = "hard";

    private static class Move implements Comparable<Move> {

        final int score;
        final Position position;

        Move(final int score, final Position position) {
            this.score = score;
            this.position = position;
        }

        @Override
        public int compareTo(Move otherMove) {
            return this.score - otherMove.score;
        }
    }

    public HardAI(final Board board, final char symbol) {
        super(board, symbol);
    }

    @Override
    public void move() {
        System.out.printf("Making move level \"%s\"\n", LABEL);
        var board = getBoard();
        var symbol = getSymbol();
        var bestMove = findBestMove(board, symbol, null);
        board.setCell(bestMove.position, symbol);
    }

    /**
     * Finds best move available
     *
     * @param curBoard    - current board in which current move happens
     * @param curSymbol   - current symbol of the move (X or O)
     * @param curPosition - current position of the move
     * @return move with greatest score
     */
    private Move findBestMove(final Board curBoard, final char curSymbol, final Position curPosition) {
        // if AI wins return +1 score
        if (curBoard.hasThreeInRow(getSymbol())) {
            return new Move(WIN_SCORE, curPosition);
        }
        // if AI loses return -1 score
        if (curBoard.hasThreeInRow(getOpponentSymbol())) {
            return new Move(LOSE_SCORE, curPosition);
        }
        // if draw return 0 score
        if (!curBoard.hasEmptyCells()) {
            return new Move(DRAW_SCORE, curPosition);
        }
        // recursively search for move with highest score
        var bestMove = new Move(DRAW_SCORE, null);
        for (var nextPosition : curBoard.getEmptyCells()) {
            var nextBoard = new Board(curBoard);
            nextBoard.setCell(nextPosition, curSymbol);
            var nextMove = findBestMove(nextBoard, getOppositeSymbol(curSymbol), nextPosition);
            if (bestMove.position == null || isBetterScore(nextMove, bestMove, curSymbol)) {
                bestMove = new Move(nextMove.score, nextPosition);
            }
        }
        return bestMove;
    }

    /**
     * Determines better move (with greater score for AI, with lower for other player)
     * Maximises ai move score, minimises other player move score
     *
     * @param first         move
     * @param second        move
     * @param currentSymbol - moving symbol
     * @return true if better else false
     */
    private boolean isBetterScore(final Move first, final Move second, final char currentSymbol) {
        if (currentSymbol == getSymbol()) {
            return first.compareTo(second) > 0;
        }
        return first.compareTo(second) < 0;
    }

    private char getOppositeSymbol(final char symbol) {
        return symbol == Player.CROSS ? Player.ZERO : Player.CROSS;
    }
}
