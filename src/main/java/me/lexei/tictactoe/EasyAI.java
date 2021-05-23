package me.lexei.tictactoe;

public class EasyAI extends RandomAI {

    public static final String LABEL = "easy";

    public EasyAI(final Board board, final char symbol) {
        super(board, symbol);
    }

    @Override
    public void move() {
        System.out.printf("Making move level \"%s\"\n", LABEL);
        super.move();
    }
}
