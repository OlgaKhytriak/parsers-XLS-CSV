package com.epam.fivethreads.data.model;

import com.epam.fivethreads.data.utils.parsersXLSandCSV.anotations.ParserElement;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "user")
public class User implements Cloneable {
    @ParserElement("login")
    @XmlElement(required = true, name = "user-login")
    private String userLogin;

    @ParserElement("password")
    @XmlElement(required = true, name = "user-password")
    private String userPassword;

    public User() {
    }

    public User(String userLogin, String userPassword) {
        this.setUserPassword(userPassword);
        this.setUserLogin(userLogin);
    }

    public User copy() {
        User user = new User();
        user.setUserLogin(this.userLogin);
        user.setUserPassword(this.userPassword);
        return user;
    }

    @Override
    public User clone() {
        Object clonedObj = null;
        try {
            clonedObj = super.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println("Error in User.clone()");
            e.printStackTrace();
        }
        return (User) clonedObj;
    }

    public String toString() {
        return String.format(
                "[USER]: user-login= %s ; user-password= %s ",
                userLogin, userPassword);
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

}
