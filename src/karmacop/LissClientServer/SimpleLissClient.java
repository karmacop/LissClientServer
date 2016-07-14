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

import java.net.URL;
import java.util.*;
import karmacop.liss4j.*;
import org.apache.xmlrpc.*;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

/**
 * 
 * @author Glenn Tester
 */
public class SimpleLissClient {
    //XMLRPCClient server;
    XmlRpcClient server;
    XmlRpcClientConfigImpl config;
    
    public SimpleLissClient(URL url) {
        server = new XmlRpcClient();        
        //server = new XMLRPCClient();
        config = new XmlRpcClientConfigImpl();
        //XmlRpcCommonsTransportFactory xmlFactory = new XmlRpcCommonsTransportFactory(server);
        //server.setTransportFactory(xmlFactory);
	config.setServerURL(url);
        server.setConfig(config);
    }
    
    public Object generic(String method, List params) throws XmlRpcException{
        return server.execute(method, params);
    }
    
    public Hello hello() throws XmlRpcException{
        return (Hello) server.execute("liss.hello", new ArrayList());
        //return new Hello( (HashMap<String, Object>) server.execute("liss.hello", new ArrayList()));
    }
    
    public Object getRooms(List params) throws XmlRpcException{
        return server.execute("liss.getRooms", params);
    }
    
    public Object getTeachers(List params) throws XmlRpcException{
        return server.execute("liss.getTeachers", params);
    }
    
    public StudentList getStudents(Authentication auth, Date asAtDate) throws XmlRpcException{
        List params = new ArrayList();
        params.add(auth);
        params.add(asAtDate);
        return new StudentList( (Object[]) server.execute("liss.getStudents", params) );
    }
    
    public String publishStudents(Authentication auth, Date asAtDate, StudentList students) throws XmlRpcException{
        List params = new ArrayList();
        params.add(auth);
        params.add(asAtDate);
        params.add(students);
        Object response = null;
        try {
            //System.out.println("liss.publishStudents");
            //System.out.println(params);
            response = server.execute("liss.publishStudents", params);
        } catch (XmlRpcException ex) {
            //System.out.println(ex.code);
            //System.out.println(ex.getMessage());
            //System.out.println();
            if(ex.code == 0) {
                return null;
            } else {
                //System.out.println("code:"+ex.code);
                throw ex;
            }
        }
        return (String) response;
    }
    
    public Object getTimetable(List params) throws XmlRpcException{
        return server.execute("liss.getTimetable", params);
    }
    
    public void publishTimetable(List params) throws XmlRpcException{
        server.execute("liss.publishTimetable", params);
    }
    
    public String publishClasses(Authentication auth, int academicYear, CourseClassList courseClassList) throws XmlRpcException{
        List params = new ArrayList();
        params.add(auth);
        params.add(academicYear);
        params.add(courseClassList);
        Object response = null;
        try {
            //System.out.println("liss.publishClasses");
            //System.out.println(params);
            response = server.execute("liss.publishClasses", params);
        } catch (XmlRpcException ex) {
            //System.out.println(ex.code);
            //System.out.println(ex.getMessage());
            if(ex.code == 0) {
                return null;
            } else {
                throw ex;
            }
        }
        return (String) response;
    }
    
    public CourseClassList getClasses(Authentication auth) throws XmlRpcException{
        List params = new ArrayList();
        params.add(auth);
        return (CourseClassList) server.execute("liss.getClasses", params);
        //return new CourseClassList( (Object[]) server.execute("liss.getClasses", params) );
    }
    
    public Object getTimetableStructures(List params) throws XmlRpcException{
        return server.execute("liss.getTimetableStructures", params);
    }
    
    public Object getClassMembership(List params) throws XmlRpcException{
        return server.execute("liss.getClassMembership", params);
    }
    
    public String publishClassMemberships(Authentication auth, ClassMembershipList classMembershipList, Date asAtDate) throws XmlRpcException{
        List params = new ArrayList();
        params.add(auth);
        params.add(classMembershipList);
        params.add(asAtDate);
        Object response = null;
        try {
            //System.out.println("liss.publishClassMemberships");
            //System.out.println(params);
            response = server.execute("liss.publishClassMemberships", params);
        } catch (XmlRpcException ex) {
            //System.out.println(ex.code);
            //System.out.println(ex.getMessage());
            if(ex.code == 0) {
                return null;
            } else {
                //System.out.println("error:"+ex);
                throw ex;
            }
        }
        return (String) response;
    }
    
    public Object getStudentFamilies(List params) throws XmlRpcException{
        return server.execute("liss.getStudentFamilies", params);
    }
    
    public void publishDailyData(List params) throws XmlRpcException{
        server.execute("liss.publishDailyData", params);
    }
    
    public Object getBellTimes(List params) throws XmlRpcException{
        return server.execute("liss.getBellTimes", params);
    }
    
    public void publishBellTimes(List params) throws XmlRpcException{
        server.execute("liss.publishBellTimes", params);
    }
}
