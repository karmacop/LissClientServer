package karmacop.LissClientServer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Glenn Tester
 */
public class LissListener {
    static Logger logger;
    static ConsoleHandler consoleHandler;
    
    public static void main(String[] args) throws IOException {
        consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.ALL);
        logger = Logger.getLogger(LissListener.class.getName());
        logger.addHandler(consoleHandler);
        SimpleLissServer sls = new SimpleLissServer(80);
        sls.rpcServer.setHandlerMapping( new SimpleLissHandler2Console(logger) );
        sls.start();
    }
}
