package com.mylib;

import java.util.*;

public class MenuCheckOut {

     private final String stockName;
     private final Map<SharesMarket, Integer>list;

    public MenuCheckOut(String stockName) {
        this.stockName = stockName;
        this.list = new TreeMap<>();
    }

public int addSharesToCheckOut(SharesMarket shareItem, int stockQty) {
        if ((shareItem != null)&&(stockQty>0)){
            int inCheckout = list.getOrDefault(shareItem,0);
            list.put(shareItem, inCheckout + stockQty);
            return inCheckout;
        }
        return 0;
}

public Map<SharesMarket, Integer> StockPicksItems(){
        return Collections.unmodifiableMap(list);
}

    @Override
    public String toString() {
       String s = "\n Shooping Stock Picks " + stockName   + " contains"
               + list.size() + ((list.size()==1) ? " stock item ": " stock items")
               + "\n";
       double totalCostOfShares = 0.0;
       for (Map.Entry<SharesMarket,Integer>item : list.entrySet()){
           s =s +  item.getKey() + ". " + item.getValue() + " purchased \n";
           totalCostOfShares += item.getKey().getStockPrice()* item.getValue();
       }
       return s + "Total share of " + totalCostOfShares;
    }
}
