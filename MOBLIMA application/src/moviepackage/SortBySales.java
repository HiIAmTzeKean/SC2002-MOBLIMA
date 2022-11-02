package moviepackage;
import java.util.Comparator;
class SortBySales implements Comparator<Movie>{
    @Override
    public int compare(Movie a, Movie b){
        //using the integer static method as the "substraction trick"
        //b.getSales()-a.getSales() is prone to integer overflow.
        return Integer.compare(b.getSales(), a.getSales());
    }
}