package com.milinda.assessment.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "cart_item")
public class CartItem {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @ManyToOne()
    @JoinColumn(name = "cart_id", referencedColumnName = "cart_id", updatable = true, insertable = true, nullable=false)
    Cart cart;

    @ManyToOne()
    @JoinColumn(name = "item_id", referencedColumnName = "id", updatable = true, insertable = true, nullable=false)
    Item item;

    @Column(name = "cartons")
    Integer cartons;

    @Column(name = "singles")
    Integer singles;

    @Column(name = "amount")
    Double amount = 0.0;

    @Column(name = "discount")
    Double discount = 0.0;

    @Column(name = "tot_price")
    Double totPrice = 0.0;

}
