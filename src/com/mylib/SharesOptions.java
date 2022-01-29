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
                item.adjustStockCount(inStock.getStockQty()); // add if it exist like mtn stock on mtn stock
            }
            list.put(item.getStockName(),item);
            return item.getStockQty();
        }
        return  0 ;
    }

    public int sellShares (String item , int qty ) {
        SharesMarket inStock = list.getOrDefault(item, null);

        if((inStock != null) && (inStock.getStockQty() >= qty ) && ((qty > 0 ))){
            inStock.adjustStockCount(-qty);
            return qty;
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

            double itemValue  = sharesMarket.getStockPrice() * sharesMarket.getStockQty();

            s = "We have an entry of  " + s + sharesMarket + " : THere  in stock. Vlaue of Item added ";
            s  = s + String.format(" %.2f",itemValue) + "\n";
            totalCost += itemValue;
        }
        return s + " Total stock value " + String.format(" %.6f",totalCost) ;
    }
}
