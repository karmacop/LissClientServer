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

import java.util.Date;
import org.apache.xmlrpc.XmlRpcHandler;
import org.apache.xmlrpc.XmlRpcRequest;
import karmacop.liss4j.*;

/**
 *
 * @author Glenn Tester
 */
public class SimpleLissHandlerJson extends SimpleLissHandlerInterface {

    SimpleLissHandlerJsonConfig config;
    TeacherList teacherList;
    Date startDate;
    Date endDate;
    TimetableList timetableList;
    TimetableList cyclicalTimetableList;
    CalendarList calendarMapping;

    public SimpleLissHandlerJson() {
        config = new SimpleLissHandlerJsonConfig();
    }
    
    
    
    @Override
    public XmlRpcHandler hello() {
        this.setChanged();
        notifyObservers("liss.hello called");
        return (XmlRpcRequest pRequest) -> new Hello(config.sisName, config.lissVersion);
        
    }
    
    @Override
    public XmlRpcHandler getTeachers() {
        this.setChanged();
        notifyObservers("liss.getTeachers called");
        return (XmlRpcRequest pRequest) -> "getTeachers not implemented";
    }

    @Override
    public XmlRpcHandler publishTeachers() {
        this.setChanged();
        notifyObservers("liss.publishTeachers called");
        return (XmlRpcRequest pRequest) -> {
            if( pRequest.getParameterCount() != 3 ){
                this.setChanged();
                notifyObservers("liss.publishTeachers invalid params");
                return "Incorrect number of parameters. 3 needed.";
            }
            
            Authentication auth = (Authentication)pRequest.getParameter(0);
            if(false) {
                this.setChanged();
                notifyObservers("liss.publishTeachers invalid authentication");
                return "Username or password incorrect";
            }
            
            //Use Date in the future?
            Date asAtDate = ((Date)pRequest.getParameter(1));
            
            teacherList = new TeacherList((Object[]) pRequest.getParameter(2));
            this.setChanged();
            notifyObservers("liss.publishTeachers completed");
            return "";
        };
    }

    @Override
    public XmlRpcHandler getRooms() {
        this.setChanged();
        notifyObservers("liss.getRooms called");
        return (XmlRpcRequest pRequest) -> "getRooms not implemented";
    }

    @Override
    public XmlRpcHandler publishRooms() {
        this.setChanged();
        notifyObservers("liss.publishRooms called");
        return (XmlRpcRequest pRequest) -> "publishRooms not implemented";
    }

    @Override
    public XmlRpcHandler getClasses() {
        this.setChanged();
        notifyObservers("liss.getClasses called");
        return (XmlRpcRequest pRequest) -> "getClasses not implemented";
    }

    @Override
    public XmlRpcHandler publishClasses() {
        this.setChanged();
        notifyObservers("liss.publishClasses called");
        return (XmlRpcRequest pRequest) -> "publishClasses not implemented";
    }

    @Override
    public XmlRpcHandler getTimetable() {
        this.setChanged();
        notifyObservers("liss.getTimetable called");
        return (XmlRpcRequest pRequest) -> "getTimetable not implemented";
    }

    @Override
    public XmlRpcHandler publishTimetable() {
        this.setChanged();
        notifyObservers("liss.publishTimetable called");
        
        return (XmlRpcRequest pRequest) -> {
            if( pRequest.getParameterCount() != 8 ){
                this.setChanged();
                notifyObservers("liss.publishTimetable invalid params");
                return "Incorrect number of parameters. 8 needed.";
            }
           
            Authentication auth = (Authentication)pRequest.getParameter(0);
            if(false) {
                this.setChanged();
                notifyObservers("liss.publishTimetable invalid authentication");
                return "Username or password incorrect";
            }
            
            cyclicalTimetableList = (TimetableList) pRequest.getParameter(1);
            
            int academicYear = (int)pRequest.getParameter(2);
            
            int timetableId = (int)pRequest.getParameter(3);
            
            int termId = (int)pRequest.getParameter(4);
            
            if(null == startDate) {
                startDate = (Date)pRequest.getParameter(5);
            } else {
                startDate = startDate.before((Date)pRequest.getParameter(5))?(Date)pRequest.getParameter(5):startDate;
            }
            
            if(null == endDate) {
                endDate = (Date)pRequest.getParameter(6);
            } else {
                endDate = endDate.after((Date)pRequest.getParameter(6))?(Date)pRequest.getParameter(6):endDate;
            }
            
            boolean createClassesFlag = (boolean)pRequest.getParameter(7);
            
            this.setChanged();
            notifyObservers("liss.publishTimetable completed");
            return "";
        };
        
        //return (XmlRpcRequest pRequest) -> "publishTimetable not implemented";
    }

    @Override
    public XmlRpcHandler getTimetableStructures() {
        this.setChanged();
        notifyObservers("liss.getTimetableStructures called");
        return (XmlRpcRequest pRequest) -> "getTimetableStructures not implemented";
    }

    @Override
    public XmlRpcHandler publishTimetableStructures() {
        this.setChanged();
        notifyObservers("liss.publishTimetableStructures called");
        return (XmlRpcRequest pRequest) -> "publishTimetableStructures not implemented";
    }

    @Override
    public XmlRpcHandler getClassMemberships() {
        this.setChanged();
        notifyObservers("liss.getClassMemberships called");
        return (XmlRpcRequest pRequest) -> "getClassMemberships not implemented";
    }

    @Override
    public XmlRpcHandler publishClassMemberships() {
        this.setChanged();
        notifyObservers("liss.publishClassMemberships called");
        return (XmlRpcRequest pRequest) -> "publishClassMemberships not implemented";
    }

    @Override
    public XmlRpcHandler getStudents() {
        this.setChanged();
        notifyObservers("liss.getStudents called");
        return (XmlRpcRequest pRequest) -> "getStudents not implemented";
    }

    @Override
    public XmlRpcHandler publishStudents() {
        this.setChanged();
        notifyObservers("liss.publishStudents called");
        return (XmlRpcRequest pRequest) -> "publishStudents not implemented";
    }

    @Override
    public XmlRpcHandler getStudentFamilies() {
        this.setChanged();
        notifyObservers("liss.getStudentFamilies called");
        return (XmlRpcRequest pRequest) -> "getStudentFamilies not implemented";
    }

    @Override
    public XmlRpcHandler publishStudentFamilies() {
        this.setChanged();
        notifyObservers("liss.publishStudentFamilies called");
        return (XmlRpcRequest pRequest) -> "publishStudentFamilies not implemented";
    }

    @Override
    public XmlRpcHandler getDailyData() {
        this.setChanged();
        notifyObservers("liss.getDailyData called");
        return (XmlRpcRequest pRequest) -> "getDailyData not implemented";
    }

    @Override
    public XmlRpcHandler publishDailyData() {
        this.setChanged();
        notifyObservers("liss.publishDailyData called");
        
        return (XmlRpcRequest pRequest) -> {
            if( pRequest.getParameterCount() != 4 ){
                this.setChanged();
                notifyObservers("liss.publishDailyData invalid params");
                return "Incorrect number of parameters. 4 needed.";
            }
            
            Authentication auth = (Authentication)pRequest.getParameter(0);
            if(false) {
                this.setChanged();
                notifyObservers("liss.publishDailyData invalid authentication");
                return "Username or password incorrect";
            }
            
            //Use Date in the future?
            startDate  = ((Date)pRequest.getParameter(1));
            //Use Date in the future?
            endDate = ((Date)pRequest.getParameter(2));
            
            timetableList = (TimetableList) pRequest.getParameter(3);
            this.setChanged();
            notifyObservers("liss.publishDailyData completed");
            return "";
        };
    }

    @Override
    public XmlRpcHandler getBellTimes() {
        this.setChanged();
        notifyObservers("liss.getBellTimes called");
        return (XmlRpcRequest pRequest) -> "getBellTimes not implemented";
    }

    @Override
    public XmlRpcHandler publishBellTimes() {
        this.setChanged();
        notifyObservers("liss.publishBellTimes called");
        return (XmlRpcRequest pRequest) -> "publishBellTimes not implemented";
    }

    @Override
    public XmlRpcHandler getCalendar() {
        this.setChanged();
        notifyObservers("liss.getCalendar called");
        return (XmlRpcRequest pRequest) -> "getCalendar not implemented";
    }

    @Override
    public XmlRpcHandler publishCalendar() {
        this.setChanged();
        notifyObservers("liss.publishCalendar called");
        
        return (XmlRpcRequest pRequest) -> {
            if( pRequest.getParameterCount() != 8 ){
                this.setChanged();
                notifyObservers("liss.publishCalendar invalid params");
                return "Incorrect number of parameters. 2 needed.";
            }
           
            Authentication auth = (Authentication)pRequest.getParameter(0);
            if(false) {
                this.setChanged();
                notifyObservers("liss.publishCalendar invalid authentication");
                return "Username or password incorrect";
            }
            
            calendarMapping = (CalendarList) pRequest.getParameter(1);

            this.setChanged();
            notifyObservers("liss.publishCalendar completed");
            return "";
        };
    }

    @Override
    public XmlRpcHandler changeClassMembership() {
        this.setChanged();
        notifyObservers("liss.changeClassMembership called");
        return (XmlRpcRequest pRequest) -> "changeClassMembership not implemented";
    }
        
    @Override
    public XmlRpcHandler errorText(String text) {
        return (XmlRpcRequest pRequest) -> text;
    }


}
