import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserTicketsComponent } from './user-tickets.component';

describe('UserTicketsComponent', () => {
  let component: UserTicketsComponent;
  let fixture: ComponentFixture<UserTicketsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UserTicketsComponent]
    });
    fixture = TestBed.createComponent(UserTicketsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
