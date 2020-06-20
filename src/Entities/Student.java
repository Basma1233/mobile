/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import Entities.User;

/**
 *
 * @author Asus
 */
public class Student extends User {

    private String dateOfBirth;
    private String Class;
    private String Adress;
    private String mail;
    private String sex;
    private String name;
    private String login;
    private String password;
    private int id ;
 
    public Student() {
    }
     public Student(int id) {
         
         this.id= id;
    }

    public Student(String Class, String Adress, String mail, String sex, String name, String login, String password) {
        this.Class = Class;
        this.Adress = Adress;
        this.mail = mail;
        this.sex = sex;
        this.name = name;
        this.login = login;
        this.password = password;
    }
    

    public Student(int id, String dateOfBirth, String Class, String Adress, String mail, String sex, String studentname, String login, String password) {
        super();
        this.id= id;
        this.dateOfBirth = dateOfBirth;
        this.Class = Class;
        this.Adress = Adress;
        this.mail = mail;
        this.sex = sex;
        this.name = studentname;
        this.login = login;
        this.password = password;
    }

    public Student(int parseInt, String text) {
    }


    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getClasse() {
        return this.Class;
    }

    public String getAdress() {
        return Adress;
    }

    public String getMail() {
        return mail;
    }

    public String getSex() {
        return sex;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setClass(String Class) {
        this.Class = Class;
    }

    public void setAdress(String Adress) {
        this.Adress = Adress;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getLogin() {
        return login;
    }

    /**
     *
     * @param login
     */
    @Override
    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Student{" + "dateOfBirth=" + dateOfBirth + ", Class=" + Class + ", Adress=" + Adress + ", mail=" + mail + ", sex=" + sex + ", name=" + name + ", login=" + login + ", password=" + password + '}';
    }

}
