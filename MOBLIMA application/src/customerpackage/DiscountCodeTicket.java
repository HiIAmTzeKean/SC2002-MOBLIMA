package customerpackage;

import java.io.Serializable;

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
