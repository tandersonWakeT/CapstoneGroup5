// import { ComponentFixture, TestBed } from '@angular/core/testing';
// import { FormBuilder } from '@angular/forms';
// import { Router } from '@angular/router';
// import { of } from 'rxjs';
// import { Country } from 'src/app/common/country';
// import { CartService } from 'src/app/services/cart.service';
// import { CheckoutService } from 'src/app/services/checkout.service';
// import { Luv2ShopFormService } from 'src/app/services/luv2-shop-form.service';

// import { CheckoutComponent } from './checkout.component';

// describe('CheckoutComponent', () => {
//   let component: CheckoutComponent;
//   let fixture: ComponentFixture<CheckoutComponent>;

//   let formBuilderSpy = jasmine.createSpyObj(FormBuilder, ['group']);
//   let luv2ShopFormServiceSpy = jasmine.createSpyObj(Luv2ShopFormService, ['getCountries']);
//   let cartServiceSpy = jasmine.createSpyObj(CartService, ['']);
//   let checkoutServiceSpy = jasmine.createSpyObj(CheckoutService, ['']);
//   let routerSpy = jasmine.createSpyObj(Router, ['']);

//   let mockStripe: any;
//   const countryMockData: Country[] = [];

//   beforeEach(async () => {

//     luv2ShopFormServiceSpy.getCountries.and.returnValue(of(countryMockData));
//     //mockStripe = jasmine.createSpyObj()

//     await TestBed.configureTestingModule({
//       declarations: [ CheckoutComponent ],
//       providers: [
//         {provide: FormBuilder, useValue: formBuilderSpy},
//         {provide: Luv2ShopFormService, useValue: luv2ShopFormServiceSpy},
//         {provide: CartService, useValue: cartServiceSpy},
//         {provide: CheckoutService, useValue: checkoutServiceSpy},
//         {provide: Router, useValue: routerSpy},
//         //{provide: Stripe, useValue: stripeSpy}
//       ]
//     })
//     .compileComponents();
//   });

//   beforeEach(() => {
//     fixture = TestBed.createComponent(CheckoutComponent);
//     component = fixture.componentInstance;
//     fixture.detectChanges();
//   });

//   it('should create', () => {
//     expect(component).toBeTruthy();
//   });
// });
