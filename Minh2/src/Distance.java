public class Distance {
    private double AB;
    private double BC;
    private double CD;
    public Distance(double AB, double BC, double CD )
    {
        this.AB = AB;
        this.BC = BC;
        this.CD = CD;
    }

    public double getAB() {
        return AB;
    }

    public double getBC() {
        return BC;
    }

    public double getCD() {
        return CD;
    }
    public double getAC(){
        return AB + BC;
    }
    public double getAD(){
        return AB + BC + CD;
    }
    public double getBD(){
        return BC + CD;
    }

    public void setAB(double AB) {
        this.AB = AB;
    }

    public void setBC(double BC) {
        this.BC = BC;
    }

    public void setCD(double CD) {
        this.CD = CD;
    }
}
