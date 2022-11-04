package customerpackage;

public class CustomerNullException extends Exception {
    public CustomerNullException() {
        super("Customer object passed was null");
    }
}
