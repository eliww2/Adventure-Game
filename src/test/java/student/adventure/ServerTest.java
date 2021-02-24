package student.adventure;

import com.google.gson.Gson;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import student.adventure.ChangeState;
import student.adventure.Game;
import student.server.AdventureResource;
import student.server.Command;
import student.server.IllinoisAdventureService;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import static org.junit.Assert.*;

@RunWith(Enclosed.class)
public final class ServerTest {
    private static Gson gson;
    private static Reader jsonReader;
    private static IllinoisAdventureService service;

    @Before
    public static void setUp() {
        try {
            gson = new Gson();
            jsonReader = Files.newBufferedReader(Paths.get("src/main/resources/illinois.json"));
            service = new IllinoisAdventureService();
            service.reset();
        } catch (Exception e) {
            assert false;
        }
    }

    /*
    Tests new server methods successfully change the games on server and can hold multiple games.
    Also checks if deserialized correctly.
     */
    public static class ServerMethodsTests {
        @Before public void setUp() { ServerTest.setUp(); }

        @Test
        public void MultipleGameTest() {
            try {
                assertEquals(0, service.newGame());
                assertEquals(1, service.newGame());
                assertEquals(2, service.newGame());
            } catch (Exception e) {
                assert false;
            }
        }

        @Test
        public void TwoGamesPlayedTest() {
            try {
                service.newGame();
                Game gameOne = service.getGameInstance().get(0);
                service.newGame();
                Game gameTwo = service.getGameInstance().get(1);
                ChangeState.updateRoom("Outside", gameOne);
                assertEquals("BlockOne", gameOne.getCurrentRoom().getName());
                assertEquals("Home", gameTwo.getCurrentRoom().getName());
            } catch (Exception e) {
                assert false;
            }
        }

        @Test
        public void ClearGamesTest() {
            try {
                assertEquals(0,service.getGameInstance().size());
                service.newGame();
                service.newGame();
                assertEquals(2,service.getGameInstance().size());
                service.reset();
                assertEquals(0,service.getGameInstance().size());
            } catch (Exception e) {
                assert false;
            }
        }

        @Test
        public void DestroyGameTest() {
            try {
                service.newGame();
                service.newGame();
                assertNotNull(service.getGameInstance().get(1));
                service.destroyGame(1);
                assertNull(service.getGameInstance().get(1));
            } catch (Exception e) {
                assert false;
            }
        }

        @Test
        public void DeserializeTest() {
            try {
                service.newGame();
                Game currentGame = service.getGameInstance().get(0);
                assertEquals("Home", currentGame.getCurrentRoom().getName());
                assertEquals(0, currentGame.getInventory().size());
            } catch (Exception e) {
                assert false;
            }

        }
    }

    /*
    Test change of rooms works and there states are correct when changed
     */
    public static class RoomTests {
        @Before public void setUp() { ServerTest.setUp(); }

        @Test
        public void EnterRegularRoomsTest() {
            try {
                service.newGame();
                Game currentGame = service.getGameInstance().get(0);
                ChangeState.updateRoom("outside", currentGame);
                assertEquals("BlockOne", currentGame.getCurrentRoom().getName());
                ChangeState.updateRoom("north", currentGame);
                assertEquals("BlockTwo", currentGame.getCurrentRoom().getName());
            } catch (Exception e) {
                assert false;
            }
        }

        @Test
        public void InvalidDirection() {
            try {
                service.newGame();
                Game currentGame = service.getGameInstance().get(0);
                ChangeState.updateRoom("bleh", currentGame);
                assertEquals("Home", currentGame.getCurrentRoom().getName());
            } catch (Exception e) {
                assert false;
            }
        }

        @Test
        public void EnterLockedRoom() {
            try {
                service.newGame();
                Game currentGame = service.getGameInstance().get(0);
                ChangeState.updateRoom("outside", currentGame);
                ChangeState.updateRoom("eat", currentGame);
                assertEquals("BlockOne", currentGame.getCurrentRoom().getName());
            } catch (Exception e) {
                assert false;
            }
        }

    }

    /*
    Tests that Items are in the right place when taken/ dropped
     */
    public static class ItemTests {
        @Before public void setUp() { ServerTest.setUp(); }

        @Test
        public void ItemPickupTest() {
            try {
                service.newGame();
                Game currentGame = service.getGameInstance().get(0);
                assertEquals(1, currentGame.getCurrentRoom().getItems().size());
                ChangeState.addToInventory("Mask", currentGame);
                assertEquals("Mask", currentGame.getInventory().get(0).getItemName());
                assertEquals(0, currentGame.getCurrentRoom().getItems().size());
            } catch (Exception e) {
                assert false;
            }
        }

        @Test
        public void ItemDropTest() {
            try {
                service.newGame();
                Game currentGame = service.getGameInstance().get(0);
                ChangeState.addToInventory("Mask", currentGame);
                assertEquals("Mask", currentGame.getInventory().get(0).getItemName());
                ChangeState.removeFromInventory("Mask", currentGame);
                assertEquals(0, currentGame.getInventory().size());
            } catch (Exception e) {
                assert false;
            }
        }

        @Test
        public void ItemNotInRoomTest() {
            try {
                service.newGame();
                Game currentGame = service.getGameInstance().get(0);
                ChangeState.updateRoom("outside", currentGame);
                ChangeState.addToInventory("Mask", currentGame);
                assertEquals(0, currentGame.getInventory().size());
            } catch (Exception e) {
                assert false;
            }
        }

        @Test
        public void InvalidItemTest() {
            try {
                service.newGame();
                Game currentGame = service.getGameInstance().get(0);
                ChangeState.addToInventory("sk", currentGame);
                assertEquals(0, currentGame.getInventory().size());
            } catch (Exception e) {
                assert false;
            }
        }
    }

    /*
    Tests server commands change game correct way
     */
    public static class CommandTests {
        @Before public void setUp() { ServerTest.setUp();}

        @Test
        public void CommandNotGoodTest() {
            try {
                service.newGame();
                Game currentGame = service.getGameInstance().get(0);
                Command myCommand = new Command("Go", "There");
                service.executeCommand(0, myCommand);
                assertEquals("Home", currentGame.getCurrentRoom().getName());
            } catch (Exception e) {
                assert false;
            }
        }

        @Test
        public void GoTest() {
            try {
                service.newGame();
                Game currentGame = service.getGameInstance().get(0);
                Command goCommand = new Command("Go", "Outside");
                service.executeCommand(0, goCommand);
                assertEquals("BlockOne", currentGame.getCurrentRoom().getName());
            } catch (Exception e) {
                assert false;
            }
        }

        @Test
        public void TakeTest() {
            try {
                service.newGame();
                Game currentGame = service.getGameInstance().get(0);
                Command takeCommand = new Command("Take", "Mask");
                service.executeCommand(0, takeCommand);
                assertEquals("Mask", currentGame.getInventory().get(0).getItemName());
                assertEquals(0, currentGame.getCurrentRoom().getItems().size());
            } catch (Exception e) {
                assert false;
            }
        }

        @Test
        public void DropTest() {
            try {
                service.newGame();
                Game currentGame = service.getGameInstance().get(0);
                Command takeCommand = new Command("Take", "Mask");
                service.executeCommand(0, takeCommand);
                assertEquals("Mask", currentGame.getInventory().get(0).getItemName());
                Command dropCommand = new Command("Drop", "Mask");
                service.executeCommand(0, dropCommand);
                assertEquals("Mask", currentGame.getCurrentRoom().getItems().get(0).getItemName());
                assertEquals(0, currentGame.getInventory().size());
            } catch (Exception e) {
                assert false;
            }
        }

    }

    /*
    Tests new feature of use and locked doors.
    Tests that use works and that it unlocks doors when needed.
     */
    public static class UseTests {
        @Before
        public void setUp() { ServerTest.setUp(); }

        @Test
        public void RemovesFromInventoryTest() {
            try {
                service.newGame();
                Game currentGame = service.getGameInstance().get(0);
                Command takeCommand = new Command("Take", "Mask");
                Command useCommand = new Command("Use", "Mask");
                service.executeCommand(0, takeCommand);
                service.executeCommand(0, useCommand);
                assertEquals(0, currentGame.getInventory().size());
                assertEquals(0, currentGame.getInventory().size());
            } catch (Exception e) {
                assert false;
            }
        }

        @Test
        public void CantEnterYetTest() {
            try {
                service.newGame();
                Game currentGame = service.getGameInstance().get(0);
                Command outsideCommand = new Command("Go", "Outside");
                service.executeCommand(0, outsideCommand);
                Command eatCommand = new Command("Go", "Eat");
                service.executeCommand(0, eatCommand);
                assertEquals("BlockOne", currentGame.getCurrentRoom().getName());
            } catch (Exception e) {
                assert false;
            }
        }

        @Test
        public void CanEnterTest() {
            try {
                service.newGame();
                Game currentGame = service.getGameInstance().get(0);

                Command takeCommand = new Command("Take", "Mask");
                service.executeCommand(0, takeCommand);

                Command useCommand = new Command("Use", "Mask");
                service.executeCommand(0, useCommand);

                Command outsideCommand = new Command("Go", "Outside");
                service.executeCommand(0, outsideCommand);

                Command eatCommand = new Command("Go", "Eat");
                service.executeCommand(0, eatCommand);

                assertEquals("FatSandwich", currentGame.getCurrentRoom().getName());
            } catch (Exception e) {
                assert false;
            }
        }


    }

}
