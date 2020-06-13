package Object;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * A class contains all handlers exception of data type
 * @author Pham Chi Thanh
 */
public class ExceptionHandler {
    public ExceptionHandler(){};
    public static String getString()
    {
        String a;
        Scanner s;
        while (true)
            try{
                s=new Scanner(System.in);
                a=s.nextLine();
                if(a == null || a.trim().isEmpty()){
                    throw new InputMismatchException();
                }
                else return a.trim();
            }catch (InputMismatchException e)
            {
                System.out.println("That string can't an Empty String or null Value.");
            }
    }
    public static long getLong(long min_value, long max_value)
    {
        long num=0;
        String a;

        while(true)
        {
            try{
                a=getString();
                num=Long.parseLong(a);
                if(num<min_value||num>max_value) throw new InputMismatchException();
                else return num;
            }catch (NumberFormatException e)
            {
                System.out.println("This parameter should be a number. ");
            }
            catch (InputMismatchException e)
            {
                System.out.println("The number should be in the range (" + min_value + "," + max_value + ").");
            }
        }
    }
    public static int getInt()
    {
        int num=0;
        String a;

        while(true)
        {
            try{
                a=getString();
                num=Integer.parseInt(a);
                return num;
            }catch (NumberFormatException e)
            {
                System.out.println("This parameter should be a number. ");
            }
        }
    }
    public static double getDouble(double min_value, double max_value)
    {
        double num=0;
        String a;
        while(true)
        {
            try{
                a=getString();
                num=Double.parseDouble(a);
                if(num>min_value&&num<max_value) return num;
                else throw new InputMismatchException();
            }catch (NumberFormatException e)
            {
                System.out.println("This parameter should be a number. ");
            }
            catch (InputMismatchException e)
            {
                System.out.println("The number should be in the range (" + min_value + "," + max_value + ").");
            }
        }

    }
    public static boolean getBoolean()
    {
        String s;
        while(true)
        {
            try{
                s= getString();
                if(s.toLowerCase().equals("true")||s.toLowerCase().equals("false"))  return Boolean.parseBoolean(s);
                else throw new InputMismatchException();
            }
            catch (InputMismatchException e)
            {
                System.out.println("This parameter should be True or False.");

            }
        }
    }
    public  static Mood creatMood()
    {
        String a = null;


        while(true)
        {   try {
            a =getString();
            for (Mood m : Mood.values()) {
                if (a.toUpperCase().equals(m.name()))
                    return m;
            }
            throw new IllegalArgumentException();
        }catch (IllegalArgumentException e)
        {
            System.out.println("This parameter should be one of the enum members.");
        }
        }
    }
    public static WeaponType creatWeaponType()
    {
        String a = null;
        while(true)
        {
            try {
                a = getString();
                for (WeaponType m : WeaponType.values()) {
                    if (a.toUpperCase().equals(m.name()))
                        return m;
                }
                throw new IllegalArgumentException();
            }catch (IllegalArgumentException e)
            {
                System.out.println("This parameter should be one of the enum members.");
            }
        }
    }
}
