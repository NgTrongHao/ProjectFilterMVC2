/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cart;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author ngtronghao <ngtronghao02@gmail.com>
 */
public class CartObject implements Serializable {

    Map<String, Integer> items;

    public CartObject() {
    }

    public Map<String, Integer> getItems() {
        return items;
    }

    public void addItemsToCart(String itemID, int quantity) {
        //1. check adding item existed
        if (itemID == null) {
            return;
        }
        if (itemID.trim().isEmpty()) {
            return;
        }
        if (quantity <= 0) {
            return;
        }
        //2. check items existed
        if (this.items == null) {
            this.items = new HashMap<>();
        }
        //3. check adding item existed in items
        if (this.items.containsKey(itemID)) {
            quantity = quantity + this.items.get(itemID);
        }
        //4. put item to items
        this.items.put(itemID, quantity);
    }

    public void deleteItemsFromCart(String itemID) {
        //1. check item existed
        if (itemID == null) {
            return;
        }
        if (itemID.trim().isEmpty()) {
            return;
        }
        //2. check items existed
        if (this.items == null) {
            return;
        }
        //3. check item existed in items
        if (this.items.containsKey(itemID)) {
            this.items.remove(itemID);
            if (this.items.isEmpty()) {
                this.items = null;
            }
        }
    }
}
