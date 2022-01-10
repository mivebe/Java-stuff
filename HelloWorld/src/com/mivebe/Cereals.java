package com.mivebe;

public enum Cereals {

    CEREAL1(4.50),
    CEREAL2(2.40),
    CEREAL3(3.20),
    CEREAL4(7.80);

    final double price;
    Cereals (double price){
        this.price = price;
    }
}
