import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
class DjTest {
    @TempDir
    Path tempDir;

    private Dj test() throws Exception{
        Path file = tempDir.resolve("graph.txt");
        Files.writeString(file, "A B 2\nB C 3\n A C 10\n");
        return new Dj(file.toString());
    }

    @Test
    void nodeValidationTest() throws Exception{
        Dj dj = test();
        assertTrue(dj.nodes().contains("A"));
        assertFalse(dj.nodes().contains("Z"));
    }




}