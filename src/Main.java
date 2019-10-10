package src;

import java.io.IOException;

class Main {

    public static void main(String[] args) {

        menuSearch menu = new Menu();
        manager fileManager = new FileManager();

        try {
            fileManager.createFile();
            menu.searchMenu();
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
