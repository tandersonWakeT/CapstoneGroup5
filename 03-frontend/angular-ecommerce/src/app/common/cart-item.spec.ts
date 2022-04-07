import { TestBed } from '@angular/core/testing';
import { CartItem } from './cart-item';
import { Product } from './product';

describe('CartItem', () => {

  let productSpy = jasmine.createSpyObj(Product, ['']);

  it('should create an instance', () => {

    expect(new CartItem(productSpy)).toBeDefined();
  });
});
