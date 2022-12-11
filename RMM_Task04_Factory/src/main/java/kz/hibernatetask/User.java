package kz.hibernatetask;

public class User {

    private Integer id;
    private String name;
    private String position;
    private String birthday;

    public User() {
    }

    public User(Integer id) {
        this.id = id;
    }

    public User(String name, String birthday, String position) {
        this.name = name;
        this.birthday = birthday;
        this.position = position;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
    
    

    @Override
    public String toString() {
        return String.format("Name-%s, Birthday-%s, Position-%s", name, birthday, position);
    }

}
