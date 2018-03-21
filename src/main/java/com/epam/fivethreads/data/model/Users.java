package com.epam.fivethreads.data.model;

import com.epam.fivethreads.data.utils.parsersXLSandCSV.anotations.ParserElement;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "users")
@XmlSeeAlso({User.class})
@XmlAccessorType(XmlAccessType.FIELD)
public class Users {
    @XmlElement(name = "user")
    @ParserElement
    private List<User> userList;

    public Users() {
        userList = new ArrayList<User>();
    }

    public void add(User user) {
        this.userList.add(user);
    }

    public int size() {
        return userList.size();
    }

    public User getUser(int index) {
        return userList.get(index);
    }

    public List<User> getUsers() {
        return userList;
    }

    public void setUsers(List<User> userList) {
        this.userList = userList;
    }

    public String toString() {
        StringBuilder rez = new StringBuilder();
        for (User user : userList) {
            rez.append(user.toString());
            rez.append("\n");
        }
        return rez.toString();
    }
}
