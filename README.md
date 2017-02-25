A simple Terminal IO wrapper to make it interruptable.

## Usage

```
InterruptableTermIO termio = new InterruptableTermIO();

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
```
