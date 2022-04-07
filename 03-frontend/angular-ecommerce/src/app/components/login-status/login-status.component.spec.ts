// import { InjectionToken } from '@angular/core';
// import { ComponentFixture, TestBed } from '@angular/core/testing';
// import { OktaAuthStateService, OKTA_AUTH } from '@okta/okta-angular';
// import { OktaAuth } from '@okta/okta-auth-js';
// import { of } from 'rxjs';

// import { LoginStatusComponent } from './login-status.component';

// describe('LoginStatusComponent', () => {

//   let mockAuthStateService: any;
//   let mockOktaAuth: any;

//   let component: LoginStatusComponent;
//   let fixture: ComponentFixture<LoginStatusComponent>;

//   let mockOKTA_AUTH: any;


//   const mockAuthStateData = [];

//   beforeEach(async () => {

//     mockAuthStateService = jasmine.createSpyObj(OktaAuthStateService, ['authState$']);
//     mockAuthStateService.authState$.and.returnValue(of(mockAuthStateData));

//     mockOktaAuth = jasmine.createSpyObj(OktaAuth, ['getUser', 'signOut']);

//     mockOKTA_AUTH = new InjectionToken<OktaAuth>('okta_auth');

//     await TestBed.configureTestingModule({
//       declarations: [ LoginStatusComponent ],
//       providers: [
//         {provide: OktaAuthStateService, useValue: mockAuthStateService},
//         {provide: OktaAuth, useValue: mockOktaAuth},
//         {provide: OKTA_AUTH, useValue: undefined}
//       ]
//     })
//     .compileComponents();
//   });

//   beforeEach(() => {
//     fixture = TestBed.createComponent(LoginStatusComponent);
//     component = fixture.componentInstance;
//     fixture.detectChanges();
//   });

//   it('should create', () => {
//     expect(component).toBeTruthy();
//   });
// });
