import { HttpClientTestingModule } from '@angular/common/http/testing';
import { TestBed } from '@angular/core/testing';

import { CheckoutService } from './checkout.service';

describe('CheckoutService', () => {
  let checkoutService: CheckoutService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule]
    });
    checkoutService = TestBed.inject(CheckoutService);
  });

  it('should be created', () => {
    expect(checkoutService).toBeDefined();
  });
});
