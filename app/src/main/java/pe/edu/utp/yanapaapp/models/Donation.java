package pe.edu.utp.yanapaapp.models;

public class Donation {
    private Integer code;
    private String name;
    private String address;
    private String color;
    private String status;

    public Donation(Integer code, String name, String address, String color, String status) {
        this.code = code;
        this.name = name;
        this.address = address;
        this.color = color;
        this.status = status;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
