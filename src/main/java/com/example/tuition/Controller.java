package com.example.tuition;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.*;

import java.time.LocalDate;


/**
 * The class contains the methods which serve for the functions of interface.
 * This contains the variables methods for executing the interface service.
 *
 * @author Xinyu Meng, Chengyu Liang
 */
public class Controller {

    final int studyabroadcredit = 12;
    Roster roster = new Roster();

    @FXML
    private RadioButton BA;

    @FXML
    private RadioButton BA_payment;

    @FXML
    private RadioButton CS;

    @FXML
    private RadioButton CS_payment;

    @FXML
    private RadioButton EE;

    @FXML
    private RadioButton EE_payment;

    @FXML
    private RadioButton IT;

    @FXML
    private RadioButton IT_payment;

    @FXML
    private RadioButton ME;

    @FXML
    private RadioButton ME_payment;

    @FXML
    private TextField amount;

    @FXML
    private TextField credit_hours;

    @FXML
    private RadioButton ct;

    @FXML
    private DatePicker datetext;

    @FXML
    private TextField financialaid;

    @FXML
    private RadioButton international;

    @FXML
    private TextField name;

    @FXML
    private TextField name_payment;

    @FXML
    private RadioButton nonresident;

    @FXML
    private RadioButton ny;

    @FXML
    private TextField payment;

    @FXML
    private RadioButton resident;

    @FXML
    private CheckBox studyabroad;

    @FXML
    private TextArea textarea;

    /**
     * To set the button status after the resident button is on action.
     *
     * @param event An event trigger when resident button is on action.
     */
    @FXML
    void residentclicked(ActionEvent event) {
        ny.setSelected(false);
        ct.setSelected(false);
        international.setSelected(false);
        studyabroad.setSelected(false);
        ny.setDisable(true);
        ct.setDisable(true);
        international.setDisable(true);
        studyabroad.setDisable(true);
    }

    /**
     * To set the button status after the resident button is on action.
     *
     * @param event An event trigger when nonresident button is on action.
     */
    @FXML
    void nonresidentclicked(ActionEvent event) {
        ny.setDisable(false);
        ct.setDisable(false);
        international.setDisable(false);
        studyabroad.setDisable(true);
    }

    /**
     * To set the button status after the tristate button is on action.
     *
     * @param event An event trigger when tristate button is on action.
     */
    @FXML
    void tristateclicked(ActionEvent event) {
        studyabroad.setSelected(false);
        studyabroad.setDisable(true);
    }

    /**
     * To set the button status after the international button is on action.
     *
     * @param event An event trigger when international button is on action.
     */
    @FXML
    void internationalclicked(ActionEvent event) {
        studyabroad.setDisable(false);
    }

    /**
     * To execute after the printbydate button is on action.
     * This print the roster by date.
     *
     * @param event An event trigger when printbydate button is on action.
     */
    @FXML
    void printbydate(ActionEvent event) {
        if (roster.getsize() == 0) {
            textarea.appendText("Student roster is empty!\n");
            return;
        }
        textarea.appendText(roster.printbyPayment());
    }

    /**
     * To execute after the printbyname button is on action.
     * This print the roster by name.
     *
     * @param event An event trigger when printbyname button is on action.
     */
    @FXML
    void printbyname(ActionEvent event) {
        if (roster.getsize() == 0) {
            textarea.appendText("Student roster is empty!\n");
            return;
        }
        textarea.appendText(roster.printbyName());
    }

    /**
     * To execute after the printbyroster button is on action.
     * This print the roster by roster.
     *
     * @param event An event trigger when printbyroster button is on action.
     */
    @FXML
    void printbyroster(ActionEvent event) {
        if (roster.getsize() == 0) {
            textarea.appendText("Student roster is empty!\n");
            return;
        }
        textarea.appendText(roster.print());
    }

    /**
     * To give information about the project.
     *
     * @param event An event trigger when about button is on action.
     */
    @FXML
    void about(ActionEvent event) {
        textarea.appendText("@author Xinyu Meng, Chengyu Liang\n");
    }

    /**
     * To calculate the tuition due for students in roster.
     *
     * @param event An event trigger when calculate button is on action.
     */
    @FXML
    void calculate(ActionEvent event) {
        if (roster.getsize() == 0) {
            textarea.appendText("Roster is empty!\n");
        }
        roster.calculate();
        textarea.appendText("Calculation completed.\n");
    }

    /**
     * The getter method for getting the major of the student.
     *
     * @return the major of the student.
     */
    @FXML
    private Major getmajorbutton() {
        Major major = null;

        if (CS.isSelected()) {
            major = Major.CS;
        } else if (EE.isSelected()) {
            major = Major.EE;
        } else if (ME.isSelected()) {
            major = Major.ME;
        } else if (BA.isSelected()) {
            major = Major.BA;
        } else if (IT.isSelected()) {
            major = Major.IT;
        }

        return major;
    }

    /**
     * To update the study abroad status of the student.
     */
    @FXML
    private void update() {
        if (name.getText() == null) {
            textarea.appendText("Missing name!\n");
            return;
        }
        if (getmajorbutton() == null) {
            textarea.appendText("Missing major!\n");
            return;
        }

        Student[] list = roster.getlist();
        for (int i = 0; i < this.roster.getsize(); i++) {

            Profile profile = list[i].getProfile();
            if ((profile.getname()).equals(name.getText())) {
                if (profile.getMajor() == getmajorbutton()) {
                    if (list[i] instanceof International) {
                        if (!((International) list[i]).getifstudyabroad()) {
                            ((International) list[i]).setIfstudyabroad();
                            textarea.appendText("Study abroad status updated.\n");
                            list[i].setCredit(studyabroadcredit);
                            return;
                        }
                        textarea.appendText("Student has already attended study abroad program!\n");
                        return;
                    }
                    textarea.appendText("Student is not a international student!\n");
                    return;
                }
            }
        }
        textarea.appendText("Cannot find this student.\n");
    }

    /**
     * To get the major of the student in the second page.(payment page)
     *
     * @return the major of the student.
     */
    @FXML
    private Major getpaymentmajor() {
        Major major = null;

        if (CS_payment.isSelected()) {
            major = Major.CS;
        } else if (EE_payment.isSelected()) {
            major = Major.EE;
        } else if (ME_payment.isSelected()) {
            major = Major.ME;
        } else if (BA_payment.isSelected()) {
            major = Major.BA;
        } else if (IT_payment.isSelected()) {
            major = Major.IT;
        }

        return major;
    }

    /**
     * To add the student to the roster with the required information.
     *
     * @param event An event trigger when add_student button is on action.
     */
    @FXML
    private void setAddstudent(ActionEvent event) {
        try {
            if (credit_hours.getText().isEmpty()) {
                textarea.appendText("Missing credit hours!\n");
                return;
            }
            int credit;
            try {
                credit = Integer.parseInt(credit_hours.getText());
            } catch (Exception e) {
                textarea.appendText("Invalid credit.\n");
                return;
            }

            if (name.getText().isEmpty()) {
                textarea.appendText("Missing name!\n");
                return;
            }
            if (getmajorbutton() == null) {
                textarea.appendText("Missing major!\n");
                return;
            }
            if (credit < 3 || credit > 24) {
                textarea.appendText("The credit hours is not in the range!\n");
                return;
            }

            boolean checkifalready = false;

            if (resident.isSelected()) {
                Student student = new Resident(name.getText(), getmajorbutton(), credit, 0);
                checkifalready = roster.add(student);
            } else if (nonresident.isSelected()) {
                if (ny.isSelected()) {
                    Student student = new TriState(name.getText(), getmajorbutton(), credit, "NY");
                    checkifalready = roster.add(student);
                } else if (ct.isSelected()) {
                    Student student = new TriState(name.getText(), getmajorbutton(), credit, "CT");
                    checkifalready = roster.add(student);
                } else if (international.isSelected()) {
                    if (studyabroad.isSelected()) {
                        if (credit != 12) {
                            textarea.appendText("Incorrect credit hours.\n");
                            return;
                        }
                        Student student = new International(name.getText(), getmajorbutton(), 12, true);
                        checkifalready = roster.add(student);
                    } else {
                        if (credit < 12) {
                            textarea.appendText("International students must enroll at least 12 credits.\n");
                            return;
                        }
                        Student student = new International(name.getText(), getmajorbutton(), credit, false);
                        checkifalready = roster.add(student);
                    }
                } else {
                    Student student = new NonResident(name.getText(), getmajorbutton(), credit);
                    checkifalready = roster.add(student);
                }
            } else {
                textarea.appendText("Missing information!\n");
                return;
            }

            if (checkifalready == true) {
                textarea.appendText("Student added.\n");
                return;
            } else {
                textarea.appendText("Student is already in the roster.\n");
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * To find the student and remove the student from the roster.
     *
     * @param event An event trigger when remove_student button is on action.
     */
    @FXML
    private void setRemovestudent(ActionEvent event) {
        try {


            if (name.getText() == null) {
                textarea.appendText("Missing name!\n");
                return;
            }
            if (getmajorbutton() == null) {
                textarea.appendText("Missing major!\n");
                return;
            }

            Student[] list = roster.getlist();
            for (int i = 0; i < this.roster.getsize(); i++) {

                Profile profile = list[i].getProfile();
                if ((profile.getname()).equals(name.getText())) {
                    if (profile.getMajor() == getmajorbutton()) {
                        roster.remove(list[i]);
                        textarea.appendText("Student removed!\n");
                        return;
                    }
                }
            }
            textarea.appendText("Cannot find this student.\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * To calculate the tuition_due for single student.
     *
     * @param event An event trigger when tuition_due button is on action.
     */
    @FXML
    private void tuitionduebutton(ActionEvent event) {
        try {
            String thename = name.getText();
            Major major = getmajorbutton();
            if (name == null) {
                textarea.appendText("Missing name!" + "\n");
                return;
            }
            if (major == null) {
                textarea.appendText("Missing major!" + "\n");
                return;
            }
            Student student = new Student(thename, major, 0);
            double tuitiondue = roster.calculatesingle(student);
            if (tuitiondue == -1) {
                textarea.appendText("Cannot find this student!\n");
                return;
            }
            amount.setText(student.formating(tuitiondue));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * To set the payment for the student with specific amount.
     *
     * @param event An event trigger when pay button is on action.
     */
    @FXML
    private void paybutton(ActionEvent event) {
        try {
            String name = name_payment.getText();
            Major major = getpaymentmajor();
            if (name == null) {
                textarea.appendText("Missing name!\n");
                return;
            }
            if (major == null) {
                textarea.appendText("Missing major!\n");
                return;
            }
            double amount;
            try {
                amount = Double.parseDouble(payment.getText());
            } catch (Exception e) {
                textarea.appendText("Invalid amount.\n");
                return;
            }
            LocalDate date = datetext.getValue();
            Date recorded_date = new Date(date.getYear(), date.getMonthValue(), date.getDayOfMonth());
            String result = roster.pay(name, major, amount, recorded_date);
            textarea.appendText(result + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * To set the financial aid for the student with specific amount.
     *
     * @param event An event trigger when set_aid button is on action.
     */
    @FXML
    private void setaid(ActionEvent event) {
        try {
            if (name_payment.getText().isEmpty()) {
                textarea.appendText("Missing name!\n");
                return;
            }
            String name = name_payment.getText();
            Major major = getpaymentmajor();
            if (major == null) {
                textarea.appendText("Missing major!\n");
                return;
            }
            double amount;
            try {
                amount = Double.parseDouble(financialaid.getText());
            } catch (Exception e) {
                textarea.appendText("Invalid amount.\n");
                return;
            }
            String result = roster.setf(name, major, amount);
            textarea.appendText(result + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * To set the other nonresident button to "off" status when User
     * click on the nonresident radio button again.
     *
     * @param mouseEvent A mouse trigger when user click the nonresident radio button again.
     */
    @FXML
    private void onceclicked(javafx.scene.input.MouseEvent mouseEvent) {
        ny.setSelected(false);
        ct.setSelected(false);
        international.setSelected(false);
        studyabroad.setSelected(false);
    }
}
