package payrollsystem_phase3;

import java.io.*;
import java.util.*;
import javax.swing.*;

public class PayrollSystem_Phase3
{

    private static Company company;
    private static JFileChooser fileChooser = new JFileChooser(".");

    public static void main(String[] args)
    {
        // initialize the company object
        company = new Company("Our Company", null);


        // Ask the user what they want to do.
        String options = "\t [1] to load a new data file\n"
                + "\t [2] to add a new department\n"
                + "\t [3] to add a new hourly employee\n"
                + "\t [4] to add a new salaried employee\n"
                + "\t [5] to add a new manager\n"
                + "\t [6] to display company information\n"
                + "\t [q] to quit the application";

        String menuMessage = "Please enter one of the following options:\n" + options;
        String userInput = JOptionPane.showInputDialog(null, menuMessage, "Payroll System", JOptionPane.QUESTION_MESSAGE);

        while (userInput != null && !userInput.equalsIgnoreCase("q"))
        {
            switch (userInput)
            {
                case "1":
                    importDataFile();
                    break;

                case "2":
                    promptUserForData("D");
                    break;

                case "3":
                    promptUserForData("H");
                    break;

                case "4":
                    promptUserForData("S");
                    break;

                case "5":
                    promptUserForData("M");
                    break;

                case "6":
                    System.out.println("******************************************************************");
                    System.out.println("********************** COMPANY INFO ******************************");
                    System.out.println("******************************************************************");
                    System.out.println(company);
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Invalid entry. Please try again.",
                            "Invalid entry", JOptionPane.INFORMATION_MESSAGE);
                    break;
            }
            userInput = JOptionPane.showInputDialog(menuMessage);
        }

        JOptionPane.showMessageDialog(null, "Quitting application.");


        System.exit(0);
    }


    private static void importDataFile()
    {
        System.out.println("Loading new data file");

        // Prompt the user to select an input data file
        File selectedFile;

        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
        {
            selectedFile = fileChooser.getSelectedFile();

            // try with resources. The inputFile Scanner object will be closed automatically.
            try (Scanner inputFile = new Scanner(selectedFile))
            {
                // read all lines of data from the file and process them
                String line;
                int lineNumber = 1;
                while (inputFile.hasNext())
                {
                    line = inputFile.nextLine();
                    try
                    {
                        processLineOfData(line);
                    }
                    catch (Exception ex)
                    {
                        String message  = "The following error occurred while processing line number "
                                + lineNumber + ": " + ex.toString()
                                + "\nLine of data skipped: " + line;

                        System.out.println(message);
                    }
                    lineNumber++;
                }
            }
            catch (FileNotFoundException e)
            {
                System.out.println(e.toString());
            }

            System.out.println("Done loading data.");
        }
    }


    private static void promptUserForData(String dataType)
    {
        String dialogMessage, dialogTitle;

        switch (dataType)
        {
            case "D":

                dialogMessage   = "Enter department information in this format: dept id,dept name";
                dialogTitle     = "Department Information";
                break;

            case "H":

                dialogMessage   = "Enter hourly employee information in this format: employee id,first name,\n"
                        + "last name,hourly rate,pay period hours,department id,list of paychecks";
                dialogTitle     = "Hourly Employee Information";
                break;

            case "S":

                dialogMessage   = "Enter salaried employee information in this format: employee id,first name,\n"
                        + "last name,salary,department id,list of paychecks";
                dialogTitle     = "Salaried Employee Information";
                break;

            case "M":

                dialogMessage   = "Enter manager information in this format: employee id,first name,last name,\n"
                        + "salary,bonus,department id,list of paychecks";
                dialogTitle     = "Manager Information";
                break;

            default:
                return;
        }

        String userInput = JOptionPane.showInputDialog(null, dialogMessage, dialogTitle, JOptionPane.INFORMATION_MESSAGE);
        try
        {
            processLineOfData(dataType + "," + userInput);
        }
        catch (Exception ex)
        {
            String errorMessage = "The following error occurred while processing the ";
            errorMessage += dialogTitle.toLowerCase() + "you entered: ";

            System.out.println(errorMessage + ex.getMessage());
        }
    }


    private static void processLineOfData(String line) throws Exception
    {



    }


    /**
     * The parseEmployeePaychecks method parses the String passed as an argument and returns
     * an ArrayList of Paycheck objects.
     * @param empID ID of employee to whom the paychecks will be added.
     * @param paycheckData A String value that represents the list of paychecks an employee has
     * received separated by the # sign; paycheck fields are separated by the : character.
     * The following is the format for each paycheck:
     * periodBeginDate:periodEndDate:grossAmount:taxAmount:bonusAmount
     * @return An equivalent ArrayList of Paycheck objects.
     */
    public static ArrayList<Paycheck> parseEmployeePaychecks(int empID, String paycheckData)
    {

        String str = "...";
        List <String> PaycheckElements= Arrays.asList(str.split(","));;
        Scanner read = new Scanner(paycheckData);

        String s="";
        while (true) {
            s=read.nextLine();
            if (s.equals("")) {
                break;
            }else{
                read.add(s);
            }
        }

// System.out.println(PaycheckElements);
        for(int i=0; i<PaycheckElements.size(); i++){
            int gross = Integer.parseInt(PaycheckElements.get(2));
            int bonus = Integer.parseInt(PaycheckElements.get(4));
            int tax = Integer.parseInt(PaycheckElements.get(3));
            int val = (gross - tax + bonus);

        }
        return;
    }

}
