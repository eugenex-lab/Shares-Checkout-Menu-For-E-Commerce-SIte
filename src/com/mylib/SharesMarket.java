package com.mylib;

public class SharesMarket implements  Comparable <SharesMarket> {
    private final String stockName;
    private double stockPrice;
    private int stockQty = 0;


    public SharesMarket(String stockName, double stockPrice) {
        this.stockName = stockName;
        this.stockPrice = stockPrice;
        this.stockQty = 0;
    }

    public SharesMarket(String stockName, double stockPrice, int stockQty) {
        this.stockName = stockName;
        this.stockPrice = stockPrice;
        this.stockQty = stockQty;
    }

    public String getStockName() {
        return stockName;
    }

    public double getStockPrice() {
        return stockPrice;
    }

    public int getStockQty() {
        return stockQty;
    }

    public void setStockPrice(double stockPrice) {
        if (stockPrice > 0.0) {
            this.stockPrice = stockPrice;
        }
    }

    public void adjustStockCount(int stockQty) {
        int newQty = this.getStockQty() + stockQty;
        if (newQty >= 0) {
            this.stockQty = newQty;
        }
    }

    @Override
    public boolean equals(Object obj) {
        System.out.println("Entering SHare DOT equals ");
        if (obj == this) {
            return true;
        }
        if ((obj == null) || ((obj.getClass() != this.getClass()))) {
            return false; // compare if they diff class
        }
        String objectName = ((SharesMarket) obj).getStockName();
        return this.stockName.equals(objectName);
    }

    @Override
    public int hashCode() {
        return this.stockName.hashCode() + 23;
    }

    @Override
    public int compareTo(SharesMarket o) {
        System.out.println("Entering ShareMarket.compareTo");
        if (this == o) {
            return 0;
        }

        if (o != null) {
            return this.stockName.compareTo(o.getStockName());
        }
        throw new NullPointerException();
    }

    @Override
    public String toString() {
        return this.stockName + " : stock price per DOllars is "
                +  this.stockPrice;
    }
}




