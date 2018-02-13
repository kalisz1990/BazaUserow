import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Users {

    public String name;
    public String pesel;
    public String address;
    public String email;

    public Users() {
        this.name = " ";
        this.address = " ";
        this.pesel = " ";
        this.email = " ";
    }

    public Users(String name, String pesel) {
        this();
        this.name = name;
        this.pesel = pesel;

        if (pesel.length() != 11)
            System.out.println("blad - " + name + ": podany zostal zly pesel");
    }

    public Users(String name, String pesel, String street, int buildingNumber, int apartmentNumber) {
        this(name, pesel);
        this.address = street + ", " + buildingNumber + "/" + apartmentNumber;
    }

    public Users(String name, String pesel, String street, int buildingNumber, int apartmentNumber, String email) {
        this(name, pesel, street, buildingNumber, apartmentNumber);
        this.email = email;
    }

    //getter
    public String getName() {
        return name;
    }

    public String getPesel() {
        return pesel;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    //setter
    public void setName() {
        Scanner inputName = new Scanner(System.in);
        System.out.print("name: ");
        this.name = inputName.nextLine();
    }

    public void setPesel() throws IOException {
        String line = null;
        String tPesel = "";

        Scanner inputPesel = new Scanner(System.in);
        BufferedReader reader = new BufferedReader(new FileReader("src" + File.separator + "usersDatabase.txt"));

        while (tPesel.length() != 11) {
            System.out.print("pesel: ");
            tPesel = inputPesel.nextLine();

            if (tPesel.length() > 11 || tPesel.length() < 11)
                System.out.println("wrong pesel !!, check and write again");
            else {
                while ((line = reader.readLine()) != null) {
                    if (line.contains(tPesel)) {
                        System.out.println("this pesel already exists");
                        tPesel = "";
                        break;
                    }
                }
            }
        }
        this.pesel = tPesel;

        reader.close();
    }

    public void  setAddress() {
        String street;
        String bNumber;
        String aNumber;

        Scanner inputAddress = new Scanner(System.in);

        System.out.println("addres details: ");
        System.out.print("    street: ");
        street = inputAddress.nextLine();
        System.out.print("    building number: ");
        bNumber = inputAddress.nextLine();
        System.out.print("    apartment number: ");
        aNumber = inputAddress.nextLine();

        this.address = street +" " + bNumber + "/" + aNumber;
    }

    public void setEmail() {
        Scanner inputEmail = new Scanner(System.in);
        System.out.print("e-mail: ");
        this.email = inputEmail.nextLine();
    }

    @Override
    public String toString() {
        return this.name + ", " + this.pesel + ", " + this.address + ", " + this.email;
    }

}

