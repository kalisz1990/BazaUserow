import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

interface manager {
    void createFile() throws IOException;

    void addUser() throws IOException;

    void findUser() throws IOException;

    void deleteUser() throws IOException;
}

// generics, squlite, konstrukcje API access point interface,
// API - REST z czym to się je
// SQL - MySQL  - relacyjne bazy danych , nierelacyjne bazy danych (bardziej zaawansowany temat - mapowanie obiektowo - relacyjne)
// unit testing

public class FileManager implements manager {

    private final String filePatch = "db/usersDatabase.txt";
    private BufferedReader reader = new BufferedReader(new FileReader(filePatch));
    private Scanner scanner = new Scanner(System.in);


    FileManager() throws FileNotFoundException {
        try {
            createFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    boolean isFileEmpty() {
        try {
            return reader.readLine() == null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void prepareFileIfEmpty() {
        if (isFileEmpty()) {
            try {
                if (reader.readLine() == null) {
                    BufferedWriter writer = new BufferedWriter(new FileWriter(filePatch));
                    writer.write("[]");
                    writer.close();
//                reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void createFile() throws IOException {
        File file = new File(filePatch);
        if (!file.exists()) {
            if (file.createNewFile()) {
                prepareFileIfEmpty();
            }
        }
    }

    public void addUser() throws IOException {
        String line;
        StringBuilder stringBuilder = new StringBuilder();
        User user = new User();

        prepareFileIfEmpty();

        System.out.print("pesel: ");
        user.setPesel(checkIfsPeselIsCorrect(scanner.nextLine(), user));
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

        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }
        addNewUserToFile(stringBuilder);
    }

    private void addNewUserToFile(StringBuilder tString) {
        String tJson;
        Gson json = new GsonBuilder().setPrettyPrinting().create();
        User user = new User();

        //deserializacja ze Stringa do LinkedList oraz dodanie nowego uzytkownika
        LinkedList<User> linkedList = json.fromJson(tString.toString(), new TypeToken<LinkedList<User>>() {
        }.getType());
        linkedList.addLast(user);

        //zapis do pliku z linkedList
        tJson = json.toJson(linkedList);
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(filePatch));
            writer.write(tJson);
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void findUser() throws IOException {
        String search;
        String line;
        StringBuilder tString = new StringBuilder();
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

//            BufferedReader reader = new BufferedReader(new FileReader("db" + File.separator + "usersDatabase.txt"));
            while ((line = reader.readLine()) != null) {
                tString.append(line);
            }
//            reader.close();
            // deserializacja ze Stringa, pliku do ArrayList
            ArrayList<User> arrayList = json.fromJson(tString.toString(), new TypeToken<ArrayList<User>>() {
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
        String userToDelete;
        String line;
        StringBuilder tString = new StringBuilder();
        String tJson;
        int count = 0;

        Scanner scanner = new Scanner(System.in);
        Gson json = new GsonBuilder().setPrettyPrinting().create();

        //sprawdzanie czy plik jest pusty
        isFileEmpty();

        if (!isFileEmpty()) {
            System.out.print("\npesel of user to delete: ");
            userToDelete = scanner.nextLine();

//            BufferedReader reader = new BufferedReader(new FileReader("db" + File.separator + "usersDatabase.txt"));
            while ((line = reader.readLine()) != null) {
                tString.append(line);
            }
//            reader.close();

            // deserializacja ze Stringa z pliku do LinkedList
            LinkedList<User> linkedList = json.fromJson(tString.toString(), new TypeToken<LinkedList<User>>() {
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
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePatch));
            writer.write(tJson);
            writer.newLine();
            writer.close();
        }
    }

    private String checkIfsPeselIsCorrect(String pesel, User user) throws IOException {
        String line;
        boolean flag = true;

        while (flag) {
            if (pesel.length() != 11) {
                System.out.println("wrong pesel !!, check and write again\npesel: ");
                pesel = scanner.next();
                flag = true;
            } else {
                while ((line = reader.readLine()) != null) {
                    if (line.contains(pesel)) {
                        System.out.println("this pesel already exists in database\npesel: ");
                        pesel = scanner.next();
                        flag = true;
                    }
                }
            }
            flag = false;
        }
        return pesel;
    }
}
