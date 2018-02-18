import java.io.File;
import java.io.IOException;

public class Main {
    
    public static void main(String[] args) {

        try {
            FileManager.createFile();
            Menu.searchMenu();
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

