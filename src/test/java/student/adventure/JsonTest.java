package student.adventure;

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
    public void compileTest() {
        assert false;
    }

}
