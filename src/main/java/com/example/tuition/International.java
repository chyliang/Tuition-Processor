package com.example.tuition;

/**
 * A subclass of student constructing the International student structure which storing
 * the different attributes of an international student.
 *
 * @author Xinyu Meng, Chengyu Liang
 */
public class International extends NonResident {
    final int tuition = 29737, universityfee = 3268, additionalfee = 2650, extraline = 16,
            parttimeline = 12, mincredit = 3, maxcredit = 24, parttime = 966;

    private boolean ifstudyabroad;

    /**
     * Constructor method to creating an international student structure with the corresponding attributes.
     *
     * @param name          international student`s name.
     * @param major         international student`s major.
     * @param credit        international student`s credit.
     * @param ifstudyabroad international student`s study-abroad status.
     */
    public International(String name, Major major, int credit, boolean ifstudyabroad) {
        super(name, major, credit);
        this.ifstudyabroad = ifstudyabroad;
    }

    /**
     * To calculate the tuition due for the international students with different credits situations.
     */
    @Override
    public void tuitionDue() {
        Profile profile = super.getProfile();
        int credit = profile.getCredit();
        if (this.ifstudyabroad == false) {
            if (credit >= parttimeline && credit <= extraline) {
                double value = (tuition + universityfee + additionalfee) - super.getPayment();
                super.setTuitiondue(value);
            } else if (credit > extraline && credit <= maxcredit) {
                double value = (tuition + universityfee + additionalfee) -
                        super.getPayment() + parttime * (credit - extraline);
                super.setTuitiondue(value);
            }
        } else {
            if (credit <= parttimeline && credit >= mincredit) {
                double value = universityfee + additionalfee - super.getPayment();
                super.setTuitiondue(value);
            }
        }
    }

    /**
     * Get the string format of an international student object.
     *
     * @return the string format of the international student object.
     */
    @Override
    public String toString() {
        Date date = super.getDate();
        if (this.ifstudyabroad == false) {
            String result = super.toString() + ":tuition due:" +
                    super.formating(super.getTuitiondue()) + ":total payment:" +
                    super.formating(super.getPayment()) + ":payment date:";
            if (date == null) {
                result += "--/--/--";
            } else {
                result += date.toString();
            }
            result += ":non-resident:international";
            return result;
        } else if (this.ifstudyabroad == true) {
            String result = super.toString() + ":tuition due:" +
                    super.formating(super.getTuitiondue()) + ":total payment:" +
                    super.formating(super.getPayment()) + ":payment date:";
            if (date == null) {
                result += "--/--/--";
            } else {
                result += date.toString();
            }
            result += ":non-resident:international:study abroad";
            return result;
        }
        return null;
    }

    /**
     * Setter method to set the international student`s study-abroad status to true.
     */
    public void setStatus() {
        this.ifstudyabroad = true;
        super.setCredit(parttimeline);
        super.resetpayment();
        super.setDate(null);
        this.tuitionDue();
    }

    /**
     * Getter method for obtaining the study abroad status of this student.
     *
     * @return the study abroad status of this student.
     */
    public boolean getifstudyabroad() {
        return this.ifstudyabroad;
    }

    /**
     * Setter method for setting the study abroad status.
     */
    public void setIfstudyabroad() {
        this.ifstudyabroad = true;
    }

}
