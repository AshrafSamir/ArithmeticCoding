public class Char {
    private String cha;
    private double low;
    private double high;
    private double range;
    Char(){

    }
    Char(String cha,double low,double high,double range ){
        this.cha = cha;
        this.low = low;
        this.high = high;
        this.range = range;
    }

    public void setCha(String cha) {
        this.cha = cha;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public void setRange(double range) {
        this.range = range;
    }

    public double getHigh() {
        return high;
    }

    public double getLow() {
        return low;
    }

    public double getRange() {
        return range;
    }

    public String getCha() {
        return cha;
    }

    @Override
    public String toString() {
        return "Char{" +
                "cha='" + cha + '\'' +
                ", low=" + low +
                ", high=" + high +
                ", range=" + range +
                '}';
    }
}

