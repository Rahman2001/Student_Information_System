package gazi.university;

import gazi.university.UMS.Parameter_Mismatch_Exception.String_Length_Mismatch_Exception.PersonIDLengthMismatchException;
import gazi.university.UMS.Parameter_Mismatch_Exception.String_Length_Mismatch_Exception.Person_Name_Length_Mismatch_Exception.PersonNameTooLongException;
import gazi.university.UMS.Parameter_Mismatch_Exception.String_Length_Mismatch_Exception.Person_Name_Length_Mismatch_Exception.PersonNameTooShortException;

public class Person {
    private String identity_no;
    private String name;

    public Person(String identity_no, String name) {
        this.identity_no = identity_no;
        this.name = name;
    }

    public Person() {
    }

    public String getIdentityNumber() {
        return this.identity_no;
    }

    public String getPersonName() {
        return this.name;
    }

    public void setIdentityNumber(String identity_no) throws PersonIDLengthMismatchException {

        if (identity_no.length() != 11) {
            throw new PersonIDLengthMismatchException(PersonIDLengthMismatchException.class.getSimpleName());
        } else {
            this.identity_no = identity_no;
        }
    }

    public void setPersonName(String name) throws PersonNameTooLongException, PersonNameTooShortException {
        if (name.length() > 50) {
            throw new PersonNameTooLongException(PersonNameTooLongException.class.getSimpleName());
        }
        if (name.length() < 10) {
            throw new PersonNameTooShortException(PersonNameTooShortException.class.getSimpleName());
        }
        this.name = name;
    }
}
