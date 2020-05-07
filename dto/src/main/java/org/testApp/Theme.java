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
    private Integer ownerId;

    public Theme(){}

    public Theme(Integer id, String name, Integer ownerId) {
        this.id = id;
        this.name = name;
        this.ownerId = ownerId;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOwnerId(Integer id_owner) {
        this.ownerId = id_owner;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getOwnerId() {
        return ownerId;
    }


    @Override
    public String toString() {
        return id + " - " + name + " - " + ownerId;
    }
}
