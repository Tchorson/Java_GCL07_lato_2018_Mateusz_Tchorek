package com.example.hibernate.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "klienci")
public class Clients implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="uzytkownik_id")
    private int user_id;

    @Column(name="login", unique = true)
    private String login;

    @Column(name="email")
    private String email;

    @Column(name="passwd")
    private String passwd;

    @ManyToMany(cascade = {
            CascadeType.ALL,
            CascadeType.MERGE
    })
    @JoinTable(name = "klienci_grupy",
            joinColumns = @JoinColumn(name = "uzytkownik_id"),
            inverseJoinColumns = @JoinColumn(name = "grupauzytkownikow_id")
    )
    private List<Groups> groupsList;

    public Clients() {
        this.groupsList = new ArrayList<>();
    }

    public Clients(String login, String email, String passwd) {
        this.login = login;
        this.email = email;
        this.passwd = passwd;
        this.groupsList = new ArrayList<>();
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public List<Groups> getGroupsList() {
        return groupsList;
    }

    public void setGroupsList(List<Groups> groupsList) {
        this.groupsList = groupsList;
    }

    @Override
    public String toString() {
        return "Clients{" +
                "user_id=" + user_id +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", passwd='" + passwd + '\'' +
                ", groupsList=" + groupsList +
                '}';
    }
}
