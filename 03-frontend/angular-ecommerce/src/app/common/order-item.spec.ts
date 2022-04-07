import { CartItem } from './cart-item';
import { OrderItem } from './order-item';

let cartItemSpy = jasmine.createSpyObj(CartItem, ['']);

describe('OrderItem', () => {
  it('should create an instance', () => {
    expect(new OrderItem(cartItemSpy)).toBeTruthy();
  });
});
