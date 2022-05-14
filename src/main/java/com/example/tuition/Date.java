package com.example.tuition;

import java.util.Calendar;

/**
 * A class constructing the date structure which storing the different attributes of date.
 *
 * @author Xinyu Meng, Chengyu Liang
 */
public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;

    final int JANUARY = 1, FEBRUARY = 2, MARCH = 3, MAY = 5, JULY = 7,
            AUGUST = 8, OCTOBER = 10, DECEMBER = 12, QUADRENNIAL = 4,
            CENTENNIAL = 100, QUATERCENTENNIAL = 400,
            BIGMONTH = 31, SMALLMONTH = 30, ORDINARYFEB = 28, LEAPFEB = 29,
            firstcase = 1, zerocase = 0, negativecase = -1;

    /**
     * Constructor for creating a Date object.
     *
     * @param year  The record of year.
     * @param month The record of month.
     * @param day   The record of day.
     */
    public Date(int year, int month, int day) {

        this.month = month;
        this.day = day;
        this.year = year;
    }

    /**
     * A method checking if the given date is a valid date.
     *
     * @return true if the given date is valid false if it is not.
     */
    public boolean isValid() {
        Calendar current = Calendar.getInstance();
        if (this.year == current.get(Calendar.YEAR)) {
            if (this.month == (current.get(Calendar.MONTH) + firstcase)) {
                if (this.month == FEBRUARY) {
                    if (this.checkfebdays() == true && this.day <= LEAPFEB && this.day > zerocase && this.day <= current.get(Calendar.DATE)) {
                        return true;
                    }
                } else if (this.month > zerocase && this.month <= DECEMBER) {
                    if (this.checkthirtyonedaysmonth()) {
                        if (this.day <= BIGMONTH && this.day > zerocase && this.day <= current.get(Calendar.DATE)) {
                            return true;
                        }
                    } else {
                        if (this.day <= SMALLMONTH && this.day > zerocase && this.day <= current.get(Calendar.DATE)) {
                            return true;
                        }
                    }
                }
            } else if (this.month < (current.get(Calendar.MONTH) + firstcase)) {
                if (this.month == FEBRUARY) {
                    if (this.checkfebdays() == true && this.day <= LEAPFEB && this.day > zerocase) {
                        return true;
                    } else if (this.checkfebdays() == false && this.day <= ORDINARYFEB && this.day > zerocase) {
                        return true;
                    }
                } else if (this.month > zerocase) {
                    if (this.checkthirtyonedaysmonth()) {
                        if (this.day <= BIGMONTH && this.day > zerocase) {
                            return true;
                        }
                    } else {
                        if (this.day <= SMALLMONTH && this.day > zerocase) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * A method used in isValid() that checks if a month has 31 days.
     *
     * @return true if the month has 31 days, false if the month has 30 days.
     */
    public boolean checkthirtyonedaysmonth() {
        if (this.month == JANUARY || this.month == MARCH || this.month == MAY || this.month == JULY || this.month == AUGUST || this.month == OCTOBER || this.month == DECEMBER) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * A method used in isValid() that checks if a year is a leap year.
     *
     * @return true if it is a leap year, false if it is not a leap year.
     */
    public boolean checkfebdays() {

        if (this.year % QUADRENNIAL == zerocase) {
            if (this.year % CENTENNIAL == zerocase) {
                if (this.year % QUATERCENTENNIAL == zerocase) {

                    return true;
                } else {

                    return false;
                }
            } else {
                return true;

            }
        } else {
            return false;
        }
    }

    /**
     * Compare the two dates and show the result.
     *
     * @param date A date object to be compared with the other one.
     * @return 1 or -1 depends on the different dates.
     */
    @Override
    public int compareTo(Date date) {
        if (this.year > date.year) {
            return 1;
        } else if (this.year < date.year) {
            return -1;
        } else {
            if (this.month > date.month) {
                return 1;
            } else if (this.month < date.month) {
                return -1;
            } else {
                if (this.day > date.day) {
                    return firstcase;
                } else if (this.day < date.day) {
                    return negativecase;
                } else {
                    return zerocase;
                }
            }
        }
    }

    /**
     * Get the string format of a Date object.
     *
     * @return the string format of a Date object.
     */
    @Override
    public String toString() {
        return this.month + "/" + this.day + "/" + this.year;
    }

}


