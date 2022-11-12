package moviepackage;
import java.util.Comparator;
class SortByRating implements Comparator<Movie>{
    
    /** 
     * @param a
     * @param b
     * @return int
     */
    @Override
    public int compare(Movie a, Movie b){
        //using the integer static method as the "substraction trick"
        //b.getReviewScores()-a.getReviewScores() is prone to integer overflow.
        return Float.compare(b.getReviewScores(), a.getReviewScores());
    }
}