package com.example.tuition;

/**
 * A class constructing a roster that has size and an array of roster. The array can grow if needed.
 *
 * @author Xinyu Meng, Chengyu Liang
 */
public class Roster {

    private Student[] roster;
    private int size;

    final int negativecase = -1, firstcase = 1, maxaid = 10000, parttimecredit = 12;

    /**
     * The constructor method that create a roster with default size 0 and length 4;
     */
    public Roster() {
        this.roster = new Student[4];
        this.size = 0;
    }

    /**
     * A method that find the given student in the roster(student with same name and major).
     *
     * @param student An input student to be found.
     * @return The index of the student found in the roster. -1 if not found.
     */
    private int find(Student student) {
        for (int i = 0; i < this.size; i++) {
            if (student.equals(this.roster[i])) {
                return i;
            }
        }
        return negativecase;
    }

    /**
     * A method that increase the length of the array by 4;
     */
    private void grow() {
        Student[] temp = new Student[this.size + 4];
        for (int i = 0; i < this.size; i++) {
            temp[i] = this.roster[i];
        }
        this.roster = temp;
    }

    /**
     * A method that adds the given student to the array of roster.
     *
     * @param student The student to be added.
     * @return A boolean value of output. True if it`s successful, false if the student is not found.
     */
    public boolean add(Student student) {
        if (this.find(student) != negativecase) {
            return false;
        }
        if (this.size == this.roster.length) {
            grow();
        }
        this.roster[this.size] = student;
        this.size += firstcase;
        return true;
    }

    /**
     * A method that calculate the tuition due of each student. Calls the tuitionDue method of each student.
     */
    public void calculate() {
        for (int i = 0; i < this.size; i++) {
            this.roster[i].tuitionDue();
        }
    }

    /**
     * The method that set the payment amount and date of the student.
     * Deal with each situation where the input is not correct and return the output string.
     *
     * @param name   The name of the student who paid the tuition.
     * @param major  The major of the student who paid the tuition.
     * @param amount The amount of the payment.
     * @param date   The date of the payment.
     * @return the corresponding response when the payment is being executed.
     */
    public String pay(String name, Major major, double amount, Date date) {
        Student student = new Student(name, major, 0);
        int index = find(student);
        if (index < 0) {
            return "Student is not in the roster.";
        }
        double amountdue = this.roster[index].getTuitiondue();
        if (amount <= 0) {
            return "Invalid amount.";
        } else if (amount > amountdue) {
            return "Amount is greater than amount due.";
        }

        if (!date.isValid()) {
            return "Payment date invalid.";
        }
        this.roster[index].setPayment(amount);
        this.roster[index].setDate(date);
        this.roster[index].tuitionDue();
        return "Payment applied.";
    }

    /**
     * The get method for size of the roster.
     *
     * @return The size of this roster.
     */
    public int getsize() {
        return this.size;
    }

    /**
     * The remove method for removing a student from the roster.
     *
     * @param student The input student to be removed.
     * @return A boolean value true if the student has been removed, false if the student is not found.
     */
    public boolean remove(Student student) {
        int i = find(student);
        if (i < 0) {
            return false;
        } else if (i == this.size - firstcase) {
            this.roster[i] = null;
            this.size -= firstcase;
            return true;
        }
        for (; i < this.size - firstcase; i++) {
            this.roster[i] = this.roster[i + 1];
        }
        this.size -= firstcase;
        return true;
    }

    /**
     * A method that set the financial aid of a student.
     *
     * @param name   The name of the student that should be given financial aid.
     * @param major  The major of the student that should be given financial aid.
     * @param amount The amount of financial aid.
     * @return the corresponding response when the financial aid is being executed.
     */
    public String setf(String name, Major major, double amount) {

        Student student = new Student(name, major, 0);
        int index = find(student);
        if (index < 0) {
            return "Student is not in the roster.";
        }
        if (amount > maxaid || amount < 0) {
            return "Invalid amount.";
        } else if (this.roster[index].getcredit() < parttimecredit) {
            return "Parttime student doesn't qualify for the award.";
        } else if (!(this.roster[index] instanceof Resident)) {
            return "Not a resident student.";
        } else if (this.roster[index].getAid() > 0) {
            return "Awarded once already.";
        } else {
            this.roster[index].setAid(amount);
            return "Tuition updated.";
        }
    }

    /**
     * To get the string format information for the students in the roster.
     *
     * @param student The student that should be returned information for.
     * @return The string format information for the student.
     */
    private String gettoString(Student student) {
        if (student instanceof Resident) {
            return ((Resident) student).toString();
        } else if (student instanceof NonResident) {
            return ((NonResident) student).toString();
        } else if (student instanceof TriState) {
            return ((TriState) student).toString();
        } else {
            return ((International) student).toString();
        }
    }

    /**
     * The method that print the roster as it is.
     */
    public String print() {
        String result = "* list of students in the roster **\n";
        for (int i = 0; i < this.size; i++) {
            result += gettoString(this.roster[i]) + "\n";
        }
        result += "* end of roster **\n";
        return result;
    }

    /**
     * The method that print the roster sorted by name of alphabetical order.
     */
    public String printbyName() {
        String result = "* list of students in the roster ordered by name**\n";
        String[] temp = new String[this.size];
        for (int i = 0; i < this.size; i++) {
            temp[i] = this.roster[i].getname().toLowerCase();
            temp[i] = temp[i].replaceAll("\\s", "");
        }
        int pivot = 0, count = 0;
        while (count != this.size) {
            if (temp[pivot] == null) {
                pivot += 1;
            } else {
                for (int i = pivot; i < this.size; i++) {
                    if (temp[i] == null) {
                        continue;
                    } else if (temp[i].compareTo(temp[pivot]) < 0) {
                        pivot = i;
                    }
                }
                result += gettoString(this.roster[pivot]) + "\n";
                temp[pivot] = null;
                pivot = 0;
                count += firstcase;
            }
        }
        result += "* end of roster **\n";
        return result;
    }

    /**
     * The method that print the paied student in the roster sort by the payment date.
     */
    public String printbyPayment() {
        String result = "* list of students made payments ordered by payment date **\n";
        int newsize = 0;
        for (int i = 0; i < this.size; i++) {
            if (this.roster[i].getDate() != null) {
                newsize += firstcase;
            }
        }
        Date[] temp = new Date[this.size];
        for (int i = 0; i < this.size; i++) {
            if (this.roster[i].getDate() != null) {
                temp[i] = this.roster[i].getDate();
            } else {
                temp[i] = null;
            }
        }
        int pivot = 0, count = 0;
        while (count != newsize) {
            if (temp[pivot] == null) {
                pivot += firstcase;
            } else {
                for (int i = pivot; i < this.size; i++) {
                    if (temp[i] == null) {
                        continue;
                    } else if (temp[i].compareTo(temp[pivot]) < 0) {
                        pivot = i;
                    }
                }
                result += gettoString(this.roster[pivot]) + "\n";
                temp[pivot] = null;
                pivot = 0;
                count += firstcase;
            }
        }
        result += "* end of roster **\n";
        return result;
    }

    /**
     * The getter method for getting the roster.
     *
     * @return The roster with students` information.
     */
    public Student[] getlist() {
        return this.roster;
    }

    /**
     * To calculate the tuition-due for single student.
     *
     * @param student The student that is being calculated tuition-due for.
     * @return The tuition due for the single student.
     */
    public double calculatesingle(Student student) {
        int i = find(student);
        if (i == negativecase) {
            return negativecase;
        } else {
            this.roster[i].tuitionDue();
            return this.roster[i].getTuitiondue();
        }
    }
}
