package multiThreading;

class Runway {

    public synchronized void useRunway(String planeId) {
        System.out.println(planeId + " requesting permission to take off.");
        System.out.println(planeId + " taking off...");

        try {

            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(planeId + " has successfully taken off.\n");
    }
}


class Plane extends Thread {
    private String planeId;
    private Runway runway;

    Plane(String planeId, Runway runway) {
        this.planeId = planeId;
        this.runway = runway;
    }

    @Override
    public void run() {
        runway.useRunway(planeId);
    }
}


public class AirportRunwaySimulation {
    public static void main(String[] args) {


        Runway runway = new Runway();


        Plane p1 = new Plane("AI101", runway);
        Plane p2 = new Plane("BA202", runway);
        Plane p3 = new Plane("EK303", runway);

        p1.start();
        p2.start();
        p3.start();
    }
}
