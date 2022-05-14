package com.example.tuition;

import java.text.DecimalFormat;

/**
 * A class constructing the student structure which storing different attributes of a student
 *
 * @author Xinyu Meng, Chengyu Liang
 */
public class Student {

    private Profile profile;
    private double payment;
    private double tuitiondue;
    private Date date;

    /**
     * Constructor method to creating a student structure with the corresponding attributes.
     *
     * @param name   student`s name.
     * @param major  student`s major.
     * @param credit student`s credit.
     */
    public Student(String name, Major major, int credit) {
        this.profile = new Profile(name, major, credit);
        this.payment = 0;
        this.tuitiondue = 0;
        this.date = null;
    }


    /**
     * To calculate the tuition due for students.
     */
    public void tuitionDue() {
    }

    /**
     * To make the format of tuition as "#,##0.00".
     *
     * @param value the input value that we want to change the format.
     * @return the correct format of input value.
     */
    public String formating(double value) {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        String result = df.format(value);
        return result;
    }


    /**
     * To call the toString in subclasses depends on the different class types.
     *
     * @return The string format of student`s information.
     */
    @Override
    public String toString() {
        return this.profile.toString();
    }

    /**
     * Getter method for getting the profile in Student class.
     *
     * @return the profile information.
     */
    public Profile getProfile() {
        return this.profile;
    }

    /**
     * Setter method for set the tuition due.
     *
     * @param value the value that should be put in tuition due.
     */
    public void setTuitiondue(double value) {
        this.tuitiondue = value;
    }

    /**
     * Getter method for tuition due.
     *
     * @return the tuition due.
     */
    public double getTuitiondue() {
        return this.tuitiondue;
    }

    /**
     * Setter method for payment value.
     *
     * @param value The value that should be put in payment.
     */
    public void setPayment(double value) {
        this.payment += value;
    }

    /**
     * Setter method for setting payment to 0.
     */
    public void resetpayment() {
        this.payment = 0;
    }

    /**
     * Getter method for getting the payment amount.
     *
     * @return the student`s payment information.
     */
    public double getPayment() {
        return this.payment;
    }

    /**
     * Getter method for getting Date.
     *
     * @return the date in student`s information.
     */
    public Date getDate() {
        return this.date;
    }

    /**
     * Setter method for setting the given value to the date of student.
     *
     * @param date The date to be assigned to the student.
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Getter method for getting the credit in student.
     *
     * @return the credit in profile in student.
     */
    public int getcredit() {
        return this.profile.getCredit();
    }

    /**
     * Compare two students and find out if they are equal.
     *
     * @param obj the student to be compared with the other one.
     * @return true if the students are equal, false if not.
     */
    @Override
    public boolean equals(Object obj) {
        Student student = (Student) obj;
        Profile profile1 = this.getProfile();
        Profile profile2 = student.getProfile();
        if (profile1.equals(profile2)) {
            return true;
        }
        return false;
    }

    /**
     * Getter method for getting the aid in student.
     *
     * @return the aid that student received.
     */
    public double getAid() {
        return this.getAid();
    }

    /**
     * Setter method for setting the aid with given amount.
     *
     * @param amount the amount to be assigned to student`s aid.
     */
    public void setAid(double amount) {
        this.setAid(amount);
    }

    /**
     * Getter method for getting name.
     *
     * @return the name that stored in student.
     */
    public String getname() {
        return this.profile.getname();
    }

    /**
     * Setter method for set the credit with the given amount.
     *
     * @param credit the amount of credit to be assigned to student`s credit.
     */
    public void setCredit(int credit) {
        this.profile.setCredit(credit);
    }
}
