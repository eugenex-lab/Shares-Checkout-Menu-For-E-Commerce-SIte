package com.mylib;

import java.util.Map;

public class StockMain {
    private static SharesOptions sharesOptions = new SharesOptions();

    public static void main(String[] args) {
        SharesMarket temp = new SharesMarket("fb",18.2,3000);
        sharesOptions.addStock(temp);

        temp = new SharesMarket("Cabury",12.2,3000);
        sharesOptions.addStock(temp);

        temp = new SharesMarket("Texaco",12.2,3000);
        sharesOptions.addStock(temp);

        temp = new SharesMarket("JackAndJones",8.2,60000);
        sharesOptions.addStock(temp);

        temp = new SharesMarket("Halogen",3.2,400050);
        sharesOptions.addStock(temp);

        temp = new SharesMarket("biggs",14.2,3000);
        sharesOptions.addStock(temp);

        temp = new SharesMarket("nestle",11.2,20000);
        sharesOptions.addStock(temp);

        System.out.println(sharesOptions);

        for(String s: sharesOptions.Items().keySet()){
            System.out.println(s);
        }

        MenuCheckOut eugeneMenu = new MenuCheckOut("mtn");
        sellItems(eugeneMenu, "mtn",12);
        System.out.println(eugeneMenu);

        sellItems( eugeneMenu ,"bigs",6);
        System.out.println(eugeneMenu);

        if(sellItems(eugeneMenu, "nestle", 4009)!=2){
            System.out.println("no nestle in stock ");
        }

        sellItems(eugeneMenu,"Texaco",34);
        System.out.println(eugeneMenu);

        sellItems(eugeneMenu,"JackAndJones",32);
        sellItems(eugeneMenu,"bigs",12);
        sellItems(eugeneMenu,"mtn",52);
        System.out.println(eugeneMenu);

        System.out.println("$$$$$$"+sharesOptions+"$$$$$$");

        sharesOptions.Items().get("bigs").adjustStockCount(320000000);
        sharesOptions.get("bigs").adjustStockCount(-23);
        System.out.println("$$$$$$"+sharesOptions+"$$$$$$");
        for (Map.Entry<String, Double> stockPrice:sharesOptions.PriceOPtion().entrySet()){
            System.out.println( "product select has a " + stockPrice.getKey() + " cost " +
                    stockPrice.getValue());
            ///start
        }
    }

    public static int sellItems(MenuCheckOut menuCheckOut, String sellItems,int stockQty){
        SharesMarket sharesMarket = sharesOptions.get(sellItems);
        if(sharesMarket==null){
            System.out.println("the stated stock is not in our repo" +
                    "\n or available at this time " + sellItems);
            return 0;
        }
        if(sharesOptions.sellShares(sellItems, stockQty)!=0){
            menuCheckOut.addSharesToCheckOut(sharesMarket,stockQty);
            return stockQty;
        }
        return 0;
    }
}

