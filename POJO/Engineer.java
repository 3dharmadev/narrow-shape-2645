package narrow_shape_2645.POJO;

public class Engineer {

    private String username;
    private int password;

    private  String Type;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    @Override
    public String toString() {
        return "Engineer{" +
                "username='" + username + '\'' +
                ", password=" + password +
                '}';
    }
}
