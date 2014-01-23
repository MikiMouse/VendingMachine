
package com.example.vendingmachine;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

public class VendingMachineActivity extends Activity {

    private int total;

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
        setContentView(R.layout.activity_main);
        total = 0;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public int getTotal() {
        return total;
    }

    /* 1円玉、5円玉、千円札以外のお札は扱えない */
    public void insert(MONEY money) {
        if (MONEY.MONEY_10YEN.equals(money) || MONEY.MONEY_50YEN.equals(money)
                || MONEY.MONEY_100YEN.equals(money) || MONEY.MONEY_500YEN.equals(money)
                || MONEY.MONEY_1000YEN.equals(money)) {
            total += money.getValue();
        }
    }
}
