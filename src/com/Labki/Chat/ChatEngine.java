
package com.Labki.Chat;


import java.util.*;


/**
 * Created by mtchorek on 2018-03-13.
 */
public class ChatEngine {
    Map<Long, User> UserList;
    ArrayList<String> BannedPeople;

    long ChatEngineId; //ok

    public ChatEngine(){
        UserList = new TreeMap<Long,User>();
        BannedPeople = new ArrayList<String>();

        ChatEngineId = 0;
    } //ok


    public void addUser(User user) throws UserAddException{
        if(user == null || BannedPeople.contains(user.returnName()))
            throw new UserAddException();


        Collection<User> userNames = UserList.values();

        for(User tmpuser : UserList.values()){
            if(userNames.contains(tmpuser))
                throw new UserAddException();

        }

        ChatEngineId++;
        UserList.put(ChatEngineId,user);


    } //chyba ok

    void createBan(String usrName){
        if(!BannedPeople.contains(usrName))
            BannedPeople.add(usrName);

        for(User userTmp: UserList.values()){
            if(BannedPeople.contains(userTmp.returnName()))
                UserList.remove(userTmp);

        }



    }//chybaok

    public void removeUser(long userId) throws UserRemoveException{
        if(userId == 0)
            throw new UserRemoveException();

        UserList.remove(userId);
       // ChatEngineId--;
    } //ok

    public int getNumberOfUsers(){
     return UserList.size();
    } //ok




    void removeBan(String userName){
        if(BannedPeople.contains(userName))
            BannedPeople.remove(userName);

    }//ok

    public boolean hasUser(long userId){
        return UserList.containsKey(userId);
    } //ok

    public boolean hasUser(String Username){
        return UserList.containsValue(Username);
    } //ok

    public boolean broadcastMessage(long userId,String message){
        for(User userTmp: UserList.values()){

            System.out.println("uzytkownik: "+userTmp.returnName()+" o id: "+userTmp.returnId());
        }
        return true;
    }

}

