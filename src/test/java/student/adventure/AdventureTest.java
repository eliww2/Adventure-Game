package student.adventure;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import com.google.gson.Gson;
import javafx.scene.control.TextFormatter;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class AdventureTest {
    private static Gson gson;
    private static Reader jsonReader;

    @Before
    public void setUp() {
        try {
            gson = new Gson();
            jsonReader = Files.newBufferedReader(Paths.get("src/main/resources/illinois.json"));
        } catch (Exception e) {
            assert false;
        }    }

    @Test
    public void updateRoomTest () {
        Game illinoisAdventure = gson.fromJson(jsonReader, Game.class);
        assertEquals("Kams",
           ChangeState.updateRoom("party", illinoisAdventure.getRooms()[3], illinoisAdventure).getName());
    }

    @Test
    public void addToInventoryTest () {
        Game illinoisAdventure = gson.fromJson(jsonReader, Game.class);
        assertEquals("Mask", ChangeState.addToInventory("Mask", illinoisAdventure.getRooms()[0]).getItemName());
    }

    @Test
    public void checkForEndTest () {
        Game illinoisAdventure = gson.fromJson(jsonReader, Game.class);
        Room winningRoom = illinoisAdventure.getRooms()[illinoisAdventure.getRooms().length - 1];
        Room losingRoom = illinoisAdventure.getRooms()[7];
        assertEquals(false, ChangeState.checkForEnd(illinoisAdventure, winningRoom));
        assertEquals(false, ChangeState.checkForEnd(illinoisAdventure, losingRoom));
    }
}