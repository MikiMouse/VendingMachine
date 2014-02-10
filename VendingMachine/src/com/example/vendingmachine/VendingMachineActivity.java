
package com.example.vendingmachine;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.vendingmachine.DrinkManager.DRINK;

public class VendingMachineActivity extends Activity {

    /* View */
    private ImageView[] mDrinkView;

    private ImageView mGrass;

    // モード
    private boolean mIsUserMode = true;

    // 投入合計金額
    private int mInsertTotal;

    // 売上合計金額
    private int mSaleTotal;

    private DrinkManager mDrinkManager;

    public enum MONEY {
        MONEY_1YEN(1), MONEY_5YEN(5), MONEY_10YEN(10), MONEY_50YEN(50), MONEY_100YEN(100),
        MONEY_500YEN(500), MONEY_1000YEN(1000), MONEY_2000YEN(2000), MONEY_5000YEN(5000),
        MONEY_10000YEN(10000);

        private int value;

        private MONEY(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vending_machine);

        mDrinkView = new ImageView[DrinkManager.DRINK_LIST_COUNT]; // 3
        mDrinkView[0] = (ImageView) findViewById(R.id.drink_list_0);
        mDrinkView[1] = (ImageView) findViewById(R.id.drink_list_1);
        mDrinkView[2] = (ImageView) findViewById(R.id.drink_list_2);
        mGrass = (ImageView) findViewById(R.id.grass);

        init(this);
    }

    // TODO public にしたくないけど、そうしないとテストできない
    public void init(Context context) {
        mInsertTotal = 0;
        mSaleTotal = 0;
        mDrinkManager = new DrinkManager(context);
        // 初期状態で紅茶花伝を5本格納している
        for (int i = 0; i < 5; i++) {
            store(DRINK.KADEN, 0);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case R.id.action_switch:
            switchMode();
            break;

        default:
            break;
        }
        return super.onOptionsItemSelected(item);
    }

    public int getInsertTotal() {
        return mInsertTotal;
    }

    public int getSaleTotal() {
        return mSaleTotal;
    }

    public DrinkManager getDrinkManager() {
        return mDrinkManager;
    }

    /* 1円玉、5円玉、千円札以外のお札は扱えない */
    public void insert(MONEY money) {
        if (MONEY.MONEY_10YEN.equals(money) || MONEY.MONEY_50YEN.equals(money)
                || MONEY.MONEY_100YEN.equals(money) || MONEY.MONEY_500YEN.equals(money)
                || MONEY.MONEY_1000YEN.equals(money)) {
            mInsertTotal += money.getValue();
        }
    }

    public int refund() {
        int change = mInsertTotal;
        mInsertTotal = 0;
        return change;
    }

    public DRINK purchase(int position) {
        ArrayList<DRINK> drinkList = mDrinkManager.getDrinkList(position);
        if (drinkList.size() > 0) {
            DRINK drink = drinkList.get(0);
            if (mDrinkManager.getPrice(drink) <= mInsertTotal) {
                mDrinkManager.getDrinkList(position).remove(0);
                mInsertTotal -= mDrinkManager.getPrice(drink);
                mSaleTotal += mDrinkManager.getPrice(drink);
                return drink;
            }
        }
        return null;
    }

    public void store(DRINK drink, int position) {
        if (mDrinkManager.getDrinkList(position).size() == 0) {
            mDrinkView[position].setImageResource(mDrinkManager.getThumbnailId(drink));
        }
        mDrinkManager.store(drink, position);
    }

    private void switchMode() {
        mIsUserMode = !mIsUserMode;
        mGrass.setVisibility(mIsUserMode ? View.VISIBLE : View.INVISIBLE);
        for (int i = 0; i < mDrinkView.length; i++) {
            mDrinkView[i].setEnabled(!mIsUserMode);
        }
    }
}
