////////////////////////////////////////////////////////////////////
// [Sonia] [Menon] [1144731]
////////////////////////////////////////////////////////////////////

package it.unipd.tos.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MenuItem {
    public enum ItemType{
        PRIMI_PIATTI, PIZZE
    }

    private ItemType itemType;
    private String name;
    private double price;
}
