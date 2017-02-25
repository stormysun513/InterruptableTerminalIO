import java.io.*;
import java.util.*;

class Demo {


    public static void main(String[] args)
    {
        Timer timer = new Timer();

        class TestTimer extends TimerTask {

            Thread target = null;

            TestTimer(Thread t){
                this.target = t;
            }

            public void run(){
                System.out.println("Timer is up!");
                target.interrupt();
                timer.cancel();
            }
        }

        InterruptableTermIO termio = new InterruptableTermIO();   
        TestTimer testTimer = new TestTimer(Thread.currentThread());

        timer.schedule(testTimer, 5000);

        while(true)
        {
            String input = null;
            System.out.println("\n\nEnter a string");
            System.out.print(">> ");
            try {
                input = termio.keyboardReadString();
            }
            catch (InterruptedException e){
                e.printStackTrace();
                break;
            }
            System.out.println("Your input is \"" + input + "\"");
        }

        System.out.println("Main thread is interrupted.");
    }
}
