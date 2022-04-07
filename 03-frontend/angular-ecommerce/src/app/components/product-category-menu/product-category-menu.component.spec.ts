import { ComponentFixture, TestBed } from '@angular/core/testing';
import { from, of } from 'rxjs';
import { ProductCategory } from 'src/app/common/product-category';
import { ProductService } from 'src/app/services/product.service';

import { ProductCategoryMenuComponent } from './product-category-menu.component';

describe('ProductCategoryMenuComponent', () => {

  let component: ProductCategoryMenuComponent;
  let fixture: ComponentFixture<ProductCategoryMenuComponent>;

  //let productSpy = jasmine.createSpyObj(ProductService, ['getProductCategories.subscribe']);
  const mockData: ProductCategory[] = [];
  let mockProductService: any;

  beforeEach(async () => {

    mockProductService = jasmine.createSpyObj(['getProductCategories']);
    mockProductService.getProductCategories.and.returnValue(of(mockData));

    await TestBed.configureTestingModule({
      declarations: [ ProductCategoryMenuComponent ],
      providers: [
        {provide: ProductService, useValue: mockProductService}
      ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ProductCategoryMenuComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
