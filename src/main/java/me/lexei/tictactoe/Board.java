package me.lexei.tictactoe;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Board {

    public static final int SIZE = 3;
    private static final int LINE_WIDTH = 9;

    public static final char EMPTY_CELL = ' ';

    private final char[][] board = new char[SIZE + 1][SIZE + 1];

    private Set<Position> emptyCells = new HashSet<>();

    public Board() {
        for (var y = 1; y <= SIZE; y++) {
            for (var x = 1; x <= SIZE; x++) {
                board[y][x] = EMPTY_CELL;
                emptyCells.add(new Position(x, y));
            }
        }
    }

    public Board(final Board board) {
        for (var y = 1; y <= SIZE; y++) {
            this.board[y] = Arrays.copyOf(board.board[y], board.board[y].length);
        }
        this.emptyCells = new HashSet<>(board.emptyCells);
    }

    public void setCell(final Position position, final char symbol) {
        board[position.getY()][position.getX()] = symbol;
        emptyCells.remove(position);
    }

    private boolean isInBounds(final int index) {
        return index >= 1 && index <= SIZE;
    }

    public boolean isInBounds(final Position position) {
        return isInBounds(position.getX()) && isInBounds(position.getY());
    }

    public boolean isEmptyCell(final Position position) {
        return board[position.getY()][position.getX()] == EMPTY_CELL;
    }

    public void show() {
        printLine();
        for (var y = 1; y <= SIZE; y++) {
            System.out.print("| ");
            for (var x = 1; x <= SIZE; x++) {
                System.out.print(board[y][x] + " ");
            }
            System.out.println("|");
        }
        printLine();
    }

    private void printLine() {
        for (var i = 0; i < LINE_WIDTH; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    private boolean isFullRow(final int row, final char symbol) {
        for (var x = 1; x <= SIZE; x++) {
            if (board[row][x] != symbol) {
                return false;
            }
        }
        return true;
    }

    private boolean isFullColumn(final int column, final char symbol) {
        for (var y = 1; y <= SIZE; y++) {
            if (board[y][column] != symbol) {
                return false;
            }
        }
        return true;
    }

    private boolean isFullDiagonal(final char symbol) {
        var toRightCount = 0;
        var toLeftCount = 0;
        var x = 1;
        for (var y = 1; y <= SIZE; y++) {
            if (board[y][x] == symbol) {
                toRightCount++;
            }
            if (board[y][SIZE - x + 1] == symbol) {
                toLeftCount++;
            }
            x++;
        }
        return toRightCount == SIZE || toLeftCount == SIZE;
    }

    public boolean hasThreeInRow(final char symbol) {
        for (var i = 1; i <= SIZE; i++) {
            if (isFullRow(i, symbol) || isFullColumn(i, symbol)) {
                return true;
            }
        }
        return isFullDiagonal(symbol);
    }

    public boolean hasEmptyCells() {
        return !emptyCells.isEmpty();
    }

    public Set<Position> getEmptyCells() {
        return emptyCells;
    }
}
