package APIInteration;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.google.gson.Gson;

import DAO.CourseDAO;
import DAO.RegistrationDAO;
import DAO.StudentDAO;
import Model.Course;
import Model.Student;

public class LambdaRequestHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent>  {

	@Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent input, Context context) {
        
        Map<String, String> queryParams = input.getQueryStringParameters();
        
        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();
        
        Map<String, String> headers=new HashMap();
        headers.put("Access-Control-Allow-Headers","Content-Type");
        headers.put("Access-Control-Allow-Origin","*");
        headers.put("Access-Control-Allow-Methods","GET");
        response.setHeaders(headers);
        
        String action=queryParams.get("action");
        Gson gson=new Gson();
        String result="\"error\"";
        
        if(action.equals("GetAllCourses")) {
        	ArrayList<Course> list=CourseDAO.SelectAll();
        	if(list!=null) result=gson.toJson(list);
        }
        else if(action.equals("GetRegisteredCourses")) {
        	Integer student_id=Integer.valueOf(queryParams.get("student_id"));
        	ArrayList<Course> list=CourseDAO.SelectByStudentId(student_id);
        	if(list!=null) result=gson.toJson(list);
        }
        else if(action.equals("GetStudent")) {
        	Integer student_id=Integer.valueOf(queryParams.get("student_id"));
        	Student st=StudentDAO.SelectByStudentId(student_id);
        	Course c=gson.fromJson("",Course.class);
        	if(st!=null) result=gson.toJson(st);
        }
        else if(action.equals("Add")) {
        	Integer course_id=Integer.valueOf(queryParams.get("course_id"));
        	Integer student_id=Integer.valueOf(queryParams.get("student_id"));
        	result="\""+RegistrationDAO.insert(course_id, student_id)+"\"";
        }
        else if(action.equals("Delete")) {
        	Integer course_id=Integer.valueOf(queryParams.get("course_id"));
        	Integer student_id=Integer.valueOf(queryParams.get("student_id"));
        	if(RegistrationDAO.delete(student_id,course_id)) result="\"success\"";
        }
        response.setBody("{\"result\":"+result+"}");
        response.setStatusCode(200);
        
        return response;
    }
}
