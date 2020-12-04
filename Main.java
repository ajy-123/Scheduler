import java.time.LocalDate;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Department department = new Department();

        String s = "2020-11-10";
        String e = "2020-12-30";


        department.fillCalendarDates(s,e);

        Worker worker1 = new Worker("worker1", "A", "Lifeguard", 2.00);
        Worker worker2 = new Worker("worker2", "A", "Lifeguard", 3.00);
        Worker worker3 = new Worker("worker3", "A", "Lifeguard", 4.00);

        worker1.addDayOff("2020-11-11");

        Worker[] workers = new Worker[3];
        workers[0] = worker1;
        workers[1] = worker2;
        workers[2] = worker3;

        department.addWorkers(workers);
        String date = "2020-11-11";
        LocalDate date1 = LocalDate.parse(date);





    }
}
