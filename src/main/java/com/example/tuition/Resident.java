package com.example.tuition;

/**
 * A subclass of student constructing the resident structure which storing the different attributes of a student who is resident.
 *
 * @author Xinyu Meng, Chengyu Liang
 */
public class Resident extends Student {

    final int residenttuition = 12536, universityfee = 3268, parttimeperhour = 404,
            extraline = 16, parttimeline = 12, mincredit = 3, maxcredit = 24;
    final double percent = 0.8;

    private double aid;

    /**
     * Constructor method to creating a resident structure with the corresponding attributes.
     *
     * @param name   resident`s name.
     * @param major  resident`s major.
     * @param credit resident`s credit.
     * @param aid    resident`s aid.
     */
    public Resident(String name, Major major, int credit, double aid) {
        super(name, major, credit);
        this.aid = aid;
    }

    /**
     * To calculate the tuition due for the students who are resident with different credits situations.
     */
    @Override
    public void tuitionDue() {
        Profile profile = super.getProfile();
        int credit = profile.getCredit();
        //credit over 16
        if (credit > extraline && credit <= maxcredit) {
            double value = (residenttuition + universityfee) - this.aid -
                    super.getPayment() + parttimeperhour * (credit - extraline);
            super.setTuitiondue(value);
        }
        //credit between 12 and 16
        else if (credit >= parttimeline && credit <= extraline) {
            double value = (residenttuition + universityfee) - this.aid - super.getPayment();
            super.setTuitiondue(value);
        }
        //part time
        else if (credit < parttimeline && credit >= mincredit) {
            double value = parttimeperhour * credit + universityfee * percent - super.getPayment();
            super.setTuitiondue(value);
        }
    }

    /**
     * Get the string format of a resident object.
     *
     * @return the string format of the resident object.
     */
    @Override
    public String toString() {

        Date date = super.getDate();
        String result = super.toString() + ":tuition due:" +
                super.formating(super.getTuitiondue()) + ":total payment:" +
                super.formating(super.getPayment()) + ":payment date:";
        if (date == null) {
            result += "--/--/--";
        } else {
            result += date.toString();
        }
        result += ":resident";
        if (aid > 0) {
            result += ":financial aid $" + super.formating(aid);
        }
        return result;
    }

    /**
     * Getter method for getting the aid that student who is resident received.
     *
     * @return the amount of aid that student received.
     */
    public double getAid() {
        return this.aid;
    }

    /**
     * Setter method for setting the aid to the student who is resident and recalculate the tuitiondue.
     *
     * @param amount the amount to be assigned to student`s aid.
     */
    public void setAid(double amount) {
        this.aid = amount;
        this.tuitionDue();
    }
}
