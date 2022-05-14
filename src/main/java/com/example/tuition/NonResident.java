package com.example.tuition;

/**
 * A subclass of student constructing the non-resident student structure which storing
 * the different attributes of a non-resident student.
 *
 * @author Xinyu Meng, Chengyu Liang
 */
public class NonResident extends Student {
    final int tuition = 29737, universityfee = 3268, parttime = 966, extraline = 16, parttimeline = 12, mincredit = 3, maxcredit = 24;
    final double percent = 0.8;

    /**
     * Constructor method to creating a non-resident student structure with the corresponding attributes.
     *
     * @param name   non-resident student`s name.
     * @param major  non-resident student`s major.
     * @param credit non-resident student`s credit.
     */
    public NonResident(String name, Major major, int credit) {
        super(name, major, credit);
    }

    /**
     * To calculate the tuition due for the non-resident students with different credits situations.
     */
    @Override
    public void tuitionDue() {
        Profile profile = super.getProfile();
        int credit = profile.getCredit();

        //credit over 16
        if (credit > extraline && credit <= maxcredit) {
            double value = (tuition + universityfee) - super.getPayment() + parttime * (credit - extraline);
            super.setTuitiondue(value);
        }
        //credit between 12 and 16
        else if (credit >= parttimeline && credit <= extraline) {
            double value = (tuition + universityfee) - super.getPayment();
            super.setTuitiondue(value);
        }
        //part-time
        else if (credit < parttimeline && credit >= mincredit) {
            double value = parttime * credit + universityfee * percent - super.getPayment();
            super.setTuitiondue(value);
        }
    }

    /**
     * Get the string format of a non-resident student object.
     *
     * @return the string format of the non-resident student object.
     */
    @Override
    public String toString() {

        Date date = super.getDate();
        String result = super.toString() + ":tuition due:" +
                super.formating(super.getTuitiondue()) + ":total payment:" + super.formating(super.getPayment()) + ":payment date:";
        if (date == null) {
            result += "--/--/--";
        } else {
            result += date.toString();
        }
        result += ":non-resident";
        return result;
    }
}
