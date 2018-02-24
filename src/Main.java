package src;

import java.awt.*;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        menuSearch menu = new Menu();
        manager fileManager = new FileManager();


        try {
            menu.searchMenu();
            fileManager.createFile();
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

