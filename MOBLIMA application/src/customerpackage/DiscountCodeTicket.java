package customerpackage;

import java.io.Serializable;

/**
 * DiscountCodeTicket class stores an instance of a valid discount code
 * to be used in customer view class to check if the code provided in I/O
 * is valid
 * @author Ng Tze Kean
 * @since 06-11-2022
 */
public class DiscountCodeTicket implements Serializable {
    private String code;
    private float discount;
    public DiscountCodeTicket(String code, float discount){
        this.code = code;
        this.discount = discount;
    }
    public boolean checkCode(String code){
        if (this.code.equals(code))
            return true;
        else 
            return false;
    }
    public String getCode(){
        return code;
    }
    public float getDiscount(){
        return discount;
    }
    public void setCode(String code){
        this.code = code;
    }
    public void setDiscount(float discount) {
        if (discount<0 || discount>1){
            System.out.println("Discount code is not valid");
            return;
        }
        this.discount = discount;
    }
}
