import { TestBed } from '@angular/core/testing';
import { OktaAuth } from '@okta/okta-auth-js';

import { AuthInterceptorService } from './auth-interceptor.service';

describe('AuthInterceptorService', () => {

  let authClientSpy: jasmine.SpyObj<OktaAuth>;
  let authService: AuthInterceptorService;

  beforeEach(() => {

    authClientSpy = jasmine.createSpyObj('OktaAuth', ['getAccessToken']);
    authService = new AuthInterceptorService(authClientSpy);
  });

  it('should be created', () => {
    expect(authService).toBeDefined();
  });
});
