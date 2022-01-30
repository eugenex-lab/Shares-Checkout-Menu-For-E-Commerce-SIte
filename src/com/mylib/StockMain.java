package com.mylib;

import java.util.Map;

public class StockMain {
    private static SharesOptions sharesOptions = new SharesOptions();

    public static void main(String[] args) {
        SharesMarket temp = new SharesMarket("fb",18.2,10);
        sharesOptions.addStock(temp);

        temp = new SharesMarket("Cabury",12.2,10);
        sharesOptions.addStock(temp);

        temp = new SharesMarket("Texaco",12.2,10);
        sharesOptions.addStock(temp);

        temp = new SharesMarket("JackAndJones",8.2,10);
        sharesOptions.addStock(temp);

        temp = new SharesMarket("Halogen",3.2,10);
        sharesOptions.addStock(temp);

        temp = new SharesMarket("tesla",3.2,10);
        sharesOptions.addStock(temp);

        temp = new SharesMarket("biggs",14.2,10);
        sharesOptions.addStock(temp);

        temp = new SharesMarket("jendolShares",14.2,10);
        sharesOptions.addStock(temp);

        temp = new SharesMarket("guiness",14.2,10);
        sharesOptions.addStock(temp);

        temp = new SharesMarket("nestle",11.2,10);
        sharesOptions.addStock(temp);

        System.out.println("###############################"+sharesOptions+"####################################");

        for(String s: sharesOptions.Items().keySet()){
            System.out.println(s);
        }

        MenuCheckOut eugeneMenu = new MenuCheckOut("eugene");
        sellItems(eugeneMenu, "mtn",8);
//        System.out.println(eugeneMenu);

        sellItems( eugeneMenu ,"bigs",6);
//        System.out.println(eugeneMenu);

        if(sellItems(eugeneMenu, "nestle", 9)!=1){
            System.out.println(  " no nestle in stock ");
        }

        sellItems(eugeneMenu,"Texaco",9);
//        System.out.println(eugeneMenu);

        sellItems(eugeneMenu,"JackAndJones",10);
        sellItems(eugeneMenu,"bigs",2);
        sellItems(eugeneMenu,"mtn",1);
        System.out.println(eugeneMenu);

        MenuCheckOut menuCheckOut = new MenuCheckOut("Xavi");
        sellItems(menuCheckOut, "tesla", 4);
        sellItems(menuCheckOut, "guiness", 4);
        sellItems(menuCheckOut,"jendolShares",5);
        removeShareItems(menuCheckOut, "tesla",10);
        removeShareItems(menuCheckOut, "jensolShares" ,1);
        System.out.println(menuCheckOut);


        removeShareItems(eugeneMenu,"mtn", 1);



        System.out.println("$$$$$$"+sharesOptions+"$$$$$$");

        sharesOptions.Items().get("nestle").adjustStockCount(15);
        sharesOptions.get("bigs").adjustStockCount(15);
        System.out.println("$$$$$$"+sharesOptions+"$$$$$$");
        for (Map.Entry<String, Double> stockPrice:sharesOptions.PriceOPtion().entrySet()){
            System.out.println( stockPrice.getKey() + " costs " +
                    stockPrice.getValue());
        }
    }

    public static int sellItems(MenuCheckOut menuCheckOut, String sellItems,int stockQty){
        SharesMarket sharesMarket = sharesOptions.get(sellItems);
        if(sharesMarket==null){
            System.out.println( sellItems + "Not available at this time " + "at quantity of " + stockQty  + " units");
            return 0;
        }
        if(sharesOptions.reserveStock(sellItems, stockQty)!=0){
//            menuCheckOut.addSharesToCheckOut(sharesMarket,stockQty);
            return menuCheckOut.addSharesToCheckOut(sharesMarket, stockQty);
        }
        return 0;
    }


    public static int removeShareItems(MenuCheckOut menuCheckOut, String sellItems,int stockQty){
        SharesMarket sharesMarket = sharesOptions.get(sellItems);
        if(sharesMarket==null){
            System.out.println("stock not  available at this time " + sellItems);
            return 0;
        }
        if(menuCheckOut.removeShareCheckOut(sharesMarket, stockQty)==stockQty){
            return  sharesOptions.unReserveStock(sellItems,stockQty);
        }
        return 0;
    }

      public static void checkOut(MenuCheckOut menuCheckOut){
        for(Map.Entry<SharesMarket, Integer> itemStock : menuCheckOut.StockPicksItems().entrySet()){
            sharesOptions.sellShares(itemStock.getKey().getStockName(),itemStock.getValue());
        }
        menuCheckOut.clearMenuCheckout();
      }
}

