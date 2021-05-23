package me.lexei.tictactoe;

import java.util.Scanner;

public class Game {

    private static final String START_CMD = "start";
    private static final String EXIT_CMD = "exit";

    private static final String SYMBOLS = String.format("%c%c", Player.CROSS, Player.ZERO);

    private static final Scanner scanner = new Scanner(System.in);

    public void play() {
        while (true) {
            System.out.print("Input command: ");
            var cmd = scanner.next();
            if (START_CMD.equals(cmd)) {
                var parameters = scanner.nextLine().trim().split(" ");
                if (checkParameters(parameters)) {
                    var board = new Board();
                    playRound(getPlayers(parameters, board), board);
                } else {
                    System.out.println("Bad parameters!");
                }
            } else if (EXIT_CMD.equals(cmd)) {
                break;
            }
        }
    }

    private boolean checkParameters(final String[] parameters) {
        return parameters.length == 2
                && Player.iaValidLabel(parameters[0])
                && Player.iaValidLabel(parameters[1]);
    }

    private Player[] getPlayers(final String[] parameters, final Board board) {
        var players = new Player[parameters.length];
        for (var i = 0; i < players.length; i++) {
            players[i] = Player.fromLabel(parameters[i], board, SYMBOLS.charAt(i));
        }
        return players;
    }

    private void playRound(final Player[] players, final Board board) {
        board.show();
        while (true) {
            for (var player : players) {
                player.move();
                board.show();
                if (player.isWinner()) {
                    System.out.println(player.getSymbol() + " wins");
                    return;
                }
                if (!board.hasEmptyCells()) {
                    System.out.println("Draw");
                    return;
                }
            }
        }
    }
}
