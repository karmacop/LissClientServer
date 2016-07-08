package karmacop.LissClientServer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Glenn Tester
 */
public class UserData {
    String username;
    String salt;
    String passwordHash;

    public UserData(String username, String salt, String passwordHash) {
        this.username = username;
        this.salt = salt;
        this.passwordHash = passwordHash;
    }
    
    
}
