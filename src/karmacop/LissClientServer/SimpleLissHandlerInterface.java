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

import java.awt.event.ActionListener;
import java.util.ArrayList;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.XmlRpcHandler;
import org.apache.xmlrpc.server.XmlRpcHandlerMapping;
import org.apache.xmlrpc.server.XmlRpcNoSuchHandlerException;

/**
 *
 * @author Glenn Tester
 */
public abstract class SimpleLissHandlerInterface extends java.util.Observable implements XmlRpcHandlerMapping {

    public ArrayList<ActionListener> listeners;

    public SimpleLissHandlerInterface() {
        listeners = new ArrayList<>();
    }
    
    @Override
    public XmlRpcHandler getHandler(String handlerName) throws XmlRpcNoSuchHandlerException, XmlRpcException {
        switch (handlerName) {
            case "liss.hello":
                return hello();
            case "liss.getTeachers":
                return getTeachers();
            case "liss.publishTeachers":
                return publishTeachers();
            case "liss.getRooms":
                return getRooms();
            case "liss.publishRooms":
                return publishRooms();
            case "liss.getClasses":
                return getClasses();
            case "liss.publishClasses":
                return publishClasses();
            case "liss.getTimetable":
                return getTimetable();
            case "liss.publishTimetable":
                return publishTimetable();
            case "liss.getTimetableStructures":
                return getTimetableStructures();
            case "liss.publishTimetableStructures":
                return publishTimetableStructures();
            case "liss.getClassMemberships":
                return getClassMemberships();
            case "liss.publishClassMemberships":
                return publishClassMemberships();
            case "liss.getStudents":
                return getStudents();
            case "liss.publishStudents":
                return publishStudents();
            case "liss.getStudentFamilies":
                return getStudentFamilies();
            case "liss.publishStudentFamilies":
                return publishStudentFamilies();    
            case "liss.getDailyData":
                return getDailyData();
            case "liss.publishDailyData":
                return publishDailyData();       
            case "liss.getBellTimes":
                return getBellTimes();
            case "liss.publishBellTimes":
                return publishBellTimes();
            case "liss.getCalendar":
                return getCalendar();
            case "liss.publishCalendar":
                return publishCalendar();
            case "liss.changeClassMembership":
                return changeClassMembership();    
            default:
                return errorText("Unsupported method: " + handlerName + ". Try liss.hello");
        }
    }
    
    public abstract XmlRpcHandler hello();
    public abstract XmlRpcHandler getTeachers();
    public abstract XmlRpcHandler publishTeachers();
    public abstract XmlRpcHandler getRooms();
    public abstract XmlRpcHandler publishRooms();
    public abstract XmlRpcHandler getClasses();
    public abstract XmlRpcHandler publishClasses();
    public abstract XmlRpcHandler getTimetable();
    public abstract XmlRpcHandler publishTimetable();
    public abstract XmlRpcHandler getTimetableStructures();
    public abstract XmlRpcHandler publishTimetableStructures();
    public abstract XmlRpcHandler getClassMemberships();
    public abstract XmlRpcHandler publishClassMemberships();
    public abstract XmlRpcHandler getStudents();
    public abstract XmlRpcHandler publishStudents();
    public abstract XmlRpcHandler getStudentFamilies();
    public abstract XmlRpcHandler publishStudentFamilies();
    public abstract XmlRpcHandler getDailyData();
    public abstract XmlRpcHandler publishDailyData();
    public abstract XmlRpcHandler getBellTimes();
    public abstract XmlRpcHandler publishBellTimes();
    public abstract XmlRpcHandler getCalendar();
    public abstract XmlRpcHandler publishCalendar();
    public abstract XmlRpcHandler changeClassMembership();
    public abstract XmlRpcHandler errorText(String text);
}