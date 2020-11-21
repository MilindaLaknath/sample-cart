import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CartService {

  constructor(private http: HttpClient) { }

  getCart() {
    return this.http.get(environment.backend + environment.path_cart)
  }

  getCartById(cartId) {
    return this.http.get(environment.backend + environment.path_cart + cartId)
  }

  addToCart(cartId, itemId, quantity) {
    return this.http.post(environment.backend + environment.path_cart + cartId,
      {
        item_id: itemId,
        quantity: quantity
      }
    )
  }

  removeFromCart(cartId, itemId) {
    return this.http.delete(environment.backend + environment.path_cart + cartId + itemId)
  }

}
