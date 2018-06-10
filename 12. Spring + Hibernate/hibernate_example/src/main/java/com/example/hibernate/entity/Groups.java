package com.example.hibernate.entity;


import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "grupy")
public class Groups implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="grupa_id")
    private int group_id;

    @Column(name="nazwa", unique = true)
    private String name;

    @ManyToMany(cascade = {
            CascadeType.ALL,
            CascadeType.MERGE
    })
    @JoinTable(name = "klienci_grupy",
            joinColumns = @JoinColumn(name = "grupauzytkownikow_id"),
            inverseJoinColumns = @JoinColumn(name = "uzytkownik_id")
    )
    private List<Clients> clientsList;

    public Groups() {
        this.clientsList = new ArrayList<>();
    }

    public Groups(String name) {
        this.name = name;
        this.clientsList = new ArrayList<>();
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Clients> getClientsList() {
        return clientsList;
    }

    public void setClientsList(List<Clients> clientsList) {
        this.clientsList = clientsList;
    }

    @Override
    public String toString() {
        return "Groups{" +
                "group_id=" + group_id +
                ", name='" + name + '\'' +
                '}';
    }
}
