
package com.example.vendingmachine.test;

import android.test.AndroidTestCase;

import com.example.vendingmachine.DrinkManager;
import com.example.vendingmachine.DrinkManager.DRINK;
import com.example.vendingmachine.VendingMachineActivity;
import com.example.vendingmachine.VendingMachineActivity.MONEY;

public class VendingMachineActivityTest extends AndroidTestCase {

    private VendingMachineActivity vma;

    @Override
    protected void setUp() throws Exception {
        vma = new VendingMachineActivity();
        vma.init(getContext());
    }

    public void test_合計金額が0である() throws Exception {
        assertEquals(0, vma.getInsertTotal());
    }

    public void test_10円投入し合計金額が10である() throws Exception {
        vma.insert(MONEY.MONEY_10YEN);
        assertEquals(10, vma.getInsertTotal());
    }

    public void test_30円投入し合計金額が30である() throws Exception {
        vma.insert(MONEY.MONEY_10YEN);
        vma.insert(MONEY.MONEY_10YEN);
        vma.insert(MONEY.MONEY_10YEN);
        assertEquals(30, vma.getInsertTotal());
    }

    public void test_15円投入し合計金額が10円であること() throws Exception {
        vma.insert(MONEY.MONEY_5YEN);
        vma.insert(MONEY.MONEY_10YEN);
        assertEquals(10, vma.getInsertTotal());
    }

    public void test_20円投入し払い戻しすると20円おつりとして返ってくること() throws Exception {
        vma.insert(MONEY.MONEY_10YEN);
        vma.insert(MONEY.MONEY_10YEN);
        assertEquals(20, vma.refund());
    }

    public void test_初期状態で120円の紅茶花伝が5本格納されていること() throws Exception {
        DrinkManager manager = vma.getDrinkManager();

        assertEquals(120, manager.getPrice(manager.getDrinkList(0).get(0)));
        assertEquals("紅茶花伝", manager.getName(manager.getDrinkList(0).get(0)));
        assertEquals(5, manager.getStock(0));
    }

    public void test_120円投入し紅茶花伝が1本購入できること() throws Exception {
        DrinkManager manager = vma.getDrinkManager();
        DRINK drink;

        vma.insert(MONEY.MONEY_100YEN);
        vma.insert(MONEY.MONEY_10YEN);
        vma.insert(MONEY.MONEY_10YEN);
        drink = vma.purchase(0);

        assertEquals(0, vma.getInsertTotal());
        assertEquals(120, vma.getSaleTotal());
        assertEquals(DRINK.KADEN, drink);
        assertEquals(4, manager.getDrinkList(0).size());
    }

    public void test_100円投入し紅茶花伝が購入できないこと() throws Exception {
        DrinkManager manager = vma.getDrinkManager();
        DRINK drink;

        vma.insert(MONEY.MONEY_100YEN);
        drink = vma.purchase(0);

        assertEquals(100, vma.getInsertTotal());
        assertEquals(0, vma.getSaleTotal());
        assertEquals(null, drink);
        assertEquals(5, manager.getDrinkList(0).size());
    }

    public void test_300円投入し紅茶花伝を2本購入しおつりが60円返ってくること() throws Exception {
        DrinkManager manager = vma.getDrinkManager();
        int change;

        vma.insert(MONEY.MONEY_100YEN);
        vma.insert(MONEY.MONEY_100YEN);
        vma.insert(MONEY.MONEY_100YEN);
        vma.purchase(0);
        vma.purchase(0);
        change = vma.refund();

        assertEquals(60, change);
        assertEquals(0, vma.getInsertTotal());
        assertEquals(240, vma.getSaleTotal());
        assertEquals(3, manager.getDrinkList(0).size());
    }

    public void test_五百円を投入して５００円が合計金額になること() throws Exception {
        vma.insert(MONEY.MONEY_500YEN);
        assertEquals(500, vma.getInsertTotal());
    }

    public void test_在庫に午後の紅茶レモンを5本追加できること() throws Exception {
        DrinkManager manager = vma.getDrinkManager();
        manager.store(DRINK.GOGO_LEMON, 1);
        manager.store(DRINK.GOGO_LEMON, 1);
        manager.store(DRINK.GOGO_LEMON, 1);
        manager.store(DRINK.GOGO_LEMON, 1);
        manager.store(DRINK.GOGO_LEMON, 1);

        assertEquals("午後の紅茶レモン", manager.getName(manager.getDrinkList(1).get(0)));
        assertEquals(5, manager.getDrinkList(1).size());
    }

    public void test_在庫におーいお茶を5本追加できること() throws Exception {
        DrinkManager manager = vma.getDrinkManager();
        manager.store(DRINK.OCHA, 2);
        manager.store(DRINK.OCHA, 2);
        manager.store(DRINK.OCHA, 2);
        manager.store(DRINK.OCHA, 2);
        manager.store(DRINK.OCHA, 2);

        assertEquals("お～いお茶", manager.getName(manager.getDrinkList(2).get(0)));
        assertEquals(5, manager.getDrinkList(2).size());
    }
}
