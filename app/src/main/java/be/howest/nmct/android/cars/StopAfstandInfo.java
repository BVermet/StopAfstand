package be.howest.nmct.android.cars;

/**
 * Created by Bryan on 13/02/2015.
 */
public class StopAfstandInfo {

    private int snelheid;
    private float reactietijd;
    private WegType typeWeg;

    public StopAfstandInfo(){
        snelheid = 0;
        reactietijd = 2.5f;
        typeWeg = WegType.WEGDEK_NAT;
    }
    public StopAfstandInfo(int snelheid, float reactieTijd, WegType typeWeg){
        super();
        this.snelheid = snelheid;
        this.reactietijd = reactieTijd;
        this.typeWeg = typeWeg ;
    }

    public int getSnelheid(){
        return snelheid;
    }

    public void setSnelheid(int snelheid){
        this.snelheid = snelheid;
    }

    public float getReactietijd(){
        return reactietijd;
    }

    public void setReactietijd(float reactietijd){
        this.reactietijd = reactietijd;
    }

    public WegType getTypeWeg() {
        return typeWeg;
    }

    public void setTypeWeg(String type) {
        if (type.equals("nat")){
            this.typeWeg = WegType.WEGDEK_NAT;
        }else if(type.equals("droog")){
            this.typeWeg = be.howest.nmct.android.cars.WegType.WEGDEK_DROOG;
        }else{
            System.out.println("Gelieve een juist type wegdek op te geven!");
            return;
        }

    }

    public float getStopAfstand() {
        float remVertraging = 8;
        if(typeWeg.equals(WegType.WEGDEK_DROOG)){
            remVertraging = 5;
        }

        return (float) (snelheid/3.6 * reactietijd + Math.pow(snelheid/3.6, 2)/(2*remVertraging));
    }
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Stopafstand bij ").append(typeWeg)
                .append(" wegdek is ").append(getStopAfstand())
                .append("m  voor de snelheid van").append(snelheid)
                .append("km per uur wanneer de reactie tijd ").append(reactietijd).append (" seconde(n) is ");
        return sb.toString();
    }


}
