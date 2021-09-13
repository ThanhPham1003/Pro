import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.util.Random;

public class Operations {
    public Operations() { };
    public double Deposit(double currentBalance, double amountofMoney)
    {
        return currentBalance + amountofMoney;
    }
    public double withDraw (double currentBalence, double amoutofMoney, String kindofAccount)
    {
        double cB = 0;
        if(kindofAccount.equals("normal")) {
            cB = currentBalence - amoutofMoney - 3000;
        }
        else if(kindofAccount.equals("vip"))
        {
            cB = currentBalence - amoutofMoney;
        }
        return cB;
    }
    public double getmonthlyInterest(double currentBalence, String kindofAccout)
    {
        double cB = 0;
        double interest = 0;
        if(kindofAccout.equals("vip")){
            interest =  currentBalence * 0.02;
        }
        else  if(kindofAccout.equals("saving")){
            Random rand = new Random();
            int rate = rand.nextInt(10)+1;
            System.out.println("Monthly interest rate for today is: "+ rate +"%");
            interest = currentBalence * ((double)rate / 100);
        }
        System.out.println("Your interest is: ");
        return cB = currentBalence - interest;

    }
}
