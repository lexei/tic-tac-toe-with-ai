package me.lexei.tictactoe;

public abstract class Player {

    public static final char CROSS = 'X';
    public static final char ZERO = 'O';

    private final Board board;
    private final char symbol;

    public Player(final Board board, final char symbol) {
        this.board = board;
        this.symbol = symbol;
    }

    public Board getBoard() {
        return board;
    }

    public char getSymbol() {
        return symbol;
    }

    public abstract void move();

    public boolean isWinner() {
        return board.hasThreeInRow(symbol);
    }

    public static boolean iaValidLabel(final String label) {
        return User.LABEL.equals(label)
                || EasyAI.LABEL.equals(label)
                || MediumAI.LABEL.equals(label)
                || HardAI.LABEL.equals(label);
    }

    public static Player fromLabel(final String label, final Board board, final char symbol) {
        if (User.LABEL.equals(label)) {
            return new User(board, symbol);
        }
        if (EasyAI.LABEL.equals(label)) {
            return new EasyAI(board, symbol);
        }
        if (MediumAI.LABEL.equals(label)) {
            return new MediumAI(board, symbol);
        }
        if (HardAI.LABEL.equals(label)) {
            return new HardAI(board, symbol);
        }
        throw new IllegalArgumentException("Unknown label :(");
    }

    public char getOpponentSymbol() {
        return symbol == CROSS ? ZERO : CROSS;
    }
}
