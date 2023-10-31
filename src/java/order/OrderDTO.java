/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package order;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author ngtronghao <ngtronghao02@gmail.com>
 */
public class OrderDTO implements Serializable {

    private String orderId;
    private Date orderDate;
    private float total;

    public OrderDTO() {
    }

    public OrderDTO(String id, Date orderDate, float total) {
        this.orderId = id;
        this.orderDate = orderDate;
        this.total = total;
    }

    public String getId() {
        return orderId;
    }

    public void setId(String id) {
        this.orderId = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

}
