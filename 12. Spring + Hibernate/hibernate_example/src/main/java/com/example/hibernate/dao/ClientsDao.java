package com.example.hibernate.dao;

import com.example.hibernate.entity.Clients;
import com.example.hibernate.entity.Groups;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClientsDao extends AbstractDAO {

    public ClientsDao() {
        setClazz(Clients.class);
    }

    public Clients getClientByLogin(String login) {
        return (Clients) entityManager.createQuery("SElECT u FROM Clients u WHERE u.login=:clientLogin")
                .setParameter("clientLogin", login)
                .getSingleResult();
    }

    public Clients getClientByEmail(String email) {
        return (Clients) entityManager.createQuery("SElECT u FROM Clients u WHERE u.email=:clientEmail")
                .setParameter("clientEmail", email)
                .getSingleResult();
    }

    public List<Groups> getClientGroupsByUser(String login) {
        Clients clients = getClientByLogin(login);
        return clients.getGroupsList();
    }


}
