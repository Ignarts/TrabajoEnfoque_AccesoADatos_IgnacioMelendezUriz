package model;

public class Contract {

    private String nif;
    private String awardedTo;
    private String genericObject;
    private String objectDescription;
    private String awardedDate;
    private double amount;
    private String consultedProviders;

    public Contract(String nif, String awardedTo, String genericObject, String objectDescription,
                    String awardedDate, double amount, String consultedProviders) {
        this.nif = nif;
        this.awardedTo = awardedTo;
        this.genericObject = genericObject;
        this.objectDescription = objectDescription;
        this.awardedDate = awardedDate;
        this.amount = amount;
        this.consultedProviders = consultedProviders;
    }

    public String getNif() { return nif; }
    public String getAwardedTo() { return awardedTo; }
    public String getGenericObject() { return genericObject; }
    public String getObjectDescription() { return objectDescription; }
    public String getAwardedDate() { return awardedDate; }
    public double getAmount() { return amount; }
    public String getConsultedProviders() { return consultedProviders; }
}
