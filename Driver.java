// Define a class named Driver
public class Driver {
    // The main method, the entry point of the program
    public static void main(String[] args) {
        // Create a new thread, providing a PageScanner instance with the URL "https://www.google.com"
        Thread t = new Thread(new PageScanner("https://www.google.com"));

        // Start the thread, causing it to execute the run method in PageScanner
        t.start();
    }
}