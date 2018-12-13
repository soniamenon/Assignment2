////////////////////////////////////////////////////////////////////
// [Sonia] [Menon] [1144731]
////////////////////////////////////////////////////////////////////

package it.unipd.tos.business;

import it.unipd.tos.business.exception.RestaurantBillException;
import it.unipd.tos.model.MenuItem;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

        totalAmount -= pizzaDiscount(itemsOrdered);
        totalAmount -= generalDiscount(totalAmount);
        return totalAmount;
    }

    private static List<MenuItem> getFilteredItemList (List<MenuItem> itemsOrdered, MenuItem.ItemType itemType) {
        return itemsOrdered.stream().filter((menuItem -> menuItem.getItemType() == itemType)).collect(Collectors.toList());
    }

    private static double pizzaDiscount (List<MenuItem> itemsOrdered) {
        List<MenuItem> pizzasOrdered = getFilteredItemList(itemsOrdered, MenuItem.ItemType.PIZZE);

        return pizzasOrdered.size()>10 ? pizzasOrdered.stream().min(Comparator.comparing(MenuItem::getPrice)).get().getPrice() : 0;
    }

    private static double generalDiscount (double totalAmount) {
        return totalAmount>100 ? totalAmount*5/100 : 0;
    }
}
