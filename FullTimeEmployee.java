/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 489516
 */
public class FullTimeEmployee extends EmployeeInfo {
    protected double salary;
        
    public FullTimeEmployee(int empNum, String firstName, String lastName, int gender, int workLocation, double deductionsRate, double salary) {
        super(empNum, firstName, lastName, gender, workLocation, deductionsRate);
        this.salary = salary;
    }

    public double calcAnnualGrossIncome() {
        return(salary);
    }

    public double calcAnnualNetIncome() {
        return(salary*(1-deductionsRate));
    }
}
