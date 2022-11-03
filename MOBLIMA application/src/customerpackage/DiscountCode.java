package customerpackage;

import java.io.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
public class DiscountCode{
    private static ArrayList<DiscountCodeTicket> discountCodes;
    private static DiscountCode discountCode;
    private DiscountCode(){}
    public static DiscountCode getInstance(){
        if (discountCode == null){
			DiscountCode.deseraliseDiscountCode("./MOBLIMA application/data/discountcode/discountcode.dat");
			DiscountCode.discountCode = new DiscountCode();
			return DiscountCode.discountCode;
		}
		return DiscountCode.discountCode;
    }
    public static void close(){
		if (discountCode != null){
			seraliseDiscountCode("./MOBLIMA application/data/discountcode/discountcode.dat",DiscountCode.discountCodes);
			discountCode = null;
		}
    }
    private static void deseraliseDiscountCode(String filename){
        ArrayList<DiscountCodeTicket>  c = null;
		try {
			FileInputStream fileIn = new FileInputStream(filename);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			c = (ArrayList<DiscountCodeTicket>) in.readObject();
			in.close();
		} 
		catch (IOException i) {
			// empty binary file
			// or file not found
			DiscountCode.discountCodes = new ArrayList<DiscountCodeTicket>();
			return;
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		DiscountCode.discountCodes = c;
    }
    private static void seraliseDiscountCode(String filename, ArrayList<DiscountCodeTicket> c) {
		try{
			FileOutputStream fos = new FileOutputStream(filename);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(c);
			oos.close();
		}
		catch (IOException i) {
			i.printStackTrace();
		}
	}
	public void addDiscountCodeTicket(String code, float discount){
		discountCodes.add(new DiscountCodeTicket(code, discount));
	}
	public boolean checkValid(String code){
		for (Iterator<DiscountCodeTicket> it = discountCodes.iterator(); it.hasNext();) {
			if(it.next().getCode() == code)
				return true;
		}
		return false;
	}
	public float getMultiplier(String code) throws IllegalArgumentException{
		for (Iterator<DiscountCodeTicket> it = discountCodes.iterator(); it.hasNext();) {
			DiscountCodeTicket c = it.next();
			if(c.checkCode(code))
				return c.getDiscount();
		}
		// Not found
		throw new IllegalArgumentException("Invalid discount code provided");	
	}
	public void printMulitplier(String code){
		float discount = getMultiplier(code);
		System.out.printf("Discount given is: %.2f%%", discount*100);
	}
}
