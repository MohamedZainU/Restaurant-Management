import java.util.*;
public class Customer{
    String custName;
    String custPlace;
    
    List<ProductBought> custBuy;
    
    public Customer(String custName,String custPlace){
        this.custName = custName;
        this.custPlace = custPlace;
        this.custBuy = new ArrayList<>();
    }
}