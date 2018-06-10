package com.example.hibernate.dao;

import com.example.hibernate.entity.Clients;
import com.example.hibernate.entity.Groups;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GroupDAO extends AbstractDAO {

    public GroupDAO() {
        setClazz(Groups.class);
    }

    public Groups getGroupByName(String name) {
        return (Groups) entityManager.createQuery("SElECT ug FROM Groups ug WHERE ug.name=:groupName")
                .setParameter("groupName", name)
                .getSingleResult();
    }

    public List<Clients> getClientsInUserGroup(String name) {
        Groups groups = getGroupByName(name);
        return groups.getClientsList();
    }
}
