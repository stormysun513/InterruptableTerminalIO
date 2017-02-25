/* *  
 * InterruptableTermIO.java - a wrapper class for allowing interrupt I/O  
 *
 * @author  Yu-Lun Tsai
 * @version 1.0 
 * @since   2017-02-22
 */ 
import java.io.*;

public class InterruptableTermIO
{
    /* *
     * This method is used to get user input from standard input.
     * It is able to handle interrupt while waiting for users
     * 
     * @param None
     * @return String This returns the input from standard input.
     */
    public String keyboardReadString() throws InterruptedException 
    {
        boolean running = true;
        StringBuilder sb = new StringBuilder();
        BufferedInputStream in = (BufferedInputStream)System.in;

        while(running)
        {
            try 
            {
                if(in.available() > 0)
                {
                    int inChar = in.read();
                    if(inChar == -1 || inChar == '\n')
                    { 
                        running = false;
                    }
                    else
                    {
                        sb.append(Character.toString((char)inChar));
                    }
                }
                else
                {
                    if(Thread.currentThread().isInterrupted())
                    {
                        throw new InterruptedException();
                    }
                    try 
                    {
                        Thread.sleep(500);
                    }
                    catch (InterruptedException e)
                    {
                        throw e;
                    }
                }
            }
            catch (IOException e)
            {
                System.err.println( "Read Error in InterruptableTermIO.keyboardReadString method" );
                running = false;
            }
        }
        return sb.toString();
    }
}
