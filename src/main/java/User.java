import java.io.*;

class User implements Serializable {

    private String pesel;
    private String name;
    private String street;
    private String bNumber;
    private String aNumber;
    private String email;
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

    void setPesel(String pesel) {
        this.pesel = pesel;
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
