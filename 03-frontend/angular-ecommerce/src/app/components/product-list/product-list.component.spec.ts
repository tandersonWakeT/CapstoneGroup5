import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute, convertToParamMap } from '@angular/router';
import { from, Observable, of } from 'rxjs';
import { CartService } from 'src/app/services/cart.service';
import { ProductService } from 'src/app/services/product.service';

import { ProductListComponent } from './product-list.component';

describe('ProductListComponent', () => {
  let component: ProductListComponent;
  let fixture: ComponentFixture<ProductListComponent>;

  let routeSpy = jasmine.createSpyObj(ActivatedRoute, ['paramMap.subscribe']);
  let cartSpy = jasmine.createSpyObj(CartService, ['']);
  let productSpy = jasmine.createSpyObj(ProductService, ['']);

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProductListComponent ],
      providers: [
        {provide: ActivatedRoute, useValue: {
          paramMap: of(convertToParamMap([{keyword: "gam"}]))
          },
        },
        {provide: CartService, useValue: cartSpy},
        {provide: ProductService, useValue: productSpy}
      ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ProductListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
