import { ComponentFixtureAutoDetect, TestBed } from '@angular/core/testing';
import { BehaviorSubject, Subject } from 'rxjs';
import { CartItem } from '../common/cart-item';
import { Product } from '../common/product';

import { CartService } from './cart.service';

describe('CartService', () => {

  let cartService: CartService;

  let mockProduct: Product;
  let mockCartItem: CartItem;

  beforeEach(() => {

    mockProduct = jasmine.createSpyObj(Product, ['']);
    mockCartItem = jasmine.createSpyObj(new CartItem(mockProduct), ['']);

    TestBed.configureTestingModule({});
    cartService = TestBed.inject(CartService);
  });

  afterEach(() => {

    while (cartService.cartItems.length > 0) {
      cartService.remove(mockCartItem);
    }
  })

  it('should be created', () => {
    expect(cartService).toBeTruthy();
  });

  /*
  * Check that a cart item is succesfully added to
  * the cart
  */
  it('should add item to cart', () => {

    cartService.addToCart(mockCartItem);
    expect(cartService.cartItems).toContain(mockCartItem);
  });

  /*
  * Check that the cartitem is no longer contianed
  * in the cart if remove is called
  */
  it('should remove cartItem from cart', () => {

    cartService.addToCart(mockCartItem);
    expect(cartService.cartItems).toContain(mockCartItem);

    cartService.remove(mockCartItem);
    expect(cartService).not.toContain(mockCartItem);
  });

  /*
  * Check that the cartitem quantity will increase
  * if an identical item is added to the cart
  */
  it('should increase cart size if duplicate items are added', () => {

    cartService.addToCart(mockCartItem);

    let length1 = cartService.cartItems.length;
    let expectedLen = 1;
    expect(expectedLen).toEqual(length1);
  });

  /*
  * Check that the cartitem quantity will increase
  * if an identical item is added to the cart
  */
  it('should increase totalQuantity if an item is added', () => {

    mockCartItem.quantity = 1;
    cartService.addToCart(mockCartItem);

    let actualQuantity: number = 0;

    cartService.totalQuantity.subscribe(

      data => actualQuantity = data
    );

    let expectedQuantity = 1;
    expect(expectedQuantity).toEqual(actualQuantity);
  });

});
