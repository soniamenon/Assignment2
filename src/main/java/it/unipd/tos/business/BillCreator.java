////////////////////////////////////////////////////////////////////
// [Sonia] [Menon] [1144731]
////////////////////////////////////////////////////////////////////

package it.unipd.tos.business;

import it.unipd.tos.business.exception.RestaurantBillException;
import it.unipd.tos.model.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class BillCreator implements RestaurantBill {
    @Override
    public double getOrderPrice(List<MenuItem> itemsOrdered) throws RestaurantBillException {
        if (itemsOrdered.isEmpty()) {
            return 0;
        }

        if(itemsOrdered.size()>20) {
            throw new RestaurantBillException();
        }

        return getTotalAmount(itemsOrdered);
    }

    private static double getTotalAmount (List<MenuItem> itemsOrdered) {
        double totalAmount = 0;

        for(MenuItem i : itemsOrdered) {
            totalAmount += i.getPrice();
        }

        totalAmount -= pizzaDiscount(itemsOrdered);
        totalAmount -= generalDiscount(totalAmount);
        return totalAmount;
    }

    private static List<MenuItem> getFilteredItemTypeList (List<MenuItem> itemsOrdered, MenuItem.ItemType itemType) {
        List<MenuItem> filteredList = new ArrayList<>();

        for(MenuItem p : itemsOrdered) {
            if (p.getItemType() == itemType) {
                filteredList.add(p);
            }
        }

        return filteredList;
    }

    private static double getMinPrice (List<MenuItem> itemsOrdered) {
        double minPrice = Integer.MAX_VALUE;

        for (MenuItem p : itemsOrdered) {
            if(p.getPrice()<minPrice) {
                minPrice = p.getPrice();
            }
        }

        return minPrice;
    }

    private static double pizzaDiscount (List<MenuItem> itemsOrdered) {
        List<MenuItem> pizzasOrdered = getFilteredItemTypeList(itemsOrdered, MenuItem.ItemType.PIZZE);

        return pizzasOrdered.size()>10 ? getMinPrice(pizzasOrdered) : 0;
    }

    private static double generalDiscount (double totalAmount) {
        return totalAmount>100 ? totalAmount*5/100 : 0;
    }
}
