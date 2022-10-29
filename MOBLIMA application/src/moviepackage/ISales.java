package moviepackage;
public interface ISales {
	int getSales(int movieID);
	void addSales(int movieID, int sales);
	void getTop5_sales();
	void getTop5_rating();
}