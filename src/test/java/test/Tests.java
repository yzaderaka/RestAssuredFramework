package test;


import com.github.javafaker.Faker;
import endpoints.EmployeeEndPoints;
import endpoints.Routes;
import endpoints.UserEndPoints;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import payload.Employee;
import payload.User;
import utilities.GlobalVariables;

import static io.restassured.RestAssured.*;
import static utilities.GlobalVariables.TOKEN;
import static utilities.GlobalVariables.empID;

public class Tests {

    Faker faker;
    User userPayload;
    Employee employeePayload;
    public Logger logger;

    @BeforeClass
    public void setup() {
        faker = new Faker();
        userPayload = new User();

        userPayload.setName(faker.name().username());
        userPayload.setEmail(faker.internet().safeEmailAddress());
        userPayload.setPassword(faker.internet().password());
        employeePayload = new Employee();
        employeePayload.setEmp_firstname(faker.name().firstName());
        employeePayload.setEmp_lastname(faker.name().lastName());
        employeePayload.setEmp_middle_name(faker.name().nameWithMiddle());
        employeePayload.setEmp_gender("M");
        employeePayload.setEmp_birthday("1984-12-23");
        employeePayload.setEmp_job_title(faker.job().position());
        employeePayload.setEmp_status(faker.job().title());

        logger= LogManager.getLogger(this.getClass());
    }

    @Test(priority = 1)
    public void createUser() {
        logger.info(" Creating User ");
        Response response = UserEndPoints.createUser(userPayload);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 201);
        logger.info(" User is created ");
    }

    @Test(priority = 2)
    public void getToken() {
        logger.info(" Creating token ");
        Response response = given()
                .header(GlobalVariables.HEADER_KEY_CONTENT_TYPE, GlobalVariables.HEADER_VALUE_CONTENT_TYPE)
                .body(this.userPayload)
                .when()
                .post(Routes.GENERATE_TOKEN);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
        TOKEN = response.jsonPath().getString("token");
        logger.info(" Token is created ");
    }

    @Test(priority = 3)
    public void createEmployee() {
        logger.info(" Creating Employee ");
        Response response = EmployeeEndPoints.createEmployee(employeePayload);
        empID = response.jsonPath().getString("Employee.employee_id");
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 201);
        logger.info(" Employee created ");
    }

    @Test(priority = 4)
    public void getEmployeeByID() {
        logger.info(" Reading Employee by ID ");
        Response response = EmployeeEndPoints.readEmployee();
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
        logger.info(" Employee info is displayed ");
    }

    @Test(priority = 5)
    public void getAllEmployees() {
        logger.info(" Reading all Employees ");
        Response response = EmployeeEndPoints.readAllEmployees();
        Assert.assertEquals(response.getStatusCode(), 200);
        logger.info(" All Employees is displayed ");
    }

    @Test(priority = 6)
    public void getEmployeeStatus() {
        logger.info(" Reading Employees Status ");
        Response response = EmployeeEndPoints.getStatus();
        Assert.assertEquals(response.getStatusCode(), 200);
        logger.info(" Status is displayed ");
    }

    @Test(priority = 7)
    public void getEmployeeJobTitle() {
        logger.info(" Reading Employees jobTitle ");
        Response response = EmployeeEndPoints.getJobTitle();
        Assert.assertEquals(response.getStatusCode(), 200);
        response.then().log().status().statusCode(200);
        String title=response.jsonPath().get("Jobs[1].job").toString();
        Assert.assertEquals(title,"Cloud Consultant");
        logger.info(" JobTitle is displayed ");
    }

    @Test(priority = 8)
    public void updateEmployee() {
        logger.info(" Updating Employee by ID ");
        employeePayload.setEmployee_id(empID);
        employeePayload.setEmp_firstname(faker.name().firstName());
        employeePayload.setEmp_lastname(faker.name().lastName());
        employeePayload.setEmp_middle_name(faker.name().nameWithMiddle());
        employeePayload.setEmp_gender("F");
        employeePayload.setEmp_birthday("1990-10-10");
        employeePayload.setEmp_job_title(faker.job().position());
        employeePayload.setEmp_status(faker.job().title());

        Response response = EmployeeEndPoints.updateEmployee(this.employeePayload);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
        logger.info(" Employee updated ");
    }

    @Test(priority = 9)
    public void deleteEmployee() {
        logger.info(" Deleting Employee by ID ");
        Response response = EmployeeEndPoints.deleteEmployee(empID);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
        logger.info(" Employee deleted ");
    }

}
