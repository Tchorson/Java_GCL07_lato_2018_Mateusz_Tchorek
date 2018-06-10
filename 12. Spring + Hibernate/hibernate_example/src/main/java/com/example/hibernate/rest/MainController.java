package com.example.hibernate.rest;

import com.example.hibernate.dao.ClientsDao;
import com.example.hibernate.dao.GroupDAO;
import com.example.hibernate.entity.Clients;
import com.example.hibernate.entity.Groups;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;
import java.util.List;

@RestController
public class MainController {

    private final ClientsDao clientsDao;

    private final GroupDAO groupDAO;

    @Autowired
    public MainController(ClientsDao clientsDao, GroupDAO groupDAO) {
        this.clientsDao = clientsDao;
        this.groupDAO = groupDAO;
    }

    @RequestMapping(value = "/client/add", method = RequestMethod.POST)
    public void addClient(@RequestParam(value = "login") String login, @RequestParam(value = "email") String email, @RequestParam(value = "passwd") String passwd) {
        clientsDao.create(new Clients(login, email, passwd));
    }

    @RequestMapping(value = "/client/{login}", method = RequestMethod.GET)
    public Clients getClient(@PathVariable(value = "login") String login) {
        try {
            return clientsDao.getClientByLogin(login);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

    }

    @RequestMapping(value = "/client/{login}/groups", method = RequestMethod.GET)
    public List getClientGroups(@PathVariable(value = "login") String login) {
        return clientsDao.getClientGroupsByUser(login);
    }

    @RequestMapping(value = "/client/{login}", method = RequestMethod.DELETE)
    public void removeClient(@PathVariable(value = "login") String login) {
        try {
            Clients clients = clientsDao.getClientByLogin(login);
            clientsDao.delete(clients);
        } catch (NoResultException e) {

        }
    }

    @RequestMapping(value = "/client/all", method = RequestMethod.DELETE)
    public void removeAllClients() {
        List<Clients> list = clientsDao.findAll();
        for (Clients u : list) {
            clientsDao.delete(u);
        }
    }

    @RequestMapping(value = "/group/add", method = RequestMethod.POST)
    public void addClientgroup(@RequestParam(value = "nazwa") String name) {
        groupDAO.create(new Groups(name));

    }

    @RequestMapping(value = "/group/{nazwa}", method = RequestMethod.DELETE)
    public void removeClientgroup(@RequestParam(value = "nazwa") String name) {
        Groups groups = groupDAO.getGroupByName(name);
        groupDAO.delete(groups);
    }

    @RequestMapping(value = "/group/all", method = RequestMethod.DELETE)
    public void removeAllClientgroup() {
        List<Groups> groupsList = groupDAO.findAll();
        for (Groups ug : groupsList) {
            groupDAO.delete(ug);
        }
    }

    @RequestMapping(value = "/group/{nazwagrupy}/add/{client}", method = RequestMethod.POST)
    public void addUserToUsergroup(@PathVariable(value = "nazwagrupy") String nazwagrupy, @PathVariable(value = "client") String client) {
        Groups ug = groupDAO.getGroupByName(nazwagrupy);
        Clients u = clientsDao.getClientByLogin(client);
        ug.getClientsList().add(u);
        groupDAO.update(ug);
    }

    @RequestMapping(value = "/group/{nazwagrupy}/remove/{client}", method = RequestMethod.POST)
    public void removeUserFromUsergroup(@PathVariable(value = "nazwagrupy") String nazwagrupy, @PathVariable(value = "client") String client) {
        Groups ug = groupDAO.getGroupByName(nazwagrupy);
        Clients u = clientsDao.getClientByLogin(client);
        ug.getClientsList().remove(u);
        groupDAO.update(ug);
    }

}
