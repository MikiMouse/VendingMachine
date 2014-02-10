
package com.example.vendingmachine;

import java.util.ArrayList;

import android.content.Context;

public class DrinkManager {

    public enum DRINK {
        KADEN(120, R.string.kaden, 0),
        GOGO_STRAIGHT(110, R.string.gogo_s, 0),
        GOGO_MILK(110, R.string.gogo_m, 0),
        GOGO_LEMON(110, R.string.gogo_l, 0),
        OCHA(100, R.string.ocha, 0);

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

    public static final int DRINK_LIST_COUNT = 3;

    private Context mContext;

    private ArrayList<DRINK>[] mDrinkLists;

    public DrinkManager(Context context) {
        mContext = context;
        mDrinkLists = new ArrayList[DRINK_LIST_COUNT];
        for (int i = 0; i < DRINK_LIST_COUNT; i++) {
            mDrinkLists[i] = new ArrayList<DrinkManager.DRINK>();
        }
    }

    public void store(DRINK drink, int position) {
        mDrinkLists[position].add(drink);
    }

    public int getPrice(DRINK drink) {
        return drink.getPrice();
    }

    public String getName(DRINK drink) {
        return mContext.getString(drink.getNameId());
    }

    public ArrayList<DRINK> getDrinkList(int position) {
        if (position > DRINK_LIST_COUNT) {
            return null;
        }
        return mDrinkLists[position];
    }

    public int getStock(int position) {
        if (position > DRINK_LIST_COUNT) {
            return 0;
        }
        return mDrinkLists[position].size();
    }
}
