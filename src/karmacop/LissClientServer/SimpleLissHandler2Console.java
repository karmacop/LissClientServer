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
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import karmacop.liss4j.*;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.XmlRpcHandler;
import org.apache.xmlrpc.XmlRpcRequest;
import org.apache.xmlrpc.server.XmlRpcNoSuchHandlerException;

/**
 *
 * @author Glenn Tester
 */
public class SimpleLissHandler2Console extends SimpleLissHandlerInterface {

    SimpleLissHandlerJsonConfig config;
    TeacherList teacherList;
    Date startDate;
    Date endDate;
    TimetableList timetableList;
    TimetableList cyclicalTimetableList;
    CalendarList calendarMapping;
    static Logger logger;
    
    @Override
    public XmlRpcHandler getHandler(String handlerName) throws XmlRpcNoSuchHandlerException, XmlRpcException {
        System.out.println("Mathod:"+handlerName);
        return super.getHandler(handlerName);
    }

    public SimpleLissHandler2Console(Logger logger) {
        SimpleLissHandler2Console.logger = logger;
        config = new SimpleLissHandlerJsonConfig();
    }
    
    
    
    @Override
    public XmlRpcHandler hello() {
        this.setChanged();
        notifyObservers("liss.hello called");
        Hello returnXml = new Hello(config.sisName, config.lissVersion);
        //logger.log(Level.INFO, "liss.hello",returnXml);
        return (XmlRpcRequest pRequest) -> returnXml;
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
        
        XmlRpcHandler handler = (XmlRpcRequest pRequest) -> {
            StringBuilder sb = new StringBuilder();
            sb.append( pRequest.getMethodName() ).append("\n");
            sb.append( pRequest.getParameter(0) ).append("\n");
            sb.append( pRequest.getParameter(1) ).append("\n");
            for (Teacher teacher : new TeacherList( (Object[]) pRequest.getParameter(2))) {
                sb.append( teacher ).append("\n");
            }
            
            //System.out.println(sb);
            logger.log(Level.INFO, "liss.publishTeachers\n"+sb.toString() );
            return "";
        };
        return handler;
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
        
        XmlRpcHandler handler = (XmlRpcRequest pRequest) -> {
            StringBuilder sb = new StringBuilder();
            sb.append( pRequest.getMethodName() ).append("\n");
            sb.append( pRequest.getParameter(0) ).append("\n");
            sb.append( pRequest.getParameter(1) ).append("\n");
            for (Room room : new RoomList( (Object[]) pRequest.getParameter(2))) {
                sb.append( room ).append("\n");
            }
            
            //System.out.println(sb);
            logger.log(Level.INFO, "liss.publishRooms\n"+sb.toString() );
            return "";
        };
        return handler;
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
        
        XmlRpcHandler handler = new XmlRpcHandler() {
            

            @Override
            public Object execute(XmlRpcRequest pRequest) throws XmlRpcException {
                StringBuilder sb = new StringBuilder();
                sb.append( pRequest.getMethodName() ).append("\n");
                sb.append( pRequest.getParameter(0) ).append("\n");
                sb.append( pRequest.getParameter(1) ).append("\n");
                //for (CourseClass courseClass : new CourseClassList( (Object[]) pRequest.getParameter(2)) ) {
                for (CourseClass courseClass : (CourseClassList) pRequest.getParameter(2) ) {
                    sb.append( courseClass ).append("\n");
                }

                //System.out.println(sb);
                logger.log(Level.INFO, "liss.publishClasses\n"+sb.toString() );
                return "";
            }
            
        };
        return handler;
    }
    
    public String request2String(XmlRpcRequest req) {
        StringBuilder sb = new StringBuilder();
        sb.append(req.getMethodName()).append("\n");
        for (int i = 0; i < req.getParameterCount(); i++) {
            System.out.println(req.getParameter(i).getClass());
            if(req.getParameter(i).getClass() == Object.class) {
                sb.append( (HashMap<String,Object>) req.getParameter(i) ).append("\n");
            } else {
                sb.append(req.getParameter(i)).append("\n");
            }
        }
        System.out.println(sb);
        return sb.toString();
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
        
        XmlRpcHandler handler = (XmlRpcRequest pRequest) -> {
            StringBuilder sb = new StringBuilder();
            sb.append( pRequest.getMethodName() ).append("\n");
            sb.append( pRequest.getParameter(0) ).append("\n");
            //for (Timetable timetable : new TimetableList( (Object[]) pRequest.getParameter(1))) {
            for (Timetable timetable : (TimetableList) pRequest.getParameter(1)) {
                sb.append( timetable ).append("\n");
            }
            sb.append( pRequest.getParameter(2) ).append("\n");
            sb.append( pRequest.getParameter(3) ).append("\n");
            sb.append( pRequest.getParameter(4) ).append("\n");
            sb.append( (Date)pRequest.getParameter(5) ).append("\n");
            sb.append( (Date)pRequest.getParameter(6) ).append("\n");
            sb.append( pRequest.getParameter(7) ).append("\n");
            //System.out.println(sb);
            logger.log(Level.INFO, "liss.publishTimetable\n"+sb.toString() );
            return "";
        };
        return handler;
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
        
        XmlRpcHandler handler = (XmlRpcRequest pRequest) -> {
            StringBuilder sb = new StringBuilder();
            sb.append( pRequest.getMethodName() ).append("\n");
            sb.append( pRequest.getParameter(0) ).append("\n");
            sb.append( pRequest.getParameter(1) ).append("\n");
            for (TimetableStructure timetableStructure : (TimetableStructureList) pRequest.getParameter(2)) {
                sb.append( timetableStructure ).append("\n");
            }
            
            //System.out.println(sb);
            logger.log(Level.INFO, "liss.publishTimetableStructures\n"+sb.toString() );
            return "";
        };
        return handler;
    }

    @Override
    public XmlRpcHandler getClassMemberships() {
        this.setChanged();
        notifyObservers("liss.getClassMemberships called");
        return (XmlRpcRequest pRequest) -> "getClassMembership not implemented";
    }

    @Override
    public XmlRpcHandler publishClassMemberships() {
        this.setChanged();
        notifyObservers("liss.publishClassMemberships called");
        System.out.println("class memebr caslled");
        XmlRpcHandler handler = (XmlRpcRequest pRequest) -> {
            StringBuilder sb = new StringBuilder();
            sb.append( pRequest.getMethodName() ).append("\n");
            sb.append( pRequest.getParameter(0) ).append("\n");
            
            for (ClassMembership classMembership : (ClassMembershipList) pRequest.getParameter(1)) {
                sb.append( classMembership ).append("\n");
            }
            sb.append( (Date)pRequest.getParameter(2) ).append("\n");
            //System.out.println(sb);
            logger.log(Level.INFO, "liss.publishClassMembership\n"+sb.toString() );
            return "";
        };
        return handler;
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
        
        XmlRpcHandler handler = (XmlRpcRequest pRequest) -> {
            StringBuilder sb = new StringBuilder();
            sb.append( pRequest.getMethodName() ).append("\n");
            sb.append( (Date) pRequest.getParameter(0) ).append("\n");
            sb.append( pRequest.getParameter(1) ).append("\n");
            for (Student student : new StudentList( (Object[]) pRequest.getParameter(2))) {
                sb.append( student ).append("\n");
            }
            
            //System.out.println(sb);
            logger.log(Level.INFO, "liss.publishStudents\n"+sb.toString() );
            return "";
        };
        return handler;
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
        
        XmlRpcHandler handler = (XmlRpcRequest pRequest) -> {
            StringBuilder sb = new StringBuilder();
            sb.append( pRequest.getMethodName() ).append("\n");
            sb.append( pRequest.getParameter(0) ).append("\n");
            sb.append( pRequest.getParameter(1) ).append("\n");
            for (DailyData dailyData : (DailyDataList) pRequest.getParameter(2)) {
                sb.append( dailyData ).append("\n");
            }
            
            //System.out.println(sb);
            logger.log(Level.INFO, "liss.publishDailyData\n"+sb.toString() );
            return "";
        };
        return handler;
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
        
        XmlRpcHandler handler = (XmlRpcRequest pRequest) -> {
            StringBuilder sb = new StringBuilder();
            sb.append( pRequest.getMethodName() ).append("\n");
            sb.append( pRequest.getParameter(0) ).append("\n");
            sb.append( pRequest.getParameter(1) ).append("\n");
            for (BellTime bellTime : (BellTimeList) pRequest.getParameter(2)) {
                sb.append( bellTime ).append("\n");
            }
            
            //System.out.println(sb);
            logger.log(Level.INFO, "liss.publishBellTimes\n"+sb.toString() );
            return "";
        };
        return handler;
    }

    @Override
    public XmlRpcHandler getCalendar() {
        this.setChanged();
        notifyObservers("liss.getCalendar called");
        
        XmlRpcHandler handler = (XmlRpcRequest pRequest) -> {
            StringBuilder sb = new StringBuilder();
            sb.append( pRequest.getMethodName() ).append("\n");
            sb.append( pRequest.getParameter(0) ).append("\n");
            sb.append( pRequest.getParameter(1) ).append("\n");
            for (Calendar bellTime : (CalendarList) pRequest.getParameter(2)) {
                sb.append( bellTime ).append("\n");
            }
            
            //System.out.println(sb);
            logger.log(Level.INFO, "liss.getCalendar\n"+sb.toString() );
            return "";
        };
        return handler;
    }

    @Override
    public XmlRpcHandler publishCalendar() {
        this.setChanged();
        notifyObservers("liss.publishCalendar called");
        
        XmlRpcHandler handler = (XmlRpcRequest pRequest) -> {
            StringBuilder sb = new StringBuilder();
            sb.append( pRequest.getMethodName() ).append("\n");
            sb.append( pRequest.getParameter(0) ).append("\n");
            sb.append( pRequest.getParameter(1) ).append("\n");
            for (Calendar calendar : (CalendarList) pRequest.getParameter(2)) {
                sb.append( calendar ).append("\n");
            }
            
            //System.out.println(sb);
            logger.log(Level.INFO, "liss.publishCalendar\n"+sb.toString() );
            return "";
        };
        return handler;
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
