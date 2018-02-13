import java.io.File;
import java.io.IOException;

public class FileManager {


public void createFile() throws IOException
    {
        File plik = new File("usersDatabase.txt");
        if (!plik.exists())
            plik.createNewFile();
    }

}
