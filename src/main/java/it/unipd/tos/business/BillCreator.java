////////////////////////////////////////////////////////////////////
// [Sonia] [Menon] [1144731]
////////////////////////////////////////////////////////////////////

package it.unipd.tos.business;

import it.unipd.tos.business.exception.RestaurantBillException;
import it.unipd.tos.model.MenuItem;

import java.util.List;

public class BillCreator implements RestaurantBill {
    @Override
    public double getOrderPrice(List<MenuItem> itemsOrdered) throws RestaurantBillException {
        if (itemsOrdered.isEmpty()) {
            return 0;
        }

        return getTotalAmount(itemsOrdered);
    }

    private static double getTotalAmount (List<MenuItem> itemsOrdered) {
        double totalAmount = 0;

        for(MenuItem i : itemsOrdered) {
            totalAmount += i.getPrice();
        }

        return totalAmount;
    }
}
