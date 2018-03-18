package com.Labki.Chat;

/**
 * Created by mtchorek on 2018-03-13.
 */
public class User {
    static long usersId = 0;
    long id = 0;
    String name;
    long createdAt;

    public User(){
        usersId++;
        id = usersId;
        name = "Jan Kowalski";
        createdAt = System.currentTimeMillis();

    }

    public void setId(long nid){
        id = nid;
    }
    public void setName(String nname){
        name = nname;
    }
    public void setCreatedAt(long ncreatedAt){
        createdAt = ncreatedAt;
    }

    public long returnId(){
        return id;
    }

    public String returnName(){
        return name;
    }
    public long returnCreatedAt(){
        return createdAt;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (createdAt != user.createdAt) return false;
        return name != null ? name.equals(user.name) : user.name == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (int) (createdAt ^ (createdAt >>> 32));
        return result;
    }
}
