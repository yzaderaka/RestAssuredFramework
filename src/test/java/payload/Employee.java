package payload;

public class Employee {
    String employee_id;
    String emp_firstname;
    String emp_lastname;
    String emp_middle_name;
    String emp_gender; //Enter M for male or F for female
    String emp_birthday; //yyyy-mm-dd
    String emp_status;
    String emp_job_title;

    public String getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(String employee_id) {
        this.employee_id = employee_id;
    }

    public String getEmp_firstname() {
        return emp_firstname;
    }

    public void setEmp_firstname(String emp_firstname) {
        this.emp_firstname = emp_firstname;
    }

    public String getEmp_lastname() {
        return emp_lastname;
    }

    public void setEmp_lastname(String emp_lastname) {
        this.emp_lastname = emp_lastname;
    }

    public String getEmp_middle_name() {
        return emp_middle_name;
    }

    public void setEmp_middle_name(String emp_middle_name) {
        this.emp_middle_name = emp_middle_name;
    }

    public String getEmp_gender() {
        return emp_gender;
    }

    public void setEmp_gender(String emp_gender) {
        this.emp_gender = emp_gender;
    }

    public String getEmp_birthday() {
        return emp_birthday;
    }

    public void setEmp_birthday(String emp_birthday) {
        this.emp_birthday = emp_birthday;
    }

    public String getEmp_status() {
        return emp_status;
    }

    public void setEmp_status(String emp_status) {
        this.emp_status = emp_status;
    }

    public String getEmp_job_title() {
        return emp_job_title;
    }

    public void setEmp_job_title(String emp_job_title) {
        this.emp_job_title = emp_job_title;
    }
}
