////////////////////////////////////////////////////////////////////
// [Sonia] [Menon] [1144731]
////////////////////////////////////////////////////////////////////

package it.unipd.tos.business.exception;

public class RestaurantBillException extends RuntimeException {
    public RestaurantBillException() {
        super("Maximum of items exceeded");
    }
}