package org.testApp;

import org.hibernate.annotations.Type;
import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tempnewtheme")
public class TempNewTheme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer Id;
    @Column(name = "owner_id")
    private Integer ownerId;
    @Column(name = "temp_theme_name")
    private String themeName;
    @Column(name = "number_of_questions")
    private int numberOfQuestions;
    @Column(name = "permit")
    private Boolean permit = false;


    public TempNewTheme(int ownerId, String themeName, int numberOfQuestions) {
        this.ownerId = ownerId;
        this.themeName = themeName;
        this.numberOfQuestions = numberOfQuestions;
    }

    public TempNewTheme() {
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public String getThemeName() {
        return themeName;
    }

    public void setThemeName(String themeName) {
        this.themeName = themeName;
    }

    public int getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public void setNumberOfQuestions(int numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
    }

    public Boolean getPermit() {
        return permit;
    }

    public void setPermit(Boolean permit) {
        this.permit = permit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TempNewTheme that = (TempNewTheme) o;
        return numberOfQuestions == that.numberOfQuestions &&
                ownerId.equals(that.ownerId) &&
                themeName.equals(that.themeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ownerId, themeName, numberOfQuestions);
    }
}
