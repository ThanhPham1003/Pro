import java.util.Scanner;

public class Main {
    public static double calculateTicketPrice(double distance)
    {
        double price =0;
        price = Math.round(distance-5)*0.4 + 1.9;
        return price;
    }
    public static void main(String[] args) {
        double AB, BC, CD;
        String kindTicket;
        String start, end;

        Scanner s= new Scanner(System.in);
        System.out.println("Please enter the distance of AB: ");
        AB = s.nextDouble();
        System.out.println("AB: "+AB);

        System.out.println("Please enter the distance of BC: ");
        BC = s.nextDouble();

        System.out.println("Please enter the distance of CD: ");
        CD = s.nextDouble();

        Distance d= new Distance(AB,BC,CD);

        System.out.println("Please enter kind of ticket: ");
        kindTicket = s.next();
        if(kindTicket.equals("one-way"))
        {
            System.out.println("Please enter the start station: ");
            start = s.next();
            System.out.println("Please enter the end station: ");
            end = s.next();
            double amountOfMoney =0;
            if (start.equals("A"))
            {
                if(end.equals("B"))
                {
                    amountOfMoney = calculateTicketPrice(d.getAB());
                }
                else if(end.equals("C")){
                    amountOfMoney = calculateTicketPrice(d.getAC());
                }
                else if(end.equals("D")){
                    amountOfMoney = calculateTicketPrice(d.getAD());
                }
            }
            else if(start.equals("B"))
            {
                if(end.equals("C")) amountOfMoney=calculateTicketPrice(d.getBC());
                else amountOfMoney = calculateTicketPrice(d.getBD());
            }
            else amountOfMoney =calculateTicketPrice(d.getCD());
            System.out.println("Price for your ticket is: " + amountOfMoney+"$");

        }
        else System.out.println("Price for your ticket is: 8.5$");

    }
}
