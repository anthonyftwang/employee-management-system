/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 489516
 */

import java.util.ArrayList;

public class MyHashTable {

    // ATTRIBUTES
    // buckets is an array of ArrayList.  Each item in an ArrayList is a EmployeeInfo
    // object holding a reference value pointing to a student.
    private ArrayList<EmployeeInfo>[] buckets;
    private int numInHash = 0;

    // CONSTRUCTOR
    public MyHashTable(int howManyBuckets) {
        // Construct the hash table (open hashing/closed addressing) as an array of howManyBuckets ArrayLists.

        // Instantiate an array to have an ArrayList as each element of the array.
        buckets = new ArrayList[howManyBuckets];

        // For each element in the array, instantiate its ArrayList.
        for (int i = 0; i < howManyBuckets; i++) {
            buckets[i] = new ArrayList<EmployeeInfo>();  // Instantiate the ArrayList for bucket i.
        }
    }

    // METHODS
    public int calcBucket(int employeeNumber) {
        return(employeeNumber % buckets.length);
    }

    public void addToTable(EmployeeInfo theEmployee) {
        // Add the student referenced by theStudent to the hash table.
        (buckets[calcBucket(theEmployee.empNum)]).add(theEmployee);
        numInHash ++;
    }

    public EmployeeInfo removeFromTable(int employeeNumber) {
        // Remove that student from the hash table and return the reference value for that student.
        int checkBucket = calcBucket(employeeNumber);           
        for (int i = 0; i < buckets[checkBucket].size(); i++) {
            if ((buckets[checkBucket].get(i)).empNum == employeeNumber) {
                EmployeeInfo removedStudent = buckets[checkBucket].get(i);
                buckets[checkBucket].remove(removedStudent);
                numInHash --;
                return removedStudent;
            }
        }
        return null;
    }

    public boolean isInTable(int employeeNumber) {
        // Return true if that student is in the hash table, false otherwise.
        int checkBucket = calcBucket(employeeNumber);
        for (int i = 0; i < buckets[checkBucket].size(); i++) {
            if ((buckets[checkBucket].get(i)).empNum == employeeNumber) {
                return true;
            }
        }
        return false;
    }

    public EmployeeInfo readFromTable(int employeeNumber) {
        int checkBucket = calcBucket(employeeNumber);
        for (int i = 0; i < buckets[checkBucket].size(); i++) {
            if ((buckets[checkBucket].get(i)).empNum == employeeNumber) {
                return buckets[checkBucket].get(i);
            }
        }
        return null;
    }

    
    public void displayTable() {
        // Iterates thru table; displays info in the following manner:
        // Each bucket printed on one line
        // Each line prints the ArrayList corresponding to the bucket
        // For each ArrayList, only the empNum attribute is actually printed (for clarity)
        for (int i = 0; i < buckets.length; i++) {
            for (int j = 0; j < (buckets[i].size()); j++) {
                // sketchy line vvvv
                System.out.print((buckets[i].get(j)).getEmpNum() + ", ");
            }
            System.out.println();
        }
    }
    
    
    public ArrayList<EmployeeInfo>[] getBuckets() {
        return buckets;
    }
    
    public int getNumInHash() {
        return numInHash;
    }
}
