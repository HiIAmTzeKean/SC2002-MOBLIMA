package customerpackage;

import java.io.*;

public class appTest {
    
    /** 
     * @param []
     */
    public static void main(String args []){
        boolean createDat = false;
        if (createDat){
            File file = new File("./MOBLIMA application/data/discountcode/discountcode.dat");
            file.delete();
            try{
                file.createNewFile();
            }
            catch (IOException ex){}
            DiscountCode d = DiscountCode.getInstance();
            d.addDiscountCodeTicket("WelcomeCoupon", 0.5f);
            d.addDiscountCodeTicket("VIP", 0.3f);
            d.addDiscountCodeTicket("Promo22", 0.2f);
            DiscountCode.close();
        }
       else{
        DiscountCode d = DiscountCode.getInstance();
        d.printMulitplier("VIP");
        DiscountCode.close();
       }
    }
}
