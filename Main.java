/******************************************************************************

                            Online Java Compiler.
                Code, Compile, Run and Debug java program online.
Write your code in this editor and press "Run" button to execute it.

*******************************************************************************/

import java.util.*;
public class Main
{
    static Scanner sc = new Scanner(System.in);
    
    static List<Restaurant> restaurantList = new ArrayList<>();
    static HashMap<String,List<Product>> mapRestProducts = new HashMap<>();
    static List<Customer> customerList = new ArrayList<>();
    static HashMap<String,List<ProductBought>> mapCustOrders = new HashMap<>();
    
    
    public static void viewOrder(){
        System.out.println("Enter your Name");
        String custToCheck = sc.next();
        int totalOrderPrice = 0;
        
        boolean isCustCheck = false;
        
        for(Customer c:customerList){
            if(c.custName.equals(custToCheck)){
                isCustCheck = true;
            }
        }
        
        if(isCustCheck){
        
        List<ProductBought> productBought = mapCustOrders.get(custToCheck);
        
        System.out.println("----------------------------------------------------------------------------------------------------------------------");
        
        for(ProductBought p:productBought){
            System.out.println(p.foodBought+" Of Price: "+p.priceBought+" is coming from "+p.restBought+" will reach you within 10 minutes ");
            totalOrderPrice = p.totalPrice;
        }
        
        System.out.println("Total Price "+totalOrderPrice);
        
        System.out.println("----------------------------------------------------------------------------------------------------------------------");
        
        }else{
            System.out.println("Customer Not Available");
        }
    }
    
    
    
    public static void buy(Customer c){
        System.out.println("Enter Restaurant Name");
        String restBought = sc.next();
        System.out.println("Enter Food Name");
        String foodBought = sc.next();
        System.out.println("Enter Quantity");
        int quantBought = sc.nextInt();
        int priceBought=0;
        
        boolean isInBuy = false;
        boolean ischeckRest = false;
        boolean isFoodCheck = false;
        boolean isFoodQuant = false;
        
        
        for(Restaurant r:restaurantList){
            if(r.restName.equals(restBought)){
                ischeckRest = true;
                for(Product p:r.productList){
                    if(p.foodName.equals(foodBought)){
                        
                        isFoodCheck = true;
                        
                        if(p.quantity>=quantBought){
                            
                            isFoodQuant = true;
                            
                            isInBuy = true;
                            priceBought = p.price * quantBought;
                            
                        }
                        
                    }
                }
            }
        }
        
        if(!ischeckRest){
            System.out.println("Restaurant of that name not Available !");
            return;
        }else if(!isFoodCheck){
            System.out.println("Food of that Name not Available !");
            return;
        }else if(!isFoodQuant){
            System.out.println("We've run out of food !");
            return;
        }
        
        
        if(isInBuy){
        ProductBought pb = new ProductBought(restBought,foodBought,quantBought,priceBought);
        c.custBuy.add(pb);
        mapCustOrders.put(c.custName,c.custBuy);
        }
    }
    
    
    public static void search(Customer c,String custPlace){
        System.out.println("1-> Search Restaurant By Loaction NearBy");
        System.out.println("2-> See All Restaurants");
        
        int choiceRest = sc.nextInt();
        
        if(choiceRest==1){
        
        boolean isIn2 = false;
        
        for(Restaurant r:restaurantList){
            if(r.restLocation.equals(custPlace)){
                isIn2 = true;
                
            List<Product> productNearLoaction = mapRestProducts.get(r.restName);
            
            System.out.println("-----------------------------");
            
            System.out.println("Restaurant Name "+r.restName);
            
            System.out.println("-----------------------------");
                
            
            for(Product p:productNearLoaction){
                
            
            
            System.out.println("-----------------------------");
            
            System.out.println("|   "+"Food Name "+p.foodName+"     |");
            System.out.println("|   "+"Price "+p.price+"            |");
            
            System.out.println("-----------------------------");
            
            }
            
            buy(c); 
                
            }
        }
        
        
        if(!isIn2){
            System.out.println("No Available Restaurant near your Location");
        }
        
        }else if(choiceRest==2){
        
        
        for(Map.Entry<String,List<Product>> res:mapRestProducts.entrySet()){
            String restName = res.getKey();
            List<Product> product = res.getValue();
            
            
            System.out.println("-----------------------------");
            
            System.out.println("Restaurant Name "+restName);
            
            System.out.println("-----------------------------");
            
            for(Product p:product){
            
            System.out.println("-----------------------------");
            
            System.out.println("|   "+"Food Name "+p.foodName+"     |");
            System.out.println("|   "+"Price "+p.price+"            |");
            
            System.out.println("-----------------------------");
            
            }
            
        }
        
        buy(c);
        
        
        }
    }
    
    
    public static void Customer(){
        System.out.println("1-> Start Ordering...");
        System.out.println("2-> View Order");
        int choiceOrder = sc.nextInt();
        
        if(choiceOrder==1){
        System.out.println("Enter your Name");
        String custName = sc.next();
        System.out.println("Enter your Location");
        String custPlace = sc.next();
        
        boolean isInCust = false;
        
        
        for(Customer c:customerList){
            if(c.custName.equals(custName)){
                isInCust = true;
                search(c,custPlace);
            }
        }
        
        if(!isInCust){
        Customer c1 = new Customer(custName,custPlace);
        customerList.add(c1);
        search(c1,custPlace);
        }
        
        }else if(choiceOrder==2){
            viewOrder();
        }
        
        
        
        
        
        
    }
    
    public static void Restaurant(){
        System.out.println("Enter Restaurant Name");
        String restName = sc.next();
        
        
        boolean Isin = false;
        
        
        for(Restaurant r:restaurantList){
            if(r.restName.equals(restName)){
                Isin = true;
                System.out.println("Enter Food Name");
                String foodNameE = sc.next();
                System.out.println("Enter Price per quantity");
                int priceE = sc.nextInt();
                System.out.println("Enter Quantity Available");
                int quantityE = sc.nextInt();
        
                Product p = new Product(foodNameE,priceE,quantityE);
                r.productList.add(p);
                mapRestProducts.put(restName,r.productList);
            }
        }
        
        if(!Isin){
        System.out.println("Enter Location");
        String restLocation = sc.next();
        Restaurant r1 = new Restaurant(restName,restLocation);
        restaurantList.add(r1);
        
        System.out.println("Enter Food Name");
        String foodName = sc.next();
        System.out.println("Enter Price per quantity");
        int price = sc.nextInt();
        System.out.println("Enter Quantity Available");
        int quantity = sc.nextInt();
        
        Product p = new Product(foodName,price,quantity);
        r1.productList.add(p);
        mapRestProducts.put(restName,r1.productList);
        }
    }
    
    
	public static void main(String[] args) {
		boolean isTrue = true;
		
		while(isTrue){
		    System.out.println("1-> Restaurant");
		    System.out.println("2-> Customer");
		    int choiceOfInit = sc.nextInt();
		    if(choiceOfInit==1){
		        Restaurant();
		    }else if(choiceOfInit==2){
		        Customer();
		    }
		}
	}
}
