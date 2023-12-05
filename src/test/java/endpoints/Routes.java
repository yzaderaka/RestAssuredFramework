package endpoints;

public class Routes {
    public static String BASE_URL = "http://hrm.syntaxtechs.net/syntaxapi/api";

    public static String GENERATE_TOKEN = BASE_URL + "/generateToken.php";
    public static final String CREATE_EMPLOYEE = BASE_URL+"/createEmployee.php";
    public static String CREATE_ADMIN_URL = BASE_URL + "/createUser.php";
    public static String GET_ONE_EMPLOYEE = BASE_URL + "/getOneEmployee.php";
    public static String UPDATE_EMPLOYEE = BASE_URL + "/updateEmployee.php";
    public static String DELETE_EMPLOYEE = BASE_URL + "/deleteEmployee.php";
    public static final String GET_ALL_EMPLOYEE = BASE_URL +"/getAllEmployees.php";
    public static final String PARTIALLY_UPDATE_EMPLOYEE= BASE_URL +"/updatePartialEmplyeesDetails.php";
    public static final String GET_EMPLOYEE_STATUS = BASE_URL +"/employeementStatus.php";
    public static final String JOB_TITLE_EMPLOYEE = BASE_URL +"/jobTitle.php";

}
