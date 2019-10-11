package src.java;

import java.io.*;

class User implements Serializable {

    private String pesel = "";
    private String name = "";
    private String street = "";
    private String bNumber = "";
    private String aNumber = "";
    private String email = "";
    private String address;


    String getPesel() {
        return pesel;
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

    void setPesel(String pesel) throws IOException {
//        this.pesel = pesel;

        String line;
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
        return this.pesel + ", " + this.name + ", " + this.address + ", " + this.email;
    }
}