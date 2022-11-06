package customerpackage;

/**
 * Custom exception to be raised when a null customer object passed into class
 * @author Ng Tze Kean
 * @since 06-11-2022
 */
public class CustomerNullException extends Exception {
    public CustomerNullException() {
        super("Customer object passed was null");
    }
}
