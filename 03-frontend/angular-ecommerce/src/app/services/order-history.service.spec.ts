import { HttpClient } from '@angular/common/http';
import { HttpTestingController } from '@angular/common/http/testing';
import { TestBed } from '@angular/core/testing';
import { defer } from 'rxjs';

import { OrderHistory } from '../common/order-history';
import { OrderHistoryService } from './order-history.service';

describe('OrderHistoryService', () => {

  let httpClientSpy: jasmine.SpyObj<HttpClient>;
  let orderService: OrderHistoryService;

  const inputData = {
    "_embedded": {
        "orders": [
            {
                "id": 16,
                "orderTrackingNumber": "2023d1a4-4d18-4884-aecd-8d3d175e34a5",
                "totalQuantity": 10,
                "totalPrice": 770.90,
                "dateCreated": "2022-03-24",
            },
            {
                "id": 14,
                "orderTrackingNumber": "c0da8268-3cec-4932-8e72-f8a065c4b59d",
                "totalQuantity": 1,
                "totalPrice": 59.99,
                "dateCreated": "2022-03-23"
            },
            {
                "id": 3,
                "orderTrackingNumber": "89527bcd-6b43-4144-806c-a8b1d15ee596",
                "totalQuantity": 2,
                "totalPrice": 36.98,
                "dateCreated": "2022-03-13"
            }
        ]
    }
};

  beforeEach(() => {

    httpClientSpy = jasmine.createSpyObj('HttpClient', ['get']);
    orderService = new OrderHistoryService(httpClientSpy);

  });

  it('should be created', () => {
    expect(orderService).toBeDefined();
  });

  /*
  * Test getOrderHistory()
  * Utilizes httpClientSpy to acquire mock data
  * about order history based on customer's email
  * and return expected information
  * =========INCOMPLETE==========
  */
  // it('should return order history based on customer email', (done: DoneFn) => {

  //   const testEmail: string = "susan.smith@luv2code.com";

  //   const expectedOrderHistory: OrderHistory[] = [
  //     {id: "16",
  //     orderTrackingNumber: "2023d1a4-4d18-4884-aecd-8d3d175e34a5",
  //     totalPrice: 770.90,
  //     totalQuantity: 10,
  //     dateCreated: new Date(2022-0o3-24)},
  //     {id: "14",
  //     orderTrackingNumber: "c0da8268-3cec-4932-8e72-f8a065c4b59d",
  //     totalPrice: 59.99,
  //     totalQuantity: 1,
  //     dateCreated: new Date(2022-0o3-24)},
  //     {id: "3",
  //     orderTrackingNumber: "89527bcd-6b43-4144-806c-a8b1d15ee596",
  //     totalPrice: 36.98,
  //     totalQuantity: 2,
  //     dateCreated: new Date(2022-0o3-24)}
  //   ];

  //   const expectedOrderHistory2 = {
  //     "_embedded": {
  //         "orders": [
  //             {
  //                 "id": 16,
  //                 "orderTrackingNumber": "2023d1a4-4d18-4884-aecd-8d3d175e34a5",
  //                 "totalQuantity": 10,
  //                 "totalPrice": 770.90,
  //                 "dateCreated": "2022-03-24",
  //             },
  //             {
  //                 "id": 14,
  //                 "orderTrackingNumber": "c0da8268-3cec-4932-8e72-f8a065c4b59d",
  //                 "totalQuantity": 1,
  //                 "totalPrice": 59.99,
  //                 "dateCreated": "2022-03-23"
  //             },
  //             {
  //                 "id": 3,
  //                 "orderTrackingNumber": "89527bcd-6b43-4144-806c-a8b1d15ee596",
  //                 "totalQuantity": 2,
  //                 "totalPrice": 36.98,
  //                 "dateCreated": "2022-03-13"
  //             }
  //         ]
  //     }
  // };

  //   let orderHistoryList: OrderHistory[] = [];

  //   httpClientSpy.get.and.returnValue(asyncData(inputData));

  //   orderService.getOrderHistory(testEmail).subscribe(

  //     data => {
  //       orderHistoryList = data._embedded.orders;
  //     }
  //   );
  //   expect(orderHistoryList).toEqual(expectedOrderHistory);

  // //     next: (history) => {
  // //       expect(history)
  // //         .withContext('expected order history based on email')
  // //         .toBe();
  // //       done();
  // //     },
  // //     error: done.fail
  // //   });
  // //   expect(httpClientSpy.get.calls.count())
  // //     .withContext('one call')
  // //     .toBe(1);
  // });

});

// /** Create async observable that emits-once and completes
//  *  after a JS engine turn */
//  function asyncData<T>(data: T) {
//   return defer(() => Promise.resolve(data));
// }
