package com.example.airlines.model;

import javax.persistence.*;

@Entity(name = "Access")
public class Access {

    private int idAccess;
    private String nameAccess;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_access")
    public int getIdAccess() {
        return idAccess;
    }

    @Column(name = "Name_access")
    public String getNameAccess() {
        return nameAccess;
    }

    public void setIdAccess(int idAccess) {
        this.idAccess = idAccess;
    }


    public void setNameAccess(String nameAccess) {
        this.nameAccess = nameAccess;
    }
}
