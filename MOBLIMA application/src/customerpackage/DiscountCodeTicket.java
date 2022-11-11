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
    /**
     * Checks if a given code is the same as the code stored in this object
     * 
     * @param code String
     * @return boolean if the cha
     */
    public boolean checkCode(String code){
        if (this.code.equals(code))
            return true;
        else 
            return false;
    }
    /**
     * Getter for the dicount code
     * 
     * @return String of the code attribute
     */
    public String getCode(){
        return code;
    }
    /**
     * Getter for the multiplier of the discount code
     * 
     * @return float multiplier of amount of discount
     */
    public float getDiscount(){
        return discount;
    }
    /**
     * Setter for the discount code string
     * 
     * @param code New code to update object with
     */
    public void setCode(String code){
        this.code = code;
    }
    /**
     * Setter for the new multiplier to update the amount of discount the code will provide
     * 
     * @param discount float muliplier
     */
    public void setDiscount(float discount) {
        if (discount<0 || discount>1){
            System.out.println("Discount code is not valid");
            return;
        }
        this.discount = discount;
    }
}
