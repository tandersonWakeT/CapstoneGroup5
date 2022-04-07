// import { ComponentFixture, TestBed } from '@angular/core/testing';
// import { OktaAuthStateService, OKTA_CONFIG } from '@okta/okta-angular';
// import { OktaAuth } from '@okta/okta-auth-js';
// import { environment } from 'src/environments/environment';

// import { LoginComponent } from './login.component';

// describe('LoginComponent', () => {

//   const oktaConfig = {
//     issuer: 'https://not-real.okta.com',
//     clientId: 'fake-client-id',
//     redirectUri: environment.compaccesApiUrl + '/products'
//   }

//   let loginComponent: LoginComponent;
//   let fixture: ComponentFixture<LoginComponent>;

//   let mockAuthClient: any;
//   //let authClientSpy = jasmine.createSpyObj(OktaAuth, ['signInWithRedirect']);

//   beforeEach(async () => {

//     mockAuthClient = jasmine.createSpyObj(['signInWithRedirect']);
//     mockAuthClient.signInWithRedirect.and.returnValue();

//     await TestBed.configureTestingModule({
//       imports: [

//       ],
//       declarations: [ LoginComponent ],
//       providers: [
//         {provide: OktaAuth, useValue: mockAuthClient},
//         {provide: OKTA_CONFIG, useValue: oktaConfig}
//       ]
//     })
//     .compileComponents();
//   });

//   beforeEach(() => {
//     fixture = TestBed.createComponent(LoginComponent);
//     loginComponent = fixture.componentInstance;
//     fixture.detectChanges();
//   });

//   it('should create', () => {
//     expect(loginComponent).toBeTruthy();
//   });
// });
