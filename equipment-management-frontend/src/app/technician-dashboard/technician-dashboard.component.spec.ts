import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TechnicianDashboardComponent } from './technician-dashboard.component';

describe('TechnicianDashboardComponent', () => {
  let component: TechnicianDashboardComponent;
  let fixture: ComponentFixture<TechnicianDashboardComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [TechnicianDashboardComponent]
    });
    fixture = TestBed.createComponent(TechnicianDashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
