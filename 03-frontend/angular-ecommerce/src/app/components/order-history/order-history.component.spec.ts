import { NgForOf, NgIf } from '@angular/common';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormsModule } from '@angular/forms';
import { of } from 'rxjs';
import { OrderHistory } from 'src/app/common/order-history';
import { OrderHistoryService } from 'src/app/services/order-history.service';

import { OrderHistoryComponent } from './order-history.component';

describe('OrderHistoryComponent', () => {

  let h1: OrderHistory = new OrderHistory;
  let h2: OrderHistory = new OrderHistory;
  let h3: OrderHistory = new OrderHistory;

  h1.id = "1";
  h1.orderTrackingNumber = "testtrackingnumber1";
  h1.totalPrice = 9.99;
  h1.totalQuantity = 10;
  h1.dateCreated = new Date();

  h2.id = "2";
  h2.orderTrackingNumber = "testtrackingnumber2";
  h2.totalPrice = 9.99;
  h2.totalQuantity = 10;
  h2.dateCreated = new Date();

  h3.id = "3";
  h3.orderTrackingNumber = "testtrackingnumber3";
  h3.totalPrice = 9.99;
  h3.totalQuantity = 10;
  h3.dateCreated = new Date();

  const mockData = [h1, h2, h3];

  let mockOrderHistoryService: any;

  //let orderHistoryServiceSpy: OrderHistoryService;
  let historyComponent: OrderHistoryComponent;
  let fixture: ComponentFixture<OrderHistoryComponent>;

  beforeEach(async () => {

    mockOrderHistoryService = jasmine.createSpyObj(['getOrderHistory']);
    mockOrderHistoryService.getOrderHistory.and.returnValue(of(mockData));

    // orderHistoryServiceSpy = jasmine.createSpyObj(
    //   OrderHistoryService,
    //    [
    //      {'getOrderHistory': of(historyData)}
    //    ]
    // );


    await TestBed.configureTestingModule({
      declarations: [ OrderHistoryComponent ],
      providers: [
        { provide: OrderHistoryService, useValue: mockOrderHistoryService }
      ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OrderHistoryComponent);
    historyComponent = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(historyComponent).toBeDefined();
  });
});
