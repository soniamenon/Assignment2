////////////////////////////////////////////////////////////////////
// [Sonia] [Menon] [1144731]
////////////////////////////////////////////////////////////////////

package it.unipd.tos.business;
import java.util.List;
import it.unipd.tos.business.exception.RestaurantBillException;
import it.unipd.tos.model.MenuItem;

public interface RestaurantBill {
    double getOrderPrice(List<MenuItem> itemsOrdered) throws RestaurantBillException;
}