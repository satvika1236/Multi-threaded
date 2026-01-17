package multiThreading;

class ReadingRoom {
    private int totalSeats;
    private int occupiedSeats = 0;

    ReadingRoom(int totalSeats) {
        this.totalSeats = totalSeats;
    }


    public synchronized void enter(String studentName) {
        while (occupiedSeats == totalSeats) {
            System.out.println(studentName + " waiting for a seat.");
            try {
                wait(); // wait until a seat becomes free
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        occupiedSeats++;
        System.out.println(studentName + " entered the reading room. (Occupied: " + occupiedSeats + ")");
    }

    public synchronized void leave(String studentName) {
        occupiedSeats--;
        System.out.println(studentName + " left the reading room. (Occupied: " + occupiedSeats + ")");
        notifyAll();
    }
}

class Student extends Thread {
    private String studentName;
    private ReadingRoom room;

    Student(String studentName, ReadingRoom room) {
        this.studentName = studentName;
        this.room = room;
    }

    @Override
    public void run() {
        room.enter(studentName);

        try {
            // Simulate reading time
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        room.leave(studentName);
    }
}


public class LibraryReadingRoom {
    public static void main(String[] args) {


        ReadingRoom room = new ReadingRoom(2);


        Student s1 = new Student("S1", room);
        Student s2 = new Student("S2", room);
        Student s3 = new Student("S3", room);

        s1.start();
        s2.start();
        s3.start();
    }
}
