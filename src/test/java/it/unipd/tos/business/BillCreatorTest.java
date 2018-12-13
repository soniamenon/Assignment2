////////////////////////////////////////////////////////////////////
// [Sonia] [Menon] [1144731]
////////////////////////////////////////////////////////////////////

package it.unipd.tos.business;

import it.unipd.tos.business.exception.RestaurantBillException;
import it.unipd.tos.model.MenuItem;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class BillCreatorTest {

    /**
     * Si deve calcolare uno sconto del 5% se il totale supera i 100E
     */
    @Test
    public void testGeneralDiscountMoreThan100Euros () {
        BillCreator billCreator = new BillCreator();
        List<MenuItem> items = new ArrayList<>();
        items.add(new MenuItem(MenuItem.ItemType.PRIMI_PIATTI, "A01", 30));
        items.add(new MenuItem(MenuItem.ItemType.PIZZE, "B01", 15));
        items.add(new MenuItem(MenuItem.ItemType.PRIMI_PIATTI, "A02", 25));
        items.add(new MenuItem(MenuItem.ItemType.PRIMI_PIATTI, "A03", 30));
        items.add(new MenuItem(MenuItem.ItemType.PRIMI_PIATTI, "B02", 20));

        assertEquals(123.5, billCreator.getOrderPrice(items), 0.1);
    }

    /**
     * Se si superano le 10 pizze nell'ordine, la meno cara e' gratis
     */
    @Test
    public void testPizzaDiscountMoreThan10Pizzas () {
        BillCreator billCreator = new BillCreator();
        List<MenuItem> items = new ArrayList<>();
        items.add(new MenuItem(MenuItem.ItemType.PIZZE, "B03", 6));
        items.add(new MenuItem(MenuItem.ItemType.PIZZE, "B01", 6));
        items.add(new MenuItem(MenuItem.ItemType.PIZZE, "B02", 6));
        items.add(new MenuItem(MenuItem.ItemType.PRIMI_PIATTI, "A01", 8));
        items.add(new MenuItem(MenuItem.ItemType.PIZZE, "B03", 7));
        items.add(new MenuItem(MenuItem.ItemType.PIZZE, "B02", 6));
        items.add(new MenuItem(MenuItem.ItemType.PIZZE, "B03", 6));
        items.add(new MenuItem(MenuItem.ItemType.PRIMI_PIATTI, "A02", 8));
        items.add(new MenuItem(MenuItem.ItemType.PIZZE, "B01", 7));
        items.add(new MenuItem(MenuItem.ItemType.PIZZE, "B02", 8));
        items.add(new MenuItem(MenuItem.ItemType.PIZZE, "B03", 5));
        items.add(new MenuItem(MenuItem.ItemType.PIZZE, "B02", 8));
        items.add(new MenuItem(MenuItem.ItemType.PIZZE, "B03", 6));
        items.add(new MenuItem(MenuItem.ItemType.PRIMI_PIATTI, "B02", 8));

        assertEquals(90, billCreator.getOrderPrice(items), 0.1);
    }

    /**
     * Il totale di elementi ordinati non deve essere maggiore a 20, altrimenti viene lanciata un'eccezione
     */
    @Test(expected = RestaurantBillException.class)
    public void testGetOrderPriceMax20Items () {
        BillCreator billCreator = new BillCreator();
        List<MenuItem> items = new ArrayList<>();
        items.add(new MenuItem(MenuItem.ItemType.PIZZE, "B03", 6));
        items.add(new MenuItem(MenuItem.ItemType.PIZZE, "B01", 6));
        items.add(new MenuItem(MenuItem.ItemType.PIZZE, "B02", 6));
        items.add(new MenuItem(MenuItem.ItemType.PRIMI_PIATTI, "A01", 8));
        items.add(new MenuItem(MenuItem.ItemType.PIZZE, "B03", 7));
        items.add(new MenuItem(MenuItem.ItemType.PIZZE, "B02", 6));
        items.add(new MenuItem(MenuItem.ItemType.PIZZE, "B03", 6));
        items.add(new MenuItem(MenuItem.ItemType.PRIMI_PIATTI, "A02", 8));
        items.add(new MenuItem(MenuItem.ItemType.PIZZE, "B01", 7));
        items.add(new MenuItem(MenuItem.ItemType.PIZZE, "B02", 8));
        items.add(new MenuItem(MenuItem.ItemType.PIZZE, "B03", 5));
        items.add(new MenuItem(MenuItem.ItemType.PIZZE, "B02", 8));
        items.add(new MenuItem(MenuItem.ItemType.PIZZE, "B03", 6));
        items.add(new MenuItem(MenuItem.ItemType.PRIMI_PIATTI, "B02", 8));
        items.add(new MenuItem(MenuItem.ItemType.PIZZE, "B02", 6));
        items.add(new MenuItem(MenuItem.ItemType.PRIMI_PIATTI, "A01", 8));
        items.add(new MenuItem(MenuItem.ItemType.PIZZE, "B03", 7));
        items.add(new MenuItem(MenuItem.ItemType.PIZZE, "B02", 6));
        items.add(new MenuItem(MenuItem.ItemType.PIZZE, "B03", 6));
        items.add(new MenuItem(MenuItem.ItemType.PRIMI_PIATTI, "A02", 8));
        items.add(new MenuItem(MenuItem.ItemType.PIZZE, "B01", 7));
        items.add(new MenuItem(MenuItem.ItemType.PIZZE, "B02", 8));

        assert(items.size()>20);
        billCreator.getOrderPrice(items);
    }

    /**
     * Calcolo del totale dell'ordine come semplice somma (non ci sono situazioni di sconto)
     */
    @Test
    public void testGetOrderPriceBaseSum () {
        BillCreator billCreator = new BillCreator();
        List<MenuItem> items = new ArrayList<>();
        items.add(new MenuItem(MenuItem.ItemType.PIZZE, "B03", 6));
        items.add(new MenuItem(MenuItem.ItemType.PIZZE, "B01", 6));
        items.add(new MenuItem(MenuItem.ItemType.PRIMI_PIATTI, "A02", 14));
        items.add(new MenuItem(MenuItem.ItemType.PIZZE, "B02", 6));
        items.add(new MenuItem(MenuItem.ItemType.PRIMI_PIATTI, "A01", 8));

        assertEquals(40, billCreator.getOrderPrice(items), 0.1);
    }

    /**
     * L'importo totale risulta essere 0 se la lista di ordinazione e' vuota
     */
    @Test
    public void testGetOrderPriceEmptyList () {
        BillCreator billCreator = new BillCreator();
        List<MenuItem> items = new ArrayList<>();

        assertEquals(0, billCreator.getOrderPrice(items), 0);
    }
}