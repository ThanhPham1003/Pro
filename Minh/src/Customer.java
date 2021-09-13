public class Customer {
    private double indentityNumber;
    private double initualBalance;
    public Customer(double indentityNumber, double initualBalance)
    {
        this.indentityNumber = indentityNumber;
        this.initualBalance = initualBalance;
    }

    public double getIndentityNumber() {
        return indentityNumber;
    }

    public double getInitualBalance() {
        return initualBalance;
    }

    public void setIndentityNumber(int indentityNumber) {
        this.indentityNumber = indentityNumber;
    }

    public void setInitualBalance(double initualBalance) {
        this.initualBalance = initualBalance;
    }
}
