import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Game {
    List<Player> playersList = new ArrayList<>();

    public void register(Player playerToRegister) {
        playersList.add(playerToRegister);
    }

    public int round(String playerName1, String playerName2) {

        Player player1 = null;
        Player player2 = null;
        for (Player player : playersList) {
            if (player.getName().equals(playerName1)) {
                player1 = player;
            }
            if (player.getName().equals(playerName2)) {
                player2 = player;
            }
        }
        if (player1 == null) {
            throw new NotRegisteredException(playerName1);
        }
        if (player2 == null) {
            throw new NotRegisteredException(playerName2);
        }
        int strength1 = player1.getStrength();
        int strength2 = player2.getStrength();
        if (strength1 < strength2) {
            return 2;
        }
        if (strength1 > strength2) {
            return 1;
        }
        return 0;
    }

}
