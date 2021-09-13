import java.util.Random;
import java.util.Scanner;

public class accountManager {
    private Operations operations;
    private Scanner s = new Scanner(System.in);
    private String kindofAccount ;
    private Customer customer;
    public accountManager (Operations operations)
    {
        this.operations = operations;
    }
    public void interactiveOperations() throws IllegalArgumentException {
       try {
           System.out.println("Please select kind of account you want to create (normal, vip, saving): ");
           kindofAccount = s.nextLine();
           Random rd = new Random();
           int n = 100000 + rd.nextInt(900000);
           if (kindofAccount.equals("normal")) {
               customer = new Customer(n, 100000);
           } else if (kindofAccount.equals("vip")) {
               customer = new Customer(n, 500000);
           } else {
               customer = new Customer(n, 20000);
           }
           while (true) {
               String command;
               double currentBalance = customer.getInitualBalance();
               if((kindofAccount.equals("normal")&&currentBalance<100000)||(kindofAccount.equals("vip")&&currentBalance < 500000) || (kindofAccount.equals("saving")&&currentBalance<20000)){
                   throw new IllegalArgumentException();
               }
               System.out.println("Please select a operation you want to execute (deposit, withdraw, get monthly interest): ");
               command = s.nextLine();
               if (command.equals("deposit")) {

                   System.out.println("Please select amount of money: ");
                   double aom = s.nextDouble();
                   customer.setInitualBalance(operations.Deposit(currentBalance,aom));
               }
               else if(command.equals("withdraw"))
               {
                   if(kindofAccount.equals("saving"))
                   {
                       System.out.println("You can't execute that operation because your kind of account is saving");
                       continue;
                   }
                   else {
                       System.out.println("Please select amount of money: ");
                       double aom = s.nextDouble();
                       customer.setInitualBalance(operations.withDraw(currentBalance,aom,kindofAccount));
                   }
               }
               else {
                   if(kindofAccount.equals("normal"))
                   {
                       System.out.println("You can't execute that operation because your kind of account is normal");
                       continue;
                   }
                   else {
                       customer.setInitualBalance(operations.getmonthlyInterest(currentBalance,kindofAccount));
                   }
               }
               System.out.println("Your current balance is: " + customer.getInitualBalance());
           }
       }catch (IllegalArgumentException e)
       {
           System.out.println("You have to put more money to your account!!!");
       }

    }
}
