import java.util.*;
public class Restaurant{
    String restName;
    String restLocation;
    List<Product> productList;
    
    public Restaurant(String restName,String restLocation){
        this.restName = restName;
        this.restLocation = restLocation;
        this.productList = new ArrayList<>();
    }
}