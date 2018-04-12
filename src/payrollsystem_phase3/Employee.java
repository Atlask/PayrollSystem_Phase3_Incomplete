package payrollsystem_phase3;

import java.util.ArrayList;

/**
 * The Employee class is an abstract class that holds general data about a company's
 * employee. Classes representing more specific types of employees should inherit
 * from this class.
 *
 * @author Mayelin
 */
public abstract class Employee implements Comparable<Employee>
{
    // instance variables
    private int employeeID;
    private String firstName;
    private String lastName;
    private ArrayList<Paycheck> listOfPaychecks = new ArrayList<>();


    /**
     * This constructor sets the employee's id, name, and the list of
     * paychecks received.
     * @param id          The employee's identification number.
     * @param first       The employee's first name.
     * @param last        The employee's last name.
     * @param paychecks   The list of paychecks the employee has received so far.
     */
    public Employee(int id, String first, String last, ArrayList<Paycheck> paychecks)
    {
        employeeID = id;
        firstName = first;
        lastName = last;

        // before trying to iterate through the paychecks array list, make sure it isn't null
        if(paychecks != null)
        {
            // create a copy of each Paycheck element and add it to the listOfPaychecks instance variable
            for( Paycheck checkElement : paychecks )
                listOfPaychecks.add( new Paycheck(checkElement) );
        }
    }


    /**
     * This is a copy constructor. It initializes the fields of the object being
     * created to the same values as the fields in the object passed as an argument.
     * @param employeeObj The object being copied.
     */
    public Employee(Employee employeeObj)
    {
        if( employeeObj != null )
        {
            employeeID = employeeObj.employeeID;
            firstName = employeeObj.firstName;
            lastName = employeeObj.lastName;

            // before trying to iterate through the listOfPaychecks in employeeObject, make sure it isn't null
            if(employeeObj.listOfPaychecks != null)
            {
                // create a copy of each Paycheck element and add it to the listOfPaychecks instance variable
                for( Paycheck checkElement : employeeObj.listOfPaychecks )
                    listOfPaychecks.add( new Paycheck(checkElement) );
            }
        }
    }


    /**
     * The getEmployeeID method returns the employee's identification number.
     * @return The employee's id.
     */
    public int getEmployeeID()
    {
        return employeeID;
    }


    /**
     * The getFirstName method returns the employee's first name.
     * @return The employee's first name.
     */
    public String getFirstName()
    {
        return firstName;
    }


    /**
     * The getLastName method returns the employee's last name.
     * @return The employee's last name.
     */
    public String getLastName()
    {
        return lastName;
    }


    /**
     * The getListOfPaychecks method returns a list containing all the paychecks
     * the employee has received.
     * @return A reference to a copy of this employee's list of paychecks.
     */
    public ArrayList<Paycheck> getListOfPaychecks()
    {
        ArrayList<Paycheck> newList = new ArrayList<>();
        for( Paycheck checkElement : listOfPaychecks )
            newList.add(new Paycheck(checkElement));

        return newList;
    }


    /**
     * The setEmployeeID method sets the employee's identification number.
     * @param id The value to store in the employee id field.
     */
    public void setEmployeeID(int id)
    {
        employeeID = id;
    }


    /**
     * The setFirstName method sets the employee's first name.
     * @param first The value to store in the first name field.
     */
    public void setFirstName(String first)
    {
        firstName = first;
    }


    /**
     * The setLastName method sets the employee's last name.
     * @param last The value to store in the last name field.
     */
    public void setLastName(String last)
    {
        lastName = last;
    }


    /**
     * The setListOfPaychecks method sets the list of paychecks that the employee
     * has received.
     * @param paychecks The value as an ArrayList of Paycheck elements to store
     * in the list of paychecks field.
     */
    public void setListOfPaychecks(ArrayList<Paycheck> paychecks)
    {
        listOfPaychecks.clear();
        if( paychecks != null )
        {
            for( Paycheck checkElement : paychecks )
                listOfPaychecks.add( new Paycheck(checkElement) );
        }
    }


    /**
     * The toString method returns a string containing the state of an Employee object.
     * @return A string containing the employee's information: id, first name,
     * last name, and list of paychecks received.
     */
    @Override
    public String toString()
    {
        String output = String.format("\n%5s %-24s %s \n%5s %-24s %s \n%5s %-24s %s \n%5s %-24s ",
                "", "Employee ID:", employeeID,
                "", "First Name:", firstName,
                "", "Last Name:", lastName,
                "", "Paychecks Received:");

        if( listOfPaychecks == null || listOfPaychecks.isEmpty() )
            output += "No paychecks received.";
        else
        {
            PayrollUtility.sortArrayList(listOfPaychecks);
            for( Paycheck checkElement : listOfPaychecks)
                output += checkElement.toString();
        }

        return output + "\n";
    }


    /**
     * The equals method compares the Employee object calling this method with the
     * Employee object passed as an argument.
     * @param obj The Employee object to compare with.
     * @return True if both objects have the same value for the firstName and lastName
     * instance variables. False otherwise.
     */
    @Override
    public boolean equals(Object obj)
    {
        // check that the type of the parameter is Employee
        if( !(obj instanceof Employee) )
            return false;

        // we already know that obj is of type Employee, so it's safe to cast
        Employee employee = (Employee) obj;

        // return true or false depending on whether firstName, lastName, and dateOfBirth have the same value
        return  firstName.equals(employee.firstName) &&
                lastName.equals(employee.lastName);

    }


    /**
     * The compareTo method compares the Employee object calling this method with the
     * Employee object passed as an argument to see which one has a greater value.
     * @param emp The Employee object to compare with.
     * @return 0 if both objects have the same value for the firstName and lastName
     * instance variables.
     * A positive number if the calling object has a greater last name or both objects
     * have the same last name but the calling object has a greater first name.
     * A negative number if the calling object has a lesser last name or both objects
     * have the same last name but the calling object has a lesser first name.
     */
    @Override
    public int compareTo(Employee emp)
    {
        // compare the last names
        int result = this.lastName.compareTo(emp.lastName);

        if( result == 0 )
        {
            // if the last names are the same, compare the first names
            result = this.firstName.compareTo(emp.firstName);
        }

        return result;
    }


    /**
     * The addPaycheck method adds a paycheck to the list of paychecks that the employee has received.
     * @param paycheck The Paycheck object to be added to the list.
     */
    public void addPaycheck(Paycheck checkObj)
    {
        listOfPaychecks.add(new Paycheck(checkObj));
    }

}
