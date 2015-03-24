package be.howest.nmct.android.cars;

/**
 * Created by Bryan on 13/02/2015.
 */
public enum WegType {
    WEGDEK_NAT(8.0f, "nat"), WEGDEK_DROOG(5.0f, "droog");
    private float remVertraging;
    private String naam;
    private WegType (float remVertraging, String naam)
    {
        this.setRemVertraging(remVertraging);
        this.naam = naam;
    }
    public float getRemVertraging() {
        return remVertraging;
    }
    public void setRemVertraging(float remVertraging) {
        this.remVertraging = remVertraging;
    }
    public String toString(){
        return naam;
    }
}
