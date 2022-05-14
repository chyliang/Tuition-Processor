package com.example.tuition;

/**
 * A class constructing each student`s profile.
 *
 * @author Xinyu Meng, Chengyu Liang
 */
public class Profile {

    private String name;
    private Major major;
    private int credit;

    /**
     * The constructor of profile.
     *
     * @param name   The name of student in profile.
     * @param major  The major of student in profile.
     * @param credit The credit of student in profile.
     */
    public Profile(String name, Major major, int credit) {
        this.name = name;
        this.major = major;
        this.credit = credit;
    }

    /**
     * To get the string format of profile.
     *
     * @return the string format of profile.
     */
    @Override
    public String toString() {
        return (name + ":" + major + ":" + credit + " credit hours");
    }

    /**
     * To check if two profiles are equal.
     *
     * @param obj The profile to be compared.
     * @return true if two profile are equal, false if not.
     */
    @Override
    public boolean equals(Object obj) {
        Profile profile = (Profile) obj;
        if (this.name.equals(profile.getname()) && this.major.equals(profile.getMajor())) {
            return true;
        }
        return false;
    }

    /**
     * Getter method for getting the name in profile.
     *
     * @return the name in profile.
     */
    public String getname() {
        return this.name;
    }

    /**
     * Getter method for getting the credit in profile.
     *
     * @return the credit in profile.
     */
    public int getCredit() {
        return this.credit;
    }

    /**
     * Getter method for getting the major in profile.
     *
     * @return the major in profile.
     */
    public Major getMajor() {
        return this.major;
    }

    /**
     * Setter method for setting the credit to profile.
     *
     * @param credit the amount of credit to be assigned.
     */
    public void setCredit(int credit) {
        this.credit = credit;
    }
}


