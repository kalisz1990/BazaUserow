import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.assertj.core.api.Assertions.*;

public class FileManagerTest {

    private FileManager fileManager;

    @BeforeEach
    public void setUp(){
        try {
            fileManager = new FileManager();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testIsFileEmpty(){
        assertThat(fileManager.isFileEmpty())
                .isTrue();
    }


}
