package src;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        menuSearch menu = new Menu();
        manager fileManager = new FileManager();


        try {
           // fileManager.createFile();
           // menu.searchMenu();
            fileManager.findUser();
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

