
import java.time.LocalDate;
import java.util.*;
public class Department {
    private ArrayList<Pool> pools;
    private ArrayList<Worker> workers;
    private ArrayList<LocalDate> dates;

    public Department(){
        this.pools = new ArrayList<Pool>();
        this.workers = new ArrayList<Worker>();
        this.dates = new ArrayList<LocalDate>();
    }

    public void fillCalendarDates(String start, String end){
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);

        while(!startDate.isAfter(endDate)){
            dates.add(startDate);
            startDate = startDate.plusDays(1);
        }
    }

    public void printDates(){
        for(LocalDate day : dates){
            System.out.println(day);
        }
    }

    public void addPools(Pool[] poolArray){
        for(Pool pool : poolArray){
            pools.add(pool);
        }
    }

    public void printPools(){
        for(Pool pool : pools){
            System.out.println(pool.toString());
        }
    }

    public void addWorkers(Worker[] workerArray){
        for(Worker worker : workerArray){
            workers.add(worker);
        }
    }

    public void printWorkers(){
        for(Worker worker : workers){
            System.out.println(worker.toString());
        }
    }

    private ArrayList<Worker> getAvailableWorkers(LocalDate date){
        ArrayList<Worker> available = new ArrayList<Worker>();

        for(Worker worker : workers){
            if(!worker.getDaysOff().contains(date)){
                available.add(worker);
            }
        }

        return available;
    }

    private ArrayList<Worker> filterByPool(Pool pool, ArrayList<Worker> workers){
        ArrayList<Worker> poolWorkers = new ArrayList<Worker>();
        for(Worker worker : workers) {
            if(worker.getPool().equals(pool.getPoolName())){
                poolWorkers.add(worker);
            }
        }
        return poolWorkers;
    }

    private ArrayList<Worker> filterByTitle(ArrayList<Worker> arrayList, String title){
        ArrayList<Worker> filtered = new ArrayList<Worker>();

        for(Worker worker : arrayList){
            if(worker.getTitle().equals(title)){
                filtered.add(worker);
            }
        }
        return filtered;
    }


    private Worker getLowest(ArrayList<Worker> arrayList){
        double low = arrayList.get(0).getNumHoursWorked();
        Worker lowest = arrayList.get(0);

        for(Worker worker : arrayList){
            if(worker.getNumHoursWorked() < low){
                low = worker.getNumHoursWorked();
                lowest = worker;
            }
        }

        return lowest;
    }


    //TODO Implement Method
    public void generateDaySchedule(LocalDate date){
        ArrayList<Worker> availableWorkers = getAvailableWorkers(date);
        ArrayList<Worker> availableManagers = filterByTitle(availableWorkers, "Manager");
        ArrayList<Worker> availableLifeguards = filterByTitle(availableWorkers, "Lifeguard");
        ArrayList<Worker> availablePAs = filterByTitle(availableWorkers, "Pool Attendant");

        for(Pool pool : pools){
            ArrayList<Worker> poolManagers = filterByPool(pool, availableManagers);
            ArrayList<Worker> poolLifeguards = filterByPool(pool, availableLifeguards);
            ArrayList<Worker> poolPAs = filterByPool(pool, availablePAs);

            if(poolPAs.size() == 0){
                Worker pa1 = getLowest(availablePAs);
                availablePAs.remove(pa1);
                Worker pa2 = getLowest(availablePAs);
                availablePAs.remove(pa2);

                pool.addWorker(date, pa1);
                pool.addWorker(date, pa2);

            } else if(poolPAs.size() == 1){
                Worker pa1 = getLowest(poolPAs);
                poolPAs.remove(pa1);
                availablePAs.remove(pa1);

                Worker pa2 = getLowest(availablePAs);
                availablePAs.remove(pa2);

                pool.addWorker(date, pa1);
                pool.addWorker(date, pa2);
            } else{
                Worker pa1 = getLowest(poolPAs);
                poolPAs.remove(pa1);
                availablePAs.remove(pa1);

                Worker pa2 = getLowest(poolPAs);
                poolPAs.remove(pa2);
                availablePAs.remove(pa2);

                pool.addWorker(date, pa1);
                pool.addWorker(date, pa2);
            }

            if(poolManagers.size() == 0){
                Worker manager1 = getLowest(availableManagers);
                availableManagers.remove(manager1);

                Worker manager2 = getLowest(availableManagers);
                availableManagers.remove(manager2);

                pool.addWorker(date, manager1);
                pool.addWorker(date, manager2);
            } else if(poolManagers.size() == 1){
                Worker manager1 = getLowest(poolManagers);
                poolManagers.remove(manager1);
                availableManagers.remove(manager1);

                Worker manager2 = getLowest(availableManagers);
                availableManagers.remove(manager2);

                pool.addWorker(date, manager1);
                pool.addWorker(date, manager2);
            } else{
                Worker manager1 = getLowest(poolManagers);
                poolManagers.remove(manager1);
                availableManagers.remove(manager1);

                Worker manager2 = getLowest(poolManagers);
                poolManagers.remove(manager2);
                availableManagers.remove(manager2);

                pool.addWorker(date, manager1);
                pool.addWorker(date, manager2);
            }

            if(poolLifeguards.size() < pool.getNumChairs() + 3){
                for(Worker lifeguard : poolLifeguards){
                    pool.addWorker(date, lifeguard);
                    poolLifeguards.remove(lifeguard);
                    availableLifeguards.remove(lifeguard);
                }
                while(pool.accessDate(date).size() < pool.getNumChairs() + 3){
                    Worker lifeguard = getLowest(availableLifeguards);
                    pool.addWorker(date, lifeguard);
                    availableLifeguards.remove(lifeguard);
                }

            } else if(poolLifeguards.size() == pool.getNumChairs() + 3){
                for(Worker lifeguard : poolLifeguards){
                    pool.addWorker(date, lifeguard);
                    poolLifeguards.remove(lifeguard);
                    availableLifeguards.remove(lifeguard);
                }
            } else {
                while(pool.accessDate(date).size() < pool.getNumChairs() + 3){
                    Worker lifeguard = getLowest(poolLifeguards);
                    pool.addWorker(date, lifeguard);
                    poolLifeguards.remove(lifeguard);
                    availableLifeguards.remove(lifeguard);
                }
            }




        }
        //TODO Check if it is a Saturday and reset numHoursWorked
        //TODO implement logic for payment and calculating number hours worked
        //TODO what if there is not enough workers? create exception
    }

    //TODO For full schedule, remember to create dateMap for each pool




}
