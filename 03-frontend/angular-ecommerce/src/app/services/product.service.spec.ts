import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { Observable } from 'rxjs';

import { ProductService } from './product.service';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Product } from '../common/product';
import { environment } from 'src/environments/environment';

describe('ProductService', () => {

  let productService: ProductService;
  let http: HttpClient;
  let httpController: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [ProductService],
    });
    productService = TestBed.inject(ProductService);
    http = TestBed.inject(HttpClient);
    httpController = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {

    httpController.verify();
  });

  it('should be created', () => {

    expect(productService).toBeDefined();
  });

  it('get a product', () => {

    const testUrl = environment.compaccesApiUrl + '/products';

    const testData =  new Product();
    const inputData = {
      productId: 0
    }

    productService
      .getProduct(inputData.productId)
      .subscribe((result) => expect(result).toBe(testData));

    const req = httpController.expectOne(testUrl + `/${inputData.productId}`);

    expect(req.request.method).toEqual('GET');

    req.flush(testData);
  });

  it('get a product fail', () => {

    const baseUrl = environment.compaccesApiUrl + '/products';

    const failMsg =  '404 not found';
    const inputData = {
      productId: 999
    }

    productService
      .getProduct(inputData.productId)
      .subscribe({
        next: () => fail('should have failed with 404 error'),
        error: (error: HttpErrorResponse) => {
                   expect(error.status).toEqual(404, 'status');
                   expect(error.error).toEqual(failMsg, 'message');
                 }
                });

    const req = httpController.expectOne(baseUrl + `/${inputData.productId}`);

    expect(req.request.method).toEqual('GET');

    req.flush(failMsg, { status:404, statusText: '404 not found'});
  });
});
