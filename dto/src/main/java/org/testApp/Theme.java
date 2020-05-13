package org.testApp;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Theme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String name;
    @Column(name = "owner_id")
    private Integer ownerId;


    @OneToMany(mappedBy = "qTheme", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Question> tQuestions = new ArrayList<>();


    @OneToMany(mappedBy = "questionnaireTheme", fetch = FetchType.LAZY)
    private List<Questionnaire> uQuestionnaires = new ArrayList<>();

    public Theme(){}

    public Theme(Integer id, String name, Integer ownerId) {
        this.id = id;
        this.name = name;
        this.ownerId = ownerId;
    }

    public List<Question> gettQuestions() {
        return tQuestions;
    }

    public void settQuestions(List<Question> tQuestions) {
        this.tQuestions = tQuestions;
    }

    public List<Questionnaire> getuQuestionnaires() {
        return uQuestionnaires;
    }

    public void setuQuestionnaires(List<Questionnaire> uQuestionnaires) {
        this.uQuestionnaires = uQuestionnaires;
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
