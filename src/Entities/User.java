package Entities;
import Entities.User;

/**
 *
 * @author Asus
 */
public class User extends Parent{
    private Student student;
    private String studentname;

    public User(int parseInt, String text) {
    }
       public User( String name, String login, String password) {
       
        this.name = name;
        this.login = login;
        this.password = password;
    }

    public User(int id, String name, String login, String password) {
        this.getId();
        this.name = name;
        this.login = login;
        this.password = password;
    }


    public String getStudentname() {
        return studentname;
    }

    public void setStudentname(String studentname) {
        this.studentname = studentname;
    }
    
    public User() {
    }

    public User(String name, String login, String password,int id) {
        super(name, login, password,id);
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
    
    
    
}
