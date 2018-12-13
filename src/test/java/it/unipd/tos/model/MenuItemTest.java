package it.unipd.tos.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class MenuItemTest {

    @Test
    public void getName() {
        MenuItem menuItem = new MenuItem(MenuItem.ItemType.PIZZE, "Pizza Margherita", 5);

        assertEquals("Pizza Margherita", menuItem.getName());
    }
}