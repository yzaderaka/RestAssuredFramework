package endpoints;

import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import payload.Employee;
import utilities.GlobalVariables;

import static io.restassured.RestAssured.given;
import static utilities.GlobalVariables.TOKEN;
import static utilities.GlobalVariables.empID;

public class EmployeeEndPoints {

    public static Response createEmployee(Employee payload) {
        return given()
                .header(GlobalVariables.HEADER_KEY_CONTENT_TYPE, GlobalVariables.HEADER_VALUE_CONTENT_TYPE)
                .header("Authorization", TOKEN)
                .body(payload)
                .when()
                .post(Routes.CREATE_EMPLOYEE);


    }

    public static Response readEmployee() {
        return given()
                .header(GlobalVariables.HEADER_KEY_CONTENT_TYPE, GlobalVariables.HEADER_VALUE_CONTENT_TYPE)
                .header(GlobalVariables.HEADER_KEY_AUTHORIZATION, TOKEN)
                .queryParam("employee_id", empID)
                .when()
                .get(Routes.GET_ONE_EMPLOYEE);

    }

    public static Response readAllEmployees() {
        return given()
                .header(GlobalVariables.HEADER_KEY_CONTENT_TYPE, GlobalVariables.HEADER_VALUE_CONTENT_TYPE)
                .header(GlobalVariables.HEADER_KEY_AUTHORIZATION, TOKEN)
                .when()
                .get(Routes.GET_ALL_EMPLOYEE);
    }

    public static Response getJobTitle(){
        return given()
                .header(GlobalVariables.HEADER_KEY_CONTENT_TYPE, GlobalVariables.HEADER_VALUE_CONTENT_TYPE)
                .header(GlobalVariables.HEADER_KEY_AUTHORIZATION, TOKEN)
                .when()
                .get(Routes.JOB_TITLE_EMPLOYEE);
    }

    public static Response getStatus(){
        return given()
                .header(GlobalVariables.HEADER_KEY_CONTENT_TYPE, GlobalVariables.HEADER_VALUE_CONTENT_TYPE)
                .header(GlobalVariables.HEADER_KEY_AUTHORIZATION, TOKEN)
                .when()
                .get(Routes.GET_EMPLOYEE_STATUS);
    }

    public static Response updateEmployee(Employee Payload) {
        return given()
                .header(GlobalVariables.HEADER_KEY_CONTENT_TYPE, GlobalVariables.HEADER_VALUE_CONTENT_TYPE)
                .header(GlobalVariables.HEADER_KEY_AUTHORIZATION, TOKEN)
                .body(Payload)
                .when()
                .put(Routes.UPDATE_EMPLOYEE);
    }



    public static Response deleteEmployee(String empID) {
        return given()
                .header(GlobalVariables.HEADER_KEY_CONTENT_TYPE, GlobalVariables.HEADER_VALUE_CONTENT_TYPE)
                .header(GlobalVariables.HEADER_KEY_AUTHORIZATION, TOKEN)
                .queryParam("employee_id", empID)
                .when()
                .delete(Routes.DELETE_EMPLOYEE);
    }
}
