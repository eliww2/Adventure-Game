package student.adventure;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import com.google.gson.Gson;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonTest {
    private static Gson gson;
    private static Reader jsonReader;

    @Before
    public void setUp() {
        try {
            gson = new Gson();
            jsonReader = Files.newBufferedReader(Paths.get("src/main/resources/illinois.json"));
        } catch (Exception e) {
            assert false;
        }
    }

    @Test
    public void RoomDeserializeTest() {
        Game illinoisAdventure = gson.fromJson(jsonReader, Game.class);
        assertEquals("Home", illinoisAdventure.getRooms()[0].getName());
    }

    @Test
    public void DirectionDeserializeTest() {
        Game illinoisAdventure = gson.fromJson(jsonReader, Game.class);
        assertEquals("Eat", illinoisAdventure.getRooms()[1].getDirections()[0].getDirectionName());
    }

    @Test
    public void ItemDeserializeTest() {
        Game illinoisAdventure = gson.fromJson(jsonReader, Game.class);
        assertEquals("FatIllini", illinoisAdventure.getRooms()[4].getItems().get(0).getItemName());
    }
}

