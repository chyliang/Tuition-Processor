package com.example.tuition;

/**
 * A subclass of student constructing the tri-state student structure which
 * storing the different attributes of a tri-state student.
 *
 * @author Xinyu Meng, Chengyu Liang
 */
public class TriState extends NonResident {
    final int tuition = 29737, universityfee = 3268, parttime = 966, extraline = 16, parttimeline = 12, mincredit = 3, maxcredit = 24, nydiscount = 4000, ctdiscount = 5000;
    final double percent = 0.8;
    private String iftristate;

    /**
     * Constructor method to creating a tri-state student structure with the corresponding attributes.
     *
     * @param name       tri-state student`s name.
     * @param major      tri-state student`s major.
     * @param credit     tri-state student`s credit.
     * @param iftristate where the tri-state student comes from.
     */
    public TriState(String name, Major major, int credit, String iftristate) {
        super(name, major, credit);
        this.iftristate = iftristate;
    }

    /**
     * To calculate the tuition due for the tri-state students with different credits situations.
     */
    @Override
    public void tuitionDue() {
        Profile profile = super.getProfile();
        int credit = profile.getCredit();
        double ifoversixteen = 0;
        if (credit > extraline && credit <= maxcredit) {
            ifoversixteen = credit - extraline;
        }

        if (credit >= parttimeline && credit <= maxcredit) {
            if (this.iftristate.equals("NY")) {
                double value = tuition + universityfee - super.getPayment() - nydiscount + ifoversixteen * parttime;
                super.setTuitiondue(value);
            } else if (this.iftristate.equals("CT")) {
                double value = tuition + universityfee - super.getPayment() - ctdiscount + ifoversixteen * parttime;
                super.setTuitiondue(value);
            }
        } else if (credit >= mincredit && credit < parttimeline) {
            double value = parttime * credit + universityfee * percent - super.getPayment();
            super.setTuitiondue(value);
        }
    }

    /**
     * Get the string format of a tri-state student object.
     *
     * @return the string format of the international student object.
     */
    @Override
    public String toString() {

        Date date = super.getDate();
        if (this.iftristate.equals("NY")) {
            String result = super.toString() + ":tuition due:" +
                    super.formating(super.getTuitiondue()) + ":total payment:" + super.formating(super.getPayment()) + ":payment date:";
            if (date == null) {
                result += "--/--/--";
            } else {
                result += date.toString();
            }
            result += ":non-resident(tri-state):NY";
            return result;
        } else if (this.iftristate.equals("CT")) {
            String result = super.toString() + ":tuition due:" +
                    super.formating(super.getTuitiondue()) + ":total payment:" + super.formating(super.getPayment()) +
                    ":payment date:";
            if (date == null) {
                result += "--/--/--";
            } else {
                result += date.toString();
            }
            result += ":non-resident(tri-state):CT";
            return result;
        }
        return null;
    }

}
