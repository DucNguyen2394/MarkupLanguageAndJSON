package jsonDemo;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public class ReadJsonDemo {
    public static void main(String[] args) throws Exception{
        // phân tích cấu trúc tài liệu json
        JSONParser jsonParser = new JSONParser();

        FileReader fileReader = new FileReader("src/jsonDemo/employee.json");
        Object object = jsonParser.parse(fileReader);

        JSONArray employeeList = (JSONArray) object;

        System.out.println(employeeList);

        employeeList.forEach(emp ->parseJSON((JSONObject) emp));
    }

    public static void parseJSON(JSONObject jsonObject){
        JSONObject employeeObject = (JSONObject) jsonObject.get("Fpt-aptech");

        String firstname = (String) employeeObject.get("firstname");
        System.out.println(firstname);
        String lastname = (String) employeeObject.get("lastname");
        System.out.println(lastname);
        String address = (String) employeeObject.get("address");
        System.out.println(address);
        String email = (String) employeeObject.get("email");
        System.out.println(email);


    }
}
