/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 489516
 */
public class EmployeeInfo {
    protected int empNum;
    protected String firstName;
    protected String lastName;
    protected int gender;
    protected int workLocation;
    protected double deductionsRate;

    public EmployeeInfo(int empNum, String firstName, String lastName, int gender, int workLocation, double deductionsRate) {
        this.empNum = empNum;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.workLocation = workLocation;
        this.deductionsRate = deductionsRate;
    }

    public int getEmpNum() {
        return this.empNum;
    }
}
