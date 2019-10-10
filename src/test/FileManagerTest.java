package src.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//import static org.assertj.core.api.Assertions.*;
import src.java.FileManager;

public class FileManagerTest {

    FileManager fileManager;

    @BeforeEach
    public void setUp(){
        fileManager = new FileManager();
    }

    @Test
    public void testIsFileEmpty(){
        fileManager.isFileEmpty();
    }


}
