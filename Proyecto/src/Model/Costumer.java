package Model;

public class Costumer {

    private String name;
    private String phone;
    private String email;
    private int zip_code;

    public Costumer(String name, String phone, String email, int zip_code) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.zip_code = zip_code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getZip_code() {
        return zip_code;
    }

    public void setZip_code(int zip_code) {
        this.zip_code = zip_code;
    }
}
