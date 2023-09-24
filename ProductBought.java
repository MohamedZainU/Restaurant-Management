public class ProductBought{
    String restBought;
    String foodBought;
    int quantBought;
    int priceBought;
    static int totalPrice = 0;
    
    public ProductBought(String restBought,String foodBought,int quantBought,int priceBought){
        this.restBought = restBought;
        this.foodBought = foodBought;
        this.quantBought = quantBought;
        this.priceBought = priceBought;
        totalPrice += priceBought;
    }
}