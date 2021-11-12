package com.example.logswasthfirebase;


public class VillagerHelperClass {

    String Education,State,District,Income,Caste,Disease;
    int Age;

    public VillagerHelperClass(String education, String state, String district, String income, String caste, String disease, int age) {
        Education = education;
        State = state;
        District = district;
        Income = income;
        Caste = caste;
        Disease = disease;
        Age = age;
    }

    public VillagerHelperClass() {
    }

    public String getEducation() {
        return Education;
    }

    public void setEducation(String education) {
        Education = education;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getDistrict() {
        return District;
    }

    public void setDistrict(String district) {
        District = district;
    }

    public String getIncome() {
        return Income;
    }

    public void setIncome(String income) {
        Income = income;
    }

    public String getCaste() {
        return Caste;
    }

    public void setCaste(String caste) {
        Caste = caste;
    }

    public String getDisease() {
        return Disease;
    }

    public void setDisease(String disease) {
        Disease = disease;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }
}