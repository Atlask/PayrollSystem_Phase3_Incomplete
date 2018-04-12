package payrollsystem_phase3;

import java.util.ArrayList;

/**
 * The Department class represents a department within a company. Departments have a
 * list of employees and a manager.
 *
 * @author Mayelin
 */
public class Department implements Comparable<Department>
{
    // instance variables
    private int departmentID;
    private String departmentName;
    private Manager departmentManager;
    private ArrayList<Employee> listOfEmployees = new ArrayList<>();


    /**
     * This constructor sets the department's id and name, as well as the list of
     * employees and manager.
     * @param id          Department's id.
     * @param name        Department's name.
     * @param manager     Department's manager.
     * @param employees   Department's list of employees.
     */
    public Department(int id, String name, Manager manager, ArrayList<Employee> employees)
    {
        departmentID = id;
        departmentName = name;
        departmentManager = new Manager(manager);

        // before trying to iterate through the employees array list, make sure it isn't null
        if(employees != null)
        {
            // create a copy of each Employee element and add it to the listOfEmployees instance variable
            for( Employee employeeElement : employees )
            {
                if( employeeElement instanceof HourlyEmployee )
                    listOfEmployees.add( new HourlyEmployee( (HourlyEmployee) employeeElement ) );
                else if( employeeElement instanceof SalariedEmployee )
                    listOfEmployees.add( new SalariedEmployee( (SalariedEmployee) employeeElement ) );
            }
        }
    }


    /**
     * This is a copy constructor. It initializes the fields of the object being
     * created to the same values as the fields in the object passed as an argument.
     * @param deptObj The object being copied.
     */
    public Department(Department deptObj)
    {
        if( deptObj != null )
        {
            departmentID = deptObj.departmentID;
            departmentName = deptObj.departmentName;
            departmentManager = new Manager(deptObj.departmentManager);


            // create a copy of each Employee element and add it to the listOfEmployees instance variable
            for( Employee employeeElement : deptObj.listOfEmployees )
            {
                if( employeeElement instanceof HourlyEmployee )
                    listOfEmployees.add( new HourlyEmployee( (HourlyEmployee) employeeElement ) );
                else if( employeeElement instanceof SalariedEmployee )
                    listOfEmployees.add( new SalariedEmployee( (SalariedEmployee) employeeElement ) );
            }
        }
    }


    /**
     * The getDepartmentID method returns the department's id number.
     * @return The department's id.
     */
    public int getDepartmentID()
    {
        return departmentID;
    }


    /**
     * The getDepartmentName method returns the department's name.
     * @return The department's name.
     */
    public String getDepartmentName()
    {
        return departmentName;
    }


    /**
     * The getDepartmentManager method returns the department's manager.
     * @return The department's manager.
     */
    public Manager getDepartmentManager()
    {
        return new Manager(departmentManager);
    }


    /**
     * The getListOfEmployees method returns the department's list of employees.
     * @return The department's list of employees as an ArrayList of Employee elements.
     */
    public ArrayList<Employee> getListOfEmployees()
    {
        ArrayList<Employee> newList = new ArrayList<>();

        // create a copy of each Employee element and add it to the newList local variable
        for( Employee employeeElement : listOfEmployees )
        {
            if( employeeElement instanceof HourlyEmployee )
                newList.add( new HourlyEmployee( (HourlyEmployee) employeeElement ) );
            else if( employeeElement instanceof SalariedEmployee )
                newList.add( new SalariedEmployee( (SalariedEmployee) employeeElement ) );
        }

        return newList;
    }


    /**
     * The setDepartmentID method sets the id for this department.
     * @param id The value to store in the id field for this department.
     */
    public void setDepartmentID(int id)
    {
        departmentID = id;
    }


    /**
     * The setDepartmentName method sets the name for this department.
     * @param name The value to store in the name field for this department.
     */
    public void setDepartmentName(String name)
    {
        departmentName = name;
    }


    /**
     * The setDepartmentManager method sets the manager for this department.
     * @param deptManager The value to store in the manager field for this department.
     */
    public void setDepartmentManager(Manager deptManager)
    {
        departmentManager = new Manager(deptManager);
    }


    /**
     * The setListOfEmployees method sets the list of employees for this department.
     * @param employees The value, as an ArrayList of Employee elements, to store
     * in the list of employees field for this department.
     */
    public void setListOfEmployees(ArrayList<Employee> employees)
    {
        listOfEmployees.clear();

        // before trying to iterate through the employees array list, make sure it isn't null
        if(employees != null)
        {
            // create a copy of each Employee element and add it to the listOfEmployees instance variable
            for( Employee employeeElement : employees )
            {
                if( employeeElement instanceof HourlyEmployee )
                    listOfEmployees.add( new HourlyEmployee( (HourlyEmployee) employeeElement ) );
                else if( employeeElement instanceof SalariedEmployee )
                    listOfEmployees.add( new SalariedEmployee( (SalariedEmployee) employeeElement ) );
            }
        }
    }


    /**
     * The toString method returns a string containing the department's data.
     * @return A String containing the Department's information: id, name,
     * manager, and list of employees.
     */
    @Override
    public String toString()
    {
        String output = String.format(  "\n%-30s %s \n%-30s %s \n%-30s %s \n%-30s %s",
                "Department ID:", departmentID,
                "Department Name:", departmentName,
                "Department Manager:", departmentManager,
                "List of Employees:", "");

        if( listOfEmployees == null || listOfEmployees.isEmpty() )
            output += "The department has no employees.\n";
        else
        {
            PayrollUtility.sortArrayList(listOfEmployees);
            for( Employee emp : listOfEmployees )
                output += emp + "\n";
        }

        output += "------------------------------------------------------------------\n";
        return output;
    }


    /**
     * The equals method compares the Department object calling this method with the
     * Department object passed as an argument.
     * @param obj The Department object to compare with.
     * @return True if both objects have the same value for the departmentName instance variable.
     * False otherwise.
     */
    @Override
    public boolean equals(Object obj)
    {
        // check that the type of the parameter is Department
        if( !(obj instanceof Department) )
            return false;

        // we already know that obj is of type Department, so it's safe to cast
        Department dept = (Department) obj;

        // return true or false depending on whether departmentName have the same value
        return dept.departmentName.equals(this.departmentName);
    }


    /**
     * The compareTo method compares the Department object calling this method with the
     * Department object passed as an argument to see which one has a greater value.
     * @param dept The Department object to compare with.
     * @return 0 if both objects have the same value for the departmentName instance variable.
     * A positive number if the calling object has a greater value for the departmentName instance variable.
     * A negative number if the calling object has a lesser value for the departmentName instance variable.
     */
    @Override
    public int compareTo(Department dept)
    {
        return this.departmentName.compareTo(dept.departmentName);
    }


    /**
     * The addEmployee method adds an employee to the list of employees for this department.
     * @param employee The Employee object to be added to the list.
     */
    public void addEmployee(Employee employee)
    {
        if(employee != null)
        {
            // create a copy of the employee parameter and add it to the listOfEmployees instance variable
            if( employee instanceof HourlyEmployee )
                listOfEmployees.add( new HourlyEmployee( (HourlyEmployee) employee ) );
            else if( employee instanceof SalariedEmployee )
                listOfEmployees.add( new SalariedEmployee( (SalariedEmployee) employee ) );

        }
    }

}
