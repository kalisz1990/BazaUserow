import java.io.IOException;

public class Main {
    
        public static void main(String[] args) {

            try
            {
                FileManager.createFile();
                FileManager.searchMenu();
            }
            catch (IOException e)
            {
                System.out.println(e.getMessage());
            }

    }

}

