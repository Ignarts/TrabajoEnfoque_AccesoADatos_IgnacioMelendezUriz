package model;

public class Contract {
    private String organization;
    private String description;
    private double amount;
    private String date;
    private String awardedTo;

    public Contract(String organization, String description, double amount, String date, String awardedTo) {
        this.organization = organization;
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.awardedTo = awardedTo;
    }

    public String getOrganization() {
        return organization;
    }

    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }

    public String getAwardedTo() {
        return awardedTo;
    }
}
