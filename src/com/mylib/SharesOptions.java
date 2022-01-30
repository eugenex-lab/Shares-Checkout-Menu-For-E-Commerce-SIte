package com.mylib;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class SharesOptions {

    private final Map<String, SharesMarket> list ;

    public SharesOptions() {
        this.list = new LinkedHashMap<>();
    }

    public int addStock(SharesMarket item){
        if(item != null){
            SharesMarket inStock = list.getOrDefault(item.getStockName(), item );

            if(inStock != item){
                item.adjustStockCount(inStock.sharesQTyAvailable()); // add if it exist like mtn stock on mtn stock
            }
            list.put(item.getStockName(),item);
            return item.sharesQTyAvailable();
        }
        return  0 ;
    }

    public int sellShares (String item , int qty ) {
        SharesMarket inStock = list.getOrDefault(item, null);

        if((inStock != null) && (qty >0)){
            return inStock.finalStockCount(qty);
        }
        return 0;

//        if((inStock != null) && (inStock.sharesQTyAvailable() >= qty ) && ((qty > 0 ))){
//            inStock.adjustStockCount(-qty);
//            return qty;
//        }
//        return 0;
    }

    public int reserveStock(String item, int qty){
        SharesMarket sharesAvailable = list.get(item);

        if((sharesAvailable != null) && (qty >0)){
            return sharesAvailable.reserveShare(qty);
        }
        return 0;
    }

    public int unReserveStock(String item, int qty){
        SharesMarket sharesAvailable = list.get(item);

        if((sharesAvailable != null) && (qty >0)){
            return sharesAvailable.unreservedShares(qty);
        }
        return 0;
    }

    public SharesMarket get(String key){
        return list.get(key);
    }

    public Map<String,Double>PriceOPtion(){
        Map<String,Double> sharePrice = new LinkedHashMap<>();
        for (Map.Entry<String, SharesMarket> item: list.entrySet()){
            sharePrice.put(item.getKey(), item.getValue().getStockPrice());
        }
        return Collections.unmodifiableMap(sharePrice);
    }

    public Map <String, SharesMarket> Items(){
        return Collections.unmodifiableMap(list);
    }

    @Override
    public String toString() {
        String s = "\nStock available List \n";
        double totalCost = 0.0 ;
        for (Map.Entry<String, SharesMarket> item : list.entrySet()){
            SharesMarket sharesMarket = item.getValue();

            double itemValue  = sharesMarket.getStockPrice() * sharesMarket.sharesQTyAvailable();

            s = "We have an entry of  " + s + sharesMarket + " : THere  in stock. Vlaue of Item added ";
            s  = s + String.format(" %.2f",itemValue) + "\n";
            totalCost += itemValue;
        }
        return s + " Total stock value " + String.format(" %.6f",totalCost) ;
    }
}
