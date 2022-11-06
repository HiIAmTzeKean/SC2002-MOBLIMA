package cinemapackage;

/**
 * Custom seat excepton raised when the seat row input is not valid
 * and seat conversion is not possible
 * @author Ng Tze Kean
 * @since 06-11-2022
 */
public class SeatRowException extends Exception {
    public SeatRowException() {
        super("Invalid seat row input");
    }
}
