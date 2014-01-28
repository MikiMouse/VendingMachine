
package com.example.vendingmachine;

import java.util.ArrayList;

import android.content.Context;

public class DrinkManager {

    public enum DRINK {
        KADEN(120, R.string.kaden, 0),
        GOGO_STRAIGHT(110, R.string.gogo_s, 0),
        GOGO_MILK(110, R.string.gogo_m, 0),
        GOGO_LEMON(110, R.string.gogo_l, 0);

        private int price;

        private int nameId;

        private int thumbnailId;

        private DRINK(int price, int nameId, int thumbnailId) {
            this.price = price;
            this.nameId = nameId;
            this.thumbnailId = thumbnailId;
        }

        private int getPrice() {
            return price;
        }

        private int getNameId() {
            return nameId;
        }

        private int getThumbnailId() {

            return thumbnailId;
        }
    }

    private Context mContext;

    private ArrayList<DRINK> mDrinkList;

    public DrinkManager(Context context) {
        mContext = context;
        mDrinkList = new ArrayList<DrinkManager.DRINK>();
    }

    public void store(DRINK drink) {
        mDrinkList.add(drink);
    }

    public int getPrice(DRINK drink) {
        return drink.getPrice();
    }

    public String getName(DRINK drink) {
        return mContext.getString(drink.getNameId());
    }

    public ArrayList<DRINK> getDrinkList() {
        return mDrinkList;
    }

    public Object getStock(DRINK drink) {
        return mDrinkList.size();
    }
}
