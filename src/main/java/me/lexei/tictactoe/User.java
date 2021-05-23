package me.lexei.tictactoe;

import java.util.Scanner;
import java.util.regex.Pattern;

public class User extends Player {

    private static final Pattern NUMBER_PATTERN = Pattern.compile("\\d+");

    public static final String LABEL = "user";

    private static final Scanner scanner = new Scanner(System.in);

    public User(final Board board, final char symbol) {
        super(board, symbol);
    }

    @Override
    public void move() {
        while (true) {
            System.out.print("Enter the coordinates: ");
            var coordinates = scanner.nextLine().split(" ");
            if (coordinates.length == 2 && isNumber(coordinates[0]) && isNumber(coordinates[1])) {
                var y = Integer.parseInt(coordinates[0]);
                var x = Integer.parseInt(coordinates[1]);
                var position = new Position(x, y);
                var board = getBoard();
                if (board.isInBounds(position)) {
                    if (board.isEmptyCell(position)) {
                        board.setCell(position, getSymbol());
                        return;
                    } else {
                        System.out.println("This cell is occupied! Choose another one!");
                    }
                } else {
                    System.out.println("Coordinates should be from 1 to 3!");
                }
            } else {
                System.out.println("You should enter numbers!");
            }
        }
    }

    private boolean isNumber(final String str) {
        var matcher = NUMBER_PATTERN.matcher(str);
        return matcher.matches();
    }
}
