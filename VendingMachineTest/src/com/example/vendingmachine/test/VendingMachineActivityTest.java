
package com.example.vendingmachine.test;

import android.test.AndroidTestCase;

import com.example.vendingmachine.VendingMachineActivity;
import com.example.vendingmachine.VendingMachineActivity.MONEY;

public class VendingMachineActivityTest extends AndroidTestCase {

    private VendingMachineActivity vma;

    @Override
    protected void setUp() throws Exception {
        vma = new VendingMachineActivity();
    }

    public void test_合計金額が0である() throws Exception {
        assertEquals(0, vma.getTotal());
    }

    public void test_10円投入し合計金額が10である() throws Exception {
        vma.insert(MONEY.MONEY_10YEN);
        assertEquals(10, vma.getTotal());
    }

    public void test_30円投入し合計金額が30である() throws Exception {
        vma.insert(MONEY.MONEY_10YEN);
        vma.insert(MONEY.MONEY_10YEN);
        vma.insert(MONEY.MONEY_10YEN);
        assertEquals(30, vma.getTotal());
    }

    public void test_15円投入しおつりが合計金額が10円であること() throws Exception {
        vma.insert(MONEY.MONEY_5YEN);
        vma.insert(MONEY.MONEY_10YEN);
        assertEquals(10, vma.getTotal());
    }
}
