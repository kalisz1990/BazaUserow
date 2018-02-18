import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class User {

    // @mk: dlaczego te właściwości są publiczne -> do manipulacji tymi danymi masz już settery i gettery, do nauki: enkapsulacja
    // @mk: do nauki wyłapywac Expection'y

    private String pesel = "";
    private String name = "";
    private String address = "";
    private String email = "";

   User() {
  }
    //getter
    String getPesel() {
        return pesel;
    }
    String getName() {
        return name;
    }
    String getAddress() {
        return address;
    }
    String getEmail() {
        return email;
    }

    //setter
    void setPesel(String pesel) throws IOException {
        String line = "";
        String tPesel = "";

        BufferedReader reader = new BufferedReader(new FileReader("db" + File.separator + "usersDatabase.txt"));

        if (pesel.length() != 11) {
            System.out.println("wrong pesel !!, check and write again\n");
            tPesel = "empty";
        }
        else {
            while ((line = reader.readLine()) != null) {
                if (line.contains(pesel)) {
                    System.out.println("this pesel already exists in database\n");
                    tPesel = "empty";
                    break;
                }
            }
        }
        if (!tPesel.equals("empty")) {
            this.pesel = pesel;
        }
   }
    void setName(String name) {
        this.name = name;
    }
    void setAddress(String address) {
        this.address = address;
    }
    void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return this.pesel + ", " + this.name + ", " + this.address + ", " + this.email;
    }

    // @mk: ta klasa "User" wygląda mi na tzw Encje (->google), zasadniczo nie powinineś się tu odwoływać do konsoli
    // @mk: settery z zasady działają trochę inaczej one przyjmują argument setName(String name) { this.name = name; }
    // @mk: natomiast całą komunikację lepiej byłoby mieć gdzieś w osobnej klasie
}

class UserManager extends User {

    UserManager(){
    }

    public void pesel() throws IOException {
        Scanner inputPesel = new Scanner(System.in);

        System.out.print("pesel: ");
        setPesel(inputPesel.next());
    }

    public void name() {
        Scanner inputName = new Scanner(System.in);

        System.out.print("name: ");
        setName(inputName.next());
    }

    public void address() {
        String street;
        String buildingNumber;
        String apartmentNumber;

        Scanner inputAddress = new Scanner(System.in);

        System.out.println("addres details: ");
        System.out.print("    street: ");
        street = inputAddress.next();
        System.out.print("    building number: ");
        buildingNumber = inputAddress.next();
        System.out.print("    apartment number: ");
        apartmentNumber = inputAddress.next();

        setAddress(street + " " + buildingNumber + "/" + apartmentNumber);
    }

    public void email() {
        Scanner inputEmail = new Scanner(System.in);

        System.out.print("e-mail: ");
        setEmail(inputEmail.next());
    }
}
