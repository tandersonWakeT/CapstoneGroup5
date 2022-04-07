import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Pipe } from '@angular/core';
import { fakeAsync, TestBed, tick } from '@angular/core/testing';
import { defer, Observable, of, Subscriber } from 'rxjs';
import { Country } from '../common/country';
import { State } from '../common/state';

import { Luv2ShopFormService } from './luv2-shop-form.service';

describe('Luv2ShopFormService', () => {

  let httpClientSpy: jasmine.SpyObj<HttpClient>;
  let formService: Luv2ShopFormService;

  beforeEach(() => {

    httpClientSpy = jasmine.createSpyObj('HttpClient', ['get']);
    formService = new Luv2ShopFormService(httpClientSpy);

  });


  it('should be created', () => {
    expect(formService).toBeDefined();
  });


  it ('should return expected countries', (done: DoneFn) => {

    const expectedCountries: Country[] =
      [{ id: 1, code: "BR", name: "Brazil" },
       { id: 2, code: "CA", name: "Canada" },
       { id: 3, code: "DE", name: "Germany" },
       { id: 4, code: "IN", name: "India" },
       { id: 5, code: "TR", name: "Turkey" },
       { id: 6, code: "US", name: "United States" }];

    const inputData = {

      "_embedded" : {

        "countries" : [ {

          "id" : 1,

          "code" : "BR",

          "name" : "Brazil"

        }, {

          "id" : 2,

          "code" : "CA",

          "name" : "Canada"

        }, {

          "id" : 3,

          "code" : "DE",

          "name" : "Germany"

        }, {

          "id" : 4,

          "code" : "IN",

          "name" : "India"

        }, {

          "id" : 5,

          "code" : "TR",

          "name" : "Turkey"

        }, {

          "id" : 6,

          "code" : "US",

          "name" : "United States"

        } ]

      },

      "_links" : {

        "self" : {

          "href" : "https://localhost:8443/api/countries"

        },

        "profile" : {

          "href" : "https://localhost:8443/api/profile/countries"

        }

      },

      "page" : {

        "size" : 20,

        "totalElements" : 6,

        "totalPages" : 1,

        "number" : 0

      }

    };

    httpClientSpy.get.and.returnValue(asyncData(inputData));

    formService.getCountries().subscribe({

      next: (countries) => {
        expect(countries)
          .withContext('expected countries')
          .toEqual(expectedCountries);
        done();
      },
      error: done.fail
    });
    expect(httpClientSpy.get.calls.count())
      .withContext('one call')
      .toBe(1);
  });


  it('should return an error when the server returns a 404 for getCountry()', (done: DoneFn) => {

    const errorResponse = new HttpErrorResponse({
      error: 'test 404 error',
      status: 404, statusText: 'Not Found'
    });

    httpClientSpy.get.and.returnValue(asyncError<Country>(errorResponse));

    formService.getCountries().subscribe({

      next: () => done.fail('expect an error, not countries'),
      error: error => {
        expect(error.message).toContain('404 Not Found');
        done();
      }
    })
  });


  it('should return expected states based on country code', (done: DoneFn) => {

    const inputCode = "us";
    const inputData = {

      "_embedded" : {

        "states" : [ {

          "id" : 93,

          "name" : "Alabama"

        }, {

          "id" : 94,

          "name" : "Alaska"

        }, {

          "id" : 95,

          "name" : "Arizona"

        }]
      },
      "_links" : {

        "self" : {

          "href" : "https://localhost:8443/api/states/search/findByCountryCode?code=us"

        }

      }
    };

    const expectedStates: State[] = [
      { id: 93, name: "Alabama" },
      { id: 94, name: "Alaska" },
      { id: 95, name: "Arizona" }
    ];

    httpClientSpy.get.and.returnValue(asyncData(inputData));

    formService.getStates(inputCode).subscribe({

      next: (states) => {
        expect(states)
          .withContext('expected states')
          .toEqual(expectedStates);
        done();
      },
      error: done.fail
    });
    expect(httpClientSpy.get.calls.count())
      .withContext('one call')
      .toBe(1);
  });


  it('should return an error when the server returns a 404 for getStates()', (done: DoneFn) => {

    const inputCode = "us";

    const errorResponse = new HttpErrorResponse({
      error: 'test 404 error',
      status: 404, statusText: 'Not Found'
    });

    httpClientSpy.get.and.returnValue(asyncError<Country>(errorResponse));

    formService.getStates(inputCode).subscribe({

      next: () => done.fail('expect an error, not countries'),
      error: error => {
        expect(error.message).toContain('404 Not Found');
        done();
      }
    })
  });

  /*
  * HAPPY PATH
  * Months returned should be 1 - 12 inclusive
  */
  it('should return numerical list representing months <= 12', (done: DoneFn) => {

    const startMonth = 1;

    let expectedMonths: number[] = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12];

    formService.getCreditCardMonths(startMonth).subscribe({

      next: (months) => {
        expect(months)
          .withContext('Expected Months')
          .toEqual(expectedMonths);
        done();
      },
      error: done.fail
    })
  });

  /*
  * HAPPY PATH
  * Months returned should be 3 - 12 inclusive
  * Since the start month is 3 only months >= 3
  * should be shown
  */
  it('should return numerical list representing months <= 12', (done: DoneFn) => {

    const startMonth = 3;

    let expectedMonths: number[] = [3, 4, 5, 6, 7, 8, 9, 10, 11, 12];

    formService.getCreditCardMonths(startMonth).subscribe({

      next: (months) => {
        expect(months)
          .withContext('Expected Months')
          .toEqual(expectedMonths);
        done();
      },
      error: done.fail
    })
  });

  /*
  * HAPPY PATH
  * Retrieve credit card years, should contain
  * 10 values in list starting with current year
  * increasing up to current year + 10
  */
  it('should return numerical list of years: curYear + 10', (done: DoneFn) => {

    const curYear: number = new Date().getFullYear();
    const expectedYearList: number[] = [];

    for (let i = curYear; i <= curYear + 10; i++) {
      expectedYearList.push(i);
    }

    formService.getCreditCardYears().subscribe({

      next: (years) => {
        expect(years)
          .withContext('Next 10 years')
          .toEqual(expectedYearList);
        done();
      },
      error: done.fail
    })

  });

  /*
  * END OF UNIT TESTS
  */
});

/** Create async observable that emits-once and completes
 *  after a JS engine turn */
function asyncData<T>(data: T) {
  return defer(() => Promise.resolve(data));
}

/**
 * Create async observable error that errors
 * after a JS engine turn
 */
function asyncError<T>(errorObject: any) {
  return defer(() => Promise.reject(errorObject));
}

