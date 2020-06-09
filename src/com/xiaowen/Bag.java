package com.xiaowen;

import Porp.PotionSet;

public class Bag {
    PotionSet potions=new PotionSet();

    private static volatile Bag instance=null;

    private Bag(){}

    public static Bag getInstance(){
        if(instance==null){
            synchronized (Bag.class){
                if(instance==null){
                    instance=new Bag();
                }
            }
        }
        return instance;
    }



    public PotionSet getPotions() {
        return potions;
    }

    public void setPotions(PotionSet potions) {
        this.potions = potions;
    }
}

