/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;
import Entities.Parent;
import Entities.Student;
/**
 *
 * @author Asus
 */
public class Parent {
    private int id;
    String name;
    String login;
    String password;

    public Parent() {
    }

    public Parent(String name, String login, String password) {
        this.name = name;
        this.login = login;
        this.password = password;
    }

    public Parent(String name, String login, String password,int id) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.id=id;
    }
     public Parent(int id,String name, String login, String password) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.id=id;
    }

    public Parent(int id) {
        this.id = id;
    }

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Parent{" + "id=" + id + ", name=" + name + ", login=" + login + ", password=" + password + '}';
    }
    
    
}
