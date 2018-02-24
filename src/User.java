package src;

import java.io.*;

public class User implements Serializable {

    // @mk: do nauki wyłapywac Expection'y
    // @mk: ta klasa "User" wygląda mi na tzw Encje (->google), zasadniczo nie powinineś się tu odwoływać do konsoli

    private String pesel = "";
    private String name = "";
    private String address = "";
    private transient String street = "";
    private transient String bNumber = "";
    private transient String aNumber = "";
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
    String getStreet() {
        return street;
    }
    String getaNumber() {
        return aNumber;
    }
    String getbNumber() {
        return bNumber;
    }
    String getEmail() {
        return email;
    }
    String getAddress() {
        return street + " " + bNumber + "/" + aNumber;
    }

    //setter
    String setPesel(String pesel) throws IOException {
        String line = "";
        String tPesel = "";

        BufferedReader reader = new BufferedReader(new FileReader("db" + File.separator + "usersDatabase.txt"));

        if (pesel.length() != 11) {
            System.out.println("wrong pesel !!, check and write again\n");
            tPesel = "empty";
        } else {
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
        return pesel;
    }
    void setName(String name) {
        this.name = name;
    }
    void setStreet(String street) {
        this.street = street;
    }
    void setaNumber(String aNumber) {
        this.aNumber = aNumber;
    }
    void setbNumber(String bNumber) {
        this.bNumber = bNumber;
    }
    void setAddress(String street, String aNumber, String bNumber) {
        this.address = street + " " + bNumber + "/" + aNumber;
    }
    void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return this.pesel + ", " + this.name + ", " + this.street + " " + this.bNumber + "/" + this.aNumber + ", " + this.email;
    }
}
