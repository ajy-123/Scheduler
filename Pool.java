import java.time.LocalDate;
import java.util.*;

public class Pool {
    private String poolName;
    private final int numChairs;
    final int NUM_MANAGERS = 2;
    final int NUM_PAs = 2;
    private Map<LocalDate , ArrayList<Worker>> dateMap;

    public Pool(String name, int numChairs){
        this.poolName = name;
        this.numChairs = numChairs;
    }

    public Pool(){
        this.poolName = "";
        this.numChairs = 0;
    }

    public void createMap(ArrayList<LocalDate> array){
        for(LocalDate date : array){
            ArrayList<Worker> arrayList = new ArrayList<Worker>();
            dateMap.put(date, arrayList);
        }
    }

    public void addWorker(LocalDate date, Worker worker){
        dateMap.get(date).add(worker);
    }

    public boolean isFilledOnDate(LocalDate date){
        int numPAs = 0;
        int numManagers = 0;
        int numGuards = 0;

        for(Worker worker : dateMap.get(date)){
            if((worker.getTitle()).equals("PA")){
                ++numPAs;
            }

            if((worker.getTitle()).equals("Lifeguard")){
                ++numGuards;
            }

            if((worker.getTitle()).equals("Manager")){
                ++numManagers;
            }
        }
        if((numManagers == NUM_MANAGERS) && (numGuards >= numChairs + 2) && (numPAs == NUM_PAs)){
            return true;
        } else{
            return false;
        }



    }

    public String getPoolName() {
        return poolName;
    }

    public int getNumChairs() {
        return numChairs;
    }

    public int getNUM_MANAGERS() {
        return NUM_MANAGERS;
    }

    public int getNUM_PAs() {
        return NUM_PAs;
    }

    public ArrayList<Worker> accessDate(LocalDate date){
        return dateMap.get(date);
    }

    @Override
    public String toString(){
        return "" + poolName + " Pool. Number of Chairs: " + numChairs;
    }
}
