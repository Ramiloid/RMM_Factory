package kz.hibernatetask;

public class Phone {

    private Integer id;
    private String phone;
    private String type;
    private User user;

    public Phone() {
    }
    
    public Phone(User user, String name, String type) {
        this.user = user;
        this.phone = name;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    

    @Override
    public String toString() {
        return String.format("Phone [id=%s, user=%s, phone=%s, type=%s]", id, user.getName(), phone, type);
    }
}
