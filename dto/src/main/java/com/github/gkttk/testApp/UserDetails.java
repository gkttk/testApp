package com.github.gkttk.testApp;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "user_details")
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "name", nullable = true)
    private String name;
    @Column(name = "surname", nullable = true)
    private String surname;
    @Column(name = "age", nullable = true)
    private Integer age;
    @CreationTimestamp
    @Column(name = "registration_date")
    private LocalDate registrationDate;


    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name ="user_id")
    private User detailsUser;


    public UserDetails() {
    }

    public UserDetails(Integer id, String name, String surname, Integer age, User detailsUser) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.detailsUser = detailsUser;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public User getDetailsUser() {
        return detailsUser;
    }

    public void setDetailsUser(User detailsUser) {
        this.detailsUser = detailsUser;
    }
}
