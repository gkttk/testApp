package org.testApp;

import org.testApp.enums.Role;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String login;
    @Column
    private String password;
    @Column
    private String email;
    @Enumerated(EnumType.STRING)
    @Column
    private Role role;

    @OneToMany(mappedBy = "questionnaireUser", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Questionnaire> uQuestionnaires = new ArrayList<>();

    @OneToOne(mappedBy = "detailsUser", fetch = FetchType.EAGER,
            cascade = CascadeType.ALL, orphanRemoval = true)
    private UserDetails uDetails;

    public User(){}

    public User(Integer id, String login, String password, String email) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.email = email;
        this.role = Role.STUDENT;

    }

    public User(String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.role = Role.STUDENT;
    }

    public User(Integer id, String login, String password, String email, Role role) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public List<Questionnaire> getuQuestionnaires() {
        return uQuestionnaires;
    }

    public void setuQuestionnaires(List<Questionnaire> uQuestionnaires) {
        this.uQuestionnaires = uQuestionnaires;
    }

    public UserDetails getuDetails() {
        return uDetails;
    }

    public void setuDetails(UserDetails uDetails) {
        this.uDetails = uDetails;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public Role getRole() {
        return role;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return login.equals(user.login) &&
                password.equals(user.password) &&
                email.equals(user.email) &&
                role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password, email, role);
    }

    @Override
    public String toString() {
        return  id + " - " + role.name() + " - " + login + " - " + email;
    }


}

