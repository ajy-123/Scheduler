import java.time.LocalDate;
import java.util.*;

public class Worker {
    private String name;
    private String pool;
    private String title;
    private double wage;
    private ArrayList<LocalDate> daysOff;
    private double numHoursWorked;

    public Worker(String pool, String title, double wage, ArrayList<LocalDate> daysOff, double numHours
    , String name){
        this.pool = pool;
        this.title = title;
        this.wage = wage;
        this.daysOff = daysOff;
        this.numHoursWorked = numHours;
        this.name = name;
    }

    public Worker(String name, String pool, String title, double wage){
        this.name = name;
        this.pool = pool;
        this.title = title;
        this.wage = wage;
        this.daysOff = new ArrayList<LocalDate>();
        this.numHoursWorked = 0.0;
    }

    public void addDayOff(String day){
        LocalDate dayOff = LocalDate.parse(day);
        daysOff.add(dayOff);
    }




    public double getWage() {
        return wage;
    }

    public String getPool() {
        return pool;
    }

    public ArrayList<LocalDate> getDaysOff() {
        return daysOff;
    }

    public String getTitle() {
        return title;
    }

    public double getNumHoursWorked() {
        return numHoursWorked;
    }

    public String toString(){
        return name + ", Title: " + title + ", Pool: " + pool + ", Wage: " + wage;
    }

}
