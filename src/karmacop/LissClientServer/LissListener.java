/* 
 * Copyright (C) 2016 Glenn Tester.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */

package karmacop.LissClientServer;

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
