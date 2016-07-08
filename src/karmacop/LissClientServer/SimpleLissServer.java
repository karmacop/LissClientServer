package karmacop.LissClientServer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.xmlrpc.server.XmlRpcHandlerMapping;
import org.apache.xmlrpc.server.XmlRpcServer;
import org.apache.xmlrpc.server.XmlRpcServerConfigImpl;
import org.apache.xmlrpc.webserver.WebServer;

/**
 *
 * @author Glenn Tester
 */
public class SimpleLissServer {
    
    WebServer webServer;
    XmlRpcServer rpcServer;
    XmlRpcServerConfigImpl config;
    public XmlRpcHandlerMapping mapping;
    
    
    public SimpleLissServer(int port) {
        webServer = new WebServer(port);
        System.out.println("Creating web server");
        config = new XmlRpcServerConfigImpl();
        rpcServer = webServer.getXmlRpcServer();
        rpcServer.setConfig(config);
        mapping = new SimpleLissHandlerJson();
        rpcServer.setHandlerMapping(mapping);
        //start();
    }
    
    public void start(){
        try {
            webServer.start();
            Logger.getLogger(SimpleLissServer.class.getName()).log(Level.INFO, "Server Started using " + rpcServer.getHandlerMapping().getClass().getName());
        } catch (IOException ex) {
            Logger.getLogger(SimpleLissServer.class.getName()).log(Level.SEVERE, "Error starting server", ex.getCause());
        }
    }
    
    public static String passwordHash(String salt, String password) {
        String generatedPassword = null;
        try {

            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt.getBytes("UTF-8"));
            byte[] bytes = md.digest(password.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } 
        catch (NoSuchAlgorithmException ex) 
        {
            Logger.getLogger(SimpleLissServer.class.getName()).log(Level.SEVERE, "Unknown type SHA-512", ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(SimpleLissServer.class.getName()).log(Level.SEVERE, "Unknown encoding UTF-8", ex);
        }
        return generatedPassword;
    }
    
    public static String generateSalt() {
        SecureRandom sr = new SecureRandom();
        byte bytes[] = new byte[32];
        sr.nextBytes(bytes);
        StringBuilder sb = new StringBuilder();
        for(int i=0; i< bytes.length ;i++)
        {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }
    
    public static boolean passwordCorrect(String salt, String password, String hashedPassword) {
        String calculatedHash = passwordHash(salt, password);
        if( calculatedHash.equals(hashedPassword) ) {
            return true;
        }
        return false;
    }
    
}

