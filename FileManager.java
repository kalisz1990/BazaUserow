import java.io.File;
import java.io.IOException;

public class FileManager {


public boolean createFile() throws IOException
{
    File plik = new File("usersDatabase.txt");
    if (!plik.exists())
        plik.createNewFile();
return true;
}

}
