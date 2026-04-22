package org.quizz.politicianparty.model;

import jakarta.persistence.*;

@Entity
@Table(name = "politicians")
public class Politician {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String party;
    @Column(name = "image_path")
    private String imagePath;

    public Politician(Long id, String name, String surname, String party, String imagePath) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.party = party;
        this.imagePath = imagePath;
    }

    public Politician() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

}
