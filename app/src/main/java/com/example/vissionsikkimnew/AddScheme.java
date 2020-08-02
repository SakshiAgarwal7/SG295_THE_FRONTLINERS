package com.example.vissionsikkimnew;

public class AddScheme {
    String SchemeName,DepartmentName,DivisionName,FinancialYear,SchemeDescription,IncomeLevel,Age,Category,Gender,WTOContact,PDFurl;

    public AddScheme() {

    }

    public AddScheme(String schemeName, String departmentName, String divisionName, String financialYear, String schemeDescription, String incomeLevel, String age, String category, String gender, String WTOContact, String pdfURL) {
        SchemeName = schemeName;
        DepartmentName = departmentName;
        DivisionName = divisionName;
        FinancialYear = financialYear;
        SchemeDescription = schemeDescription;
        IncomeLevel = incomeLevel;
        Age = age;
        Category = category;
        Gender = gender;
        this.WTOContact = WTOContact;
        this.PDFurl = pdfURL;
    }

    public String getSchemeName() {
        return SchemeName;
    }

    public void setSchemeName(String schemeName) {
        SchemeName = schemeName;
    }

    public String getDepartmentName() {
        return DepartmentName;
    }

    public void setDepartmentName(String departmentName) {
        DepartmentName = departmentName;
    }

    public String getDivisionName() {
        return DivisionName;
    }

    public void setDivisionName(String divisionName) {
        DivisionName = divisionName;
    }

    public String getFinancialYear() {
        return FinancialYear;
    }

    public void setFinancialYear(String financialYear) {
        FinancialYear = financialYear;
    }

    public String getSchemeDescription() {
        return SchemeDescription;
    }

    public void setSchemeDescription(String schemeDescription) {
        SchemeDescription = schemeDescription;
    }

    public String getIncomeLevel() {
        return IncomeLevel;
    }

    public void setIncomeLevel(String incomeLevel) {
        IncomeLevel = incomeLevel;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getWTOContact() {
        return WTOContact;
    }

    public void setWTOContact(String WTOContact) {
        this.WTOContact = WTOContact;
    }

    public String getPdfURL() {
        return PDFurl;
    }

    public void setPdfURL(String pdfURL) {
        this.PDFurl = pdfURL;
    }
}
