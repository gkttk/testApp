package org.testApp;

import javax.persistence.*;

@Entity
public class Theme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String name;
    @Column(name = "owner_id")
    private Integer id_owner;

    public Theme(){}

    public Theme(Integer id, String name, Integer id_owner) {
        this.id = id;
        this.name = name;
        this.id_owner = id_owner;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId_owner(Integer id_owner) {
        this.id_owner = id_owner;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getId_owner() {
        return id_owner;
    }


    @Override
    public String toString() {
        return id + " - " + name + " - " + id_owner;
    }
}
