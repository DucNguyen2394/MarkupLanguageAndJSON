package jsonDemo;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;

public class WriteJsonDemo {
    public static void main(String[] args) throws Exception {
        JSONObject employee = new JSONObject();
        employee.put("firstname","Nguyen");
        employee.put("lastname","Anh Duc");
        employee.put("address","Ha Noi");
        employee.put("email","ducnguyen@gmail.com");

        JSONObject employeeObject = new JSONObject();
        employeeObject.put("Fpt-aptech",employee);

        JSONObject employee2 = new JSONObject();
        employee2.put("firstname","Hoang");
        employee2.put("lastname","Van Anh");
        employee2.put("address","Ha Dong");
        employee2.put("email","hoanganh@gmail.com");

        JSONObject employeeObject2 = new JSONObject();
        employeeObject2.put("Fpt-aptech",employee);

        JSONArray employeeList = new JSONArray();
        employeeList.add(employeeObject);
        employeeList.add(employeeObject2);

        FileWriter fileWriter = new FileWriter("src/jsonDemo/employee.json");
        fileWriter.write(employeeList.toJSONString());
        fileWriter.flush();
    }
}
