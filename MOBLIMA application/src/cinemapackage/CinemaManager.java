package cinemapackage;

import java.util.ArrayList;
import java.io.*;

public class CinemaManager implements ICinema {

	private ArrayList<Cinema> cinemas;

	public CinemaManager() {
		// Deseralise all objects here
		cinemas = new ArrayList<Cinema>();

		try {
			FileInputStream fileIn = new FileInputStream("/data/cinema%d.dat");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			cinemas.add((Cinema) in.readObject());
			in.close();
			fileIn.close();

		 } 
		 catch (IOException i) {
			i.printStackTrace();
			return;
		 } 
		 catch (ClassNotFoundException c) {
			System.out.println("Employee class not found");
			c.printStackTrace();
			return;
		 }
	}

	public void seraliseCinemas() {
		try{
			FileOutputStream fos = new FileOutputStream(fileName);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(obj);
	
			fos.close();
		}
		catch (IOException i) {
			i.printStackTrace();
			return;
		}
	}

	@Override
	public void createCinema(String name, int id, String type) {
		
	}

	@Override
	public void deleteCinema(int id) {
		
	}
}