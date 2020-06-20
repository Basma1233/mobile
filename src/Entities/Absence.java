/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Date;

/**
 *
 * @author ASUS CORE I5
 */
public class Absence {
    int id;
    String matiere,classe,hdep,hfin;
    Date date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMatiere() {
        return matiere;
    }

    public void setMatiere(String matiere) {
        this.matiere = matiere;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getHdep() {
        return hdep;
    }

    public void setHdep(String hdep) {
        this.hdep = hdep;
    }

    public String getHfin() {
        return hfin;
    }

    public void setHfin(String hfin) {
        this.hfin = hfin;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Absence(int id, String matiere, String classe,String hdep, String hfin) {
        this.id = id;
        this.matiere = matiere;
        this.classe = classe;
         this.date = date;
        this.hdep = hdep;
        this.hfin = hfin;
        
    }

    public Absence(String matiere, String classe, Date date,String hdep, String hfin) {
        this.matiere = matiere;
        this.classe = classe;
         this.date = date;
        this.hdep = hdep;
        this.hfin = hfin;
       
    }

    public Absence() {
    }
    
    @Override
    public String toString() {
        return "Absence{" + "id=" + id + ", matiere=" + matiere + ", classe=" + classe + ", hdep=" + hdep + ", hfin=" + hfin + ", date=" + date + '}';
    }
    
    
}
