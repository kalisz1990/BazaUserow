package src;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

interface manager {
    void createFile() throws IOException;
    void addUser()    throws IOException;
    void findUser()   throws IOException;
    void deleteUser() throws IOException;
}
public class FileManager implements manager {

    private boolean isFileEmpty () throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("db" + File.separator + "usersDatabase.txt"));
        if (reader.readLine() == null) {
            BufferedWriter writer = new BufferedWriter(new FileWriter("db" + File.separator + "usersDatabase.txt"));
            writer.write("[]");
            writer.close();
            reader.close();
            return true;
        }
        else {
            return false;
        }
    }
    public void createFile() throws IOException {

        File plik = new File("db" + File.separator + "usersDatabase.txt");

        if (!plik.exists()) {
            plik.createNewFile();
            isFileEmpty();
        }
    }

    public void addUser() throws IOException {
        String line = "";
        String tString = "";
        String tJson = "";

        User user = new User();
        Gson json = new GsonBuilder().setPrettyPrinting().create();
        Scanner scanner = new Scanner(System.in);

        //sprawdzanie czy plik jest pusty i dodanie znaku listy
        isFileEmpty();

        // kontakt z uzytkownikiem konsoli i zapisanie danych setterem
        System.out.print("pesel: ");
        user.setPesel(scanner.nextLine());
        if (!user.getPesel().equals("")) {
            System.out.print("name: ");
            user.setName(scanner.nextLine());
            System.out.println("addres details: ");
            System.out.print("    street: ");
            user.setStreet(scanner.nextLine());
            System.out.print("    building number: ");
            user.setbNumber(scanner.nextLine());
            System.out.print("    apartment number: ");
            user.setaNumber(scanner.nextLine());
            System.out.print("email: ");
            user.setEmail(scanner.nextLine());
            user.setAddress(user.getStreet(), user.getbNumber(), user.getaNumber());
        }
        // zapis z pliku do Stringa
        BufferedReader reader = new BufferedReader(new FileReader("db" + File.separator + "usersDatabase.txt"));

        if (!isFileEmpty()){
            while ((line = reader.readLine()) != null) {
                tString += line;
            }
            reader.close();

            //deserializacja ze Stringa do LinkedList oraz dodanie nowego uzytkownika
            LinkedList<User> linkedList = json.fromJson(tString, new TypeToken<LinkedList<User>>() {
            }.getType());
            linkedList.addLast(user);

            //zapis do pliku z linkedList
            tJson = json.toJson(linkedList);
            BufferedWriter writer = new BufferedWriter(new FileWriter("db" + File.separator + "usersDatabase.txt"));
            writer.write(tJson);
            writer.newLine();
            writer.close();
        }
    }

    public void findUser() throws IOException {
        String search = "";
        String line = "";
        String tString = "";
        int count = 0;

        Gson json = new GsonBuilder().setPrettyPrinting().create();
        Scanner searchUser = new Scanner(System.in);

        //sprawdzanie czy plik jest pusty i dodanie znalu listy []
         isFileEmpty();

        // zapis z pliku do Stringa
        if (!isFileEmpty()) {
            System.out.print("\npesel of user you want to find: ");
            search = searchUser.nextLine();
            System.out.println();

            BufferedReader reader = new BufferedReader(new FileReader("db" + File.separator + "usersDatabase.txt"));
            while ((line = reader.readLine()) != null) {
                tString += line;
            }
            reader.close();
            // deserializacja ze Stringa, pliku do ArrayList
            ArrayList<User> arrayList = json.fromJson(tString, new TypeToken<ArrayList<User>>() {
            }.getType());

            for (User user : arrayList) {
                if (user.getPesel() != null && user.getPesel().contains(search)) {
                    String find = json.toJson(user);
                    System.out.println(find);
                    break;
                }
                count++;
            }
            if (count == arrayList.size()) {
                System.out.println("no user in database");
            }
        }
    }

    public void deleteUser() throws IOException {
        String userToDelete = "";
        String line = "";
        String tString = "";
        String tJson = "";
        int count = 0;

        Scanner scanner = new Scanner(System.in);
        Gson json = new GsonBuilder().setPrettyPrinting().create();

        //sprawdzanie czy plik jest pusty
        isFileEmpty();

        if (!isFileEmpty()) {
            System.out.print("\npesel of user to delete: ");
            userToDelete = scanner.nextLine();

            BufferedReader reader = new BufferedReader(new FileReader("db" + File.separator + "usersDatabase.txt"));
            while ((line = reader.readLine()) != null) {
                tString += line;
            }
            reader.close();

            // deserializacja ze Stringa z pliku do LinkedList
            LinkedList<User> linkedList = json.fromJson(tString, new TypeToken<LinkedList<User>>() {
            }.getType());

            for (User user : linkedList) {
                if (user.getPesel() != null && user.getPesel().contains(userToDelete)) {
                    linkedList.remove(user);
                    System.out.println("user remoeved");
                    break;
                }
                count++;
            }
            if (count == linkedList.size()) {
                System.out.println("no user in database");
            }

            //zapis do pliku
            tJson = json.toJson(linkedList);
            BufferedWriter writer = new BufferedWriter(new FileWriter("db" + File.separator + "usersDatabase.txt"));
            writer.write(tJson);
            writer.newLine();
            writer.close();
        }
    }
}
