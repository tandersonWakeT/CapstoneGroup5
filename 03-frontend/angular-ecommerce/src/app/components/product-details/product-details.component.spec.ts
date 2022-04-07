import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute, convertToParamMap } from '@angular/router';
import { of } from 'rxjs';
import { CartService } from 'src/app/services/cart.service';
import { ProductService } from 'src/app/services/product.service';

import { ProductDetailsComponent } from './product-details.component';

describe('ProductDetailsComponent', () => {

  let component: ProductDetailsComponent;
  let fixture: ComponentFixture<ProductDetailsComponent>;

  let cartSpy = jasmine.createSpyObj(CartService, ['']);
  let productSpy = jasmine.createSpyObj(ProductService, ['']);

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProductDetailsComponent ],
      providers: [
        {provide: ActivatedRoute, useValue: {
          paramMap: of(convertToParamMap([{id: 1}]))
          },
        },
        {provide: CartService, useValue: cartSpy},
        {provide: ProductService, useValue: productSpy}
      ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ProductDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
