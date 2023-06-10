import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class GameTest {

    @Test
    public void FindByNameTest() {
        Player player1 = new Player(1001, "kolobok", 12);
        Player player2 = new Player(1013, "belochka", 7);
        Player player3 = new Player(1006, "bulochka", 10);
        Player player4 = new Player(1004, "Dart Vader", 1);
        Player player5 = new Player(1002, "peanut666", 2);
        Player player6 = new Player(1025, "nhegm", 5);

        Game game = new Game();
        game.add(player1);
        game.add(player2);
        game.add(player3);
        game.add(player4);
        game.add(player5);
        game.add(player6);

        Player expected = player3;
        Assertions.assertEquals(expected, game.findByName("bulochka"));
    }

    @Test
    public void GameTests() {
        Player player1 = new Player(1001, "kolobok", 12);
        Player player2 = new Player(1013, "belochka", 7);
        Player player3 = new Player(1006, "bulochka", 10);
        Player player4 = new Player(1004, "Dart Vader", 1);
        Player player5 = new Player(1002, "peanut666", 2);
        Player player6 = new Player(1025, "nhegm", 5);
        Player player7 = new Player(1099, "player666", 7);
        Player player54 = new Player(332, "player54", 2);

        Game game = new Game();
        game.add(player1);
        game.add(player2);
        game.add(player3);
        game.add(player4);
        game.add(player5);
        game.add(player6);
        game.add(player7);

        game.register(player7);
        game.register(player2);
        game.register(player6);
        game.register(player1);

        int actualDraw = game.round("player666", "belochka");
        int expectedDraw = 0;
        int actualWin1 = game.round("kolobok", "player666");
        int expectedWin1 = 1;
        int actualWin2 = game.round("nhegm", "belochka");
        int expectedWin2 = 2;

        Assertions.assertThrows(AlreadyExistsException.class, () -> {       //trying to add player that already exists
            game.add(player1);
        });
        Assertions.assertThrows(PlayerDoesNotExistException.class, () -> {  //trying to register player that doesn't exist
            game.register(player54);
        });
        Assertions.assertEquals(actualDraw, expectedDraw);
        Assertions.assertEquals(actualWin1, expectedWin1);
        Assertions.assertEquals(actualWin2, expectedWin2);
        Assertions.assertThrows(NotRegisteredException.class, () -> {       // 1-st unregistered
            game.round("kolobok", "peanut666");
        });
        Assertions.assertThrows(NotRegisteredException.class, () -> {       // 2-nd unregistered
            game.round("bulochka", "player666");
        });
        Assertions.assertThrows(NotRegisteredException.class, () -> {       // both unregistered
            game.round("Dart Vader", "player54");
        });
    }


}
