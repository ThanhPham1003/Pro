
package Object;

import User.ClientAnswer;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.lang.String;
import java.util.Scanner;

/**
 * Human Being object
 * @author Pham Chi Thanh
 */

public class HumanBeing implements Serializable, Comparable<HumanBeing> {
    public static final double MAX = 1e18;
    public static final double MIN = -1e18;
    private static Integer count = 1;
    private Integer id;
    private String name;
    private Coordinates coordinates;
    private java.time.ZonedDateTime creationDate;
    private boolean realHero;
    private boolean hasToothpick;
    private long impactSpeed;
    private WeaponType weaponType;
    private Mood mood;
    private Car car;
    private String owner;
    public HumanBeing()
    {

    }
    public HumanBeing(int id, String name, Coordinates coordinates,long impactSpeed,boolean realHero,boolean hasToothpick, WeaponType weaponType,Mood mood, Car car,String owner)
    {
        this.id=id;
        this.name=name;
        this.creationDate= LocalDateTime.now().atZone(ZoneId.of("UTC+7"));
        this.coordinates=coordinates;
        this.impactSpeed=impactSpeed;
        this.realHero=realHero;
        this.hasToothpick=hasToothpick;
        this.weaponType=weaponType;
        this.mood=mood;
        this.car=car;
        this.owner=owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HumanBeing that = (HumanBeing) o;
        return id == that.id &&
                realHero == that.realHero &&
                hasToothpick == that.hasToothpick &&
                Objects.equals(name, that.name) &&
                Objects.equals(coordinates, that.coordinates) &&
                Objects.equals(creationDate, that.creationDate) &&
                Objects.equals(impactSpeed, that.impactSpeed) &&
                weaponType == that.weaponType &&
                mood == that.mood &&
                Objects.equals(car, that.car);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, coordinates, creationDate, realHero, hasToothpick, impactSpeed, weaponType, mood, car);
    }

    @Override
    public String toString() {
        return "HumanBeing{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", realHero=" + realHero +
                ", hasToothpick=" + hasToothpick +
                ", impactSpeed=" + impactSpeed +
                ", weaponType=" + weaponType +
                ", mood=" + mood +
                ", car=" + car +
                ", owner='" + owner + '\'' +
                '}';
    }

    @Override
    public int compareTo(HumanBeing people){

        return this.name.compareTo(people.getName());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public long getImpactSpeed() {
        return impactSpeed;
    }

    public void setImpactSpeed(long impactSpeed) {
        this.impactSpeed = impactSpeed;
    }

    public void setMood(Mood mood) {
        this.mood = mood;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public void setCreationDate(ZonedDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public void setHasToothpick(boolean hasToothpick) {
        this.hasToothpick = hasToothpick;
    }

    public void setRealHero(boolean realHero) {
        this.realHero = realHero;
    }

    public void setWeaponType(WeaponType weaponType) {
        this.weaponType = weaponType;
    }

    public Car getCar() {
        return car;
    }

    public Mood getMood() {
        return mood;
    }

    public WeaponType getWeaponType() {
        return weaponType;
    }

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }
    public boolean isRealHero(){
        return realHero;
    }
    public boolean isHasToothpick()
    {
        return hasToothpick;
    }

    public String getOwner() {
        return owner;
    }

    public HumanBeing creatPerson(ClientAnswer clientAnswer)
    {
        HumanBeing person;
        double x;
        double y;
        String nameofCar;
        boolean cool;
        id=count++;
        creationDate= LocalDateTime.now().atZone(ZoneId.of("UTC+7"));
        Scanner commandReader = new Scanner(System.in);
        System.out.println("Enter the name of person: ");
        name=ExceptionHandler.getString();
        System.out.println("Enter the coordinates of person: ");
        System.out.print("X: ");
        x=ExceptionHandler.getDouble(MIN,697);
        System.out.print("Y: ");
        y=ExceptionHandler.getDouble(MIN,MAX);
        coordinates = new Coordinates(x,y);
        System.out.println("This is a real hero? Just answer True or False");
        realHero=ExceptionHandler.getBoolean();
        System.out.println("Has Toothpick? Just answer True or False");
        hasToothpick=ExceptionHandler.getBoolean();
        System.out.println("How many impact speed?");
        impactSpeed=ExceptionHandler.getLong(-910, Long.MAX_VALUE);

        System.out.println("Which weapon? Look at Weapon Type and choose one of them.");
        weaponType=ExceptionHandler.creatWeaponType();
        System.out.println("Which mood? Look at enum Mood and choose one of them.");
        mood= ExceptionHandler.creatMood();
        System.out.println("Name of the Car: ");
        nameofCar=ExceptionHandler.getString();
        System.out.println("Is it cool? Just answer True or False");
        cool=ExceptionHandler.getBoolean();
        car = new Car(nameofCar,cool);
        person=new HumanBeing(id,name,coordinates,impactSpeed,realHero,hasToothpick,weaponType,mood,car,clientAnswer.getUsername());
        return person;
    }
}
