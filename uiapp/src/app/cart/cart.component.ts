import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { ItemService } from '../item.service';
import { CartService } from '../cart.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.scss']
})
export class CartComponent implements OnInit {

  sub;
  itemId;
  cartId: any;
  item;
  cart;

  quantity: number = 0;
  totalVal;

  constructor(
    private _Activatedroute: ActivatedRoute,
    private _router: Router,
    private _itemService: ItemService,
    private _cartService: CartService
  ) {

  }

  ngOnInit() {

    this.getItem()

    this.getCart()

  }

  ngOnDestroy() {
    this.sub.unsubscribe();
  }

  getCart() {
    //Get Cart from backend
    this.cartId = localStorage.getItem('cartId');
    if (this.cartId !== 'undefined' && this.cartId !== null) {
      this._cartService.getCartById(this.cartId).subscribe(
        data => {
          this.cart = data;
        }
      );
    } else {
      this._cartService.getCart().subscribe(
        data => {
          this.cart = data;
          localStorage.setItem('cartId', this.cart.cart_id)
        }
      );
    }
  }

  getItem() {
    this.sub = this._Activatedroute.paramMap.subscribe(params => {
      this.itemId = params.get('id');
    });

    // Search an Item
    if (this.itemId != undefined && this.itemId != null) {
      this._itemService.getItem(this.itemId).subscribe(
        data => {
          this.item = data;
        }
      );
    }
  }


  // Add item to the cart
  addToCart(){
    this._cartService.addToCart(this.cartId,this.itemId,this.quantity).subscribe(
      data =>{
        this.cart = data;
      }
    )
  }

  // remove item from the cart
  removeItem(cartItemId:number){
    console.log(cartItemId)
    this._cartService.removeFromCart(this.cartId,cartItemId).subscribe(
      data =>{
        this.cart = data;
        console.log(this.cart)
      }
    )
  }

  getTotal(){
    this._itemService.getItemTotal(this.itemId, this.quantity).subscribe(
      data => {
        this.totalVal = data;
      }
    );
  }

}
