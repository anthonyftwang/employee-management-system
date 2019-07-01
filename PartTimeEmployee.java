/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 489516
 */
public class PartTimeEmployee extends EmployeeInfo {
    protected double hourlyWage;
    protected double hoursPerWeek;
    protected double weeksPerYear;

    public PartTimeEmployee(int empNum, String firstName, String lastName, int gender, int workLocation, double deductionsRate, double hourlyWage, double hoursPerWeek, double weeksPerYear) {
        super(empNum, firstName, lastName, gender, workLocation, deductionsRate);
        this.hourlyWage = hourlyWage;
        this.hoursPerWeek = hoursPerWeek;
        this.weeksPerYear = weeksPerYear;
    }

    public double calcAnnualGrossIncome() {
        return(hourlyWage*hoursPerWeek*weeksPerYear);
    }

    public double calcAnnualNetIncome() {
        return((hourlyWage*hoursPerWeek*weeksPerYear)*(1-deductionsRate));
    }
}
