import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Game {
    ArrayList<String> PlayersList = new ArrayList<>();
    private Player[] players = new Player[0];

    public Player findByName(String name) {
        for (Player player : players) {
            if (player.getName() == name) {
                return player;
            }
        }
        return null;
    }

    private Player[] addToArray(Player[] current, Player player) {
        Player[] tmp = new Player[current.length + 1];
        for (int i = 0; i < current.length; i++) {
            tmp[i] = current[i];
        }
        tmp[tmp.length - 1] = player;
        return tmp;
    }

    public void add(Player playerToAdd) {
        boolean playerExists = false;
        for (Player player : players) {
            if (playerToAdd.getName() == player.getName()) {
                playerExists = true;
            }
        }
        if (playerExists) {
            throw new AlreadyExistsException(
                    "Игрок c ником " + playerToAdd.getName() + " уже существует"
            );
        } else {
            players = addToArray(players, playerToAdd);
        }
    }

    public void register(Player playerToRegister) {

        if (findByName(playerToRegister.getName()) != null) {
            PlayersList.add(playerToRegister.getName());
        } else {
            throw new PlayerDoesNotExistException(
                    "Игрока с ником " + playerToRegister.getName() + " не существует"
            );
        }

    }

    public int round(String playerName1, String playerName2) {

        if (PlayersList.contains(playerName1) && PlayersList.contains(playerName2)) {
            int strength1 = findByName(playerName1).getStrength();
            int strength2 = findByName(playerName2).getStrength();
            if (strength1 < strength2) {
                return 2;
            } else if (strength1 > strength2) {
                return 1;
            } else {
                return 0;
            }
        } else if (!PlayersList.contains(playerName1) && PlayersList.contains(playerName2)) {
            throw new NotRegisteredException(
                    "Игрок c ником " + playerName1 + " не зарегистрирован"
            );
        } else if (PlayersList.contains(playerName1) && !PlayersList.contains(playerName2)) {
            throw new NotRegisteredException(
                    "Игрок c ником " + playerName2 + " не зарегистрирован"
            );
        } else if (!PlayersList.contains(playerName1) && !PlayersList.contains(playerName2)) {
            throw new NotRegisteredException(
                    "Игроки c никами " + playerName1 + " и " + playerName2 + " не зарегистрированы"
            );
        }
        return -1;

    }

}
