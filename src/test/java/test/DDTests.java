package test;

import com.github.javafaker.Faker;
import endpoints.EmployeeEndPoints;
import endpoints.Routes;
import endpoints.UserEndPoints;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import payload.Employee;
import payload.User;
import utilities.DataProviders;
import utilities.GlobalVariables;

import static io.restassured.RestAssured.*;
import static utilities.GlobalVariables.TOKEN;
import static utilities.GlobalVariables.empID;

public class DDTests {
    Faker faker;
    User userPayload;
    Employee employeePayload;

    @BeforeClass
    public void setupData() {
        faker=new Faker();
        userPayload = new User();
        userPayload.setName(faker.name().username());
        userPayload.setEmail(faker.internet().safeEmailAddress());
        userPayload.setPassword(faker.internet().password());

    }

    @Test(priority = 1)
    public void createUser() {
        Response response = UserEndPoints.createUser(userPayload);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 201);

    }

    @Test(priority = 2)
    public void getToken() {
        Response response = given()
                .header(GlobalVariables.HEADER_KEY_CONTENT_TYPE, GlobalVariables.HEADER_VALUE_CONTENT_TYPE)
                .body(this.userPayload)
                .when()
                .post(Routes.GENERATE_TOKEN);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
        TOKEN = response.jsonPath().getString("token");
        System.out.println(TOKEN);
    }

    @Test(priority = 3, dataProvider = "Data", dataProviderClass = DataProviders.class)
    public void createEmployeeDDT(String fName, String lName, String mName, String gender, String birthday, String status, String jobTitle) {
        Employee employeePayload = new Employee();
        employeePayload.setEmp_firstname(fName);
        employeePayload.setEmp_lastname(lName);
        employeePayload.setEmp_middle_name(mName);
        employeePayload.setEmp_gender(gender);
        employeePayload.setEmp_birthday(birthday);
        employeePayload.setEmp_status(status);
        employeePayload.setEmp_job_title(jobTitle);

        Response response = EmployeeEndPoints.createEmployee(employeePayload);
        empID = response.jsonPath().getString("Employee.employee_id");
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 201);
    }

    @Test(priority = 4)
    public void getEmployeeByID() {
        Response response = EmployeeEndPoints.readEmployee();
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 5)
    public void getAllEmployees() {
        Response response = EmployeeEndPoints.readAllEmployees();
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 6)
    public void getEmployeeStatus() {
        Response response = EmployeeEndPoints.getStatus();
        Assert.assertEquals(response.getStatusCode(), 200);
        response.then().log().all();
    }

    @Test(priority = 7)
    public void getEmployeeJobTitle() {
        Response response = EmployeeEndPoints.getJobTitle();
        Assert.assertEquals(response.getStatusCode(), 200);
        response.then().log().status().statusCode(200);
        String title=response.jsonPath().get("Jobs[1].job").toString();
        Assert.assertEquals(title,"Cloud Consultant");
    }

    @Test(priority = 8)
    public void updateEmployee() {
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

    }

    @Test(priority = 9)
    public void deleteEmployee() {
        Response response = EmployeeEndPoints.deleteEmployee(empID);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
    }

}
