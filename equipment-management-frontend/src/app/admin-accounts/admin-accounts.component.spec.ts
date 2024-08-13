import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminAccountsComponent } from './admin-accounts.component';

describe('AdminAccountsComponent', () => {
  let component: AdminAccountsComponent;
  let fixture: ComponentFixture<AdminAccountsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AdminAccountsComponent]
    });
    fixture = TestBed.createComponent(AdminAccountsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
