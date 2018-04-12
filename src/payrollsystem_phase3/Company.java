package payrollsystem_phase3;


/**
 * The Company class represents a company having multiple departments.
 *
 * @author Mayelin
 */
public class Company
{
    // instance variables
    private String companyName;
    private DepartmentLinkedList listOfDepartments = new DepartmentLinkedList();


    /**
     * This constructor sets the company's name and list of departments.
     * @param name        Company's name.
     * @param departments List of departments in the company.
     */
    public Company(String name, DepartmentLinkedList departments)
    {
        companyName = name;

        // before trying to copy the departments linked list, make sure it isn't null
        if(departments != null)
            listOfDepartments = departments.copy();

    }


    /**
     * The getCompanyName method returns the company's name.
     * @return The company's name.
     */
    public String getCompanyName()
    {
        return companyName;
    }


    /**
     * The getListOfDepartments method returns the company's list of departments.
     * @return The company's list of departments as a linked list of Department elements.
     */
    public DepartmentLinkedList getListOfDepartments()
    {
        if(listOfDepartments != null)
            return listOfDepartments.copy();
        else
            return null;
    }


    /**
     * The setCompanyName method sets the name for this company.
     * @param name The value to store in the name field for this company.
     */
    public void setCompanyName(String name)
    {
        companyName = name;
    }


    /**
     * The setListOfDepartments method stores a value in the listOfDepartments field.
     * @param departments the list of departments to store in the listOfDepartments field.
     */
    public void setListOfDepartments(DepartmentLinkedList departments)
    {
        if(departments != null)
            listOfDepartments = departments.copy();
    }


    /**
     * The toString method returns a string containing the company's data.
     * @return A String containing the Company's information: name and list of
     * departments.
     */
    @Override
    public String toString()
    {
        String output = String.format("%-30s %s", "Company Name:", companyName );

        if( listOfDepartments == null || listOfDepartments.isEmpty() )
            output += "There are no departments in the company.";
        else
            output += "\n" + listOfDepartments.toString();

        return output;
    }


    /**
     * The addDepartment method adds a department to the list of departments in the company.
     * @param dept The Department object to be added to the list.
     */
    public void addDepartment(Department dept)
    {
        listOfDepartments.add(new Department(dept));
    }


    /**
     * The addEmployeeToDepartment method iterates through the listOfDepartments Linked List looking
     * for a Department whose departmentID is equal to the int value passed as the first argument.
     * If it finds it, it adds the Employee object passed as the second argument.
     * @param deptID the departmentID to search for in the list.
     * @param empObject the Employee object to be added to the department.
     */
    public void addEmployeeToDepartment (int deptID, Employee empObject)
    {
        Department foundDept = listOfDepartments.find(deptID);

        if( foundDept != null )
        {
            if( empObject instanceof HourlyEmployee )
                foundDept.addEmployee( new HourlyEmployee((HourlyEmployee)empObject));
            else if( empObject instanceof SalariedEmployee )
                foundDept.addEmployee( new SalariedEmployee((SalariedEmployee)empObject));
        }

    }


    /**
     * The setDepartmentManager method iterates through the listOfDepartments ArrayList looking
     * for a Department whose departmentID is equal to the int value passed as the first argument.
     * If it finds it, it sets its manager to be the value passed as the second argument.
     * @param deptID the departmentID to search for in the list.
     * @param manObject the Manager object used to set the department's manager.
     */
    public void setDepartmentManager(int deptID, Manager manObject)
    {
        Department foundDept = listOfDepartments.find(deptID);

        if( foundDept != null )
            foundDept.setDepartmentManager(new Manager(manObject));
    }

}
