import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TechnicienTicketsComponent } from './technicien-tickets.component';

describe('TechnicienTicketsComponent', () => {
  let component: TechnicienTicketsComponent;
  let fixture: ComponentFixture<TechnicienTicketsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [TechnicienTicketsComponent]
    });
    fixture = TestBed.createComponent(TechnicienTicketsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
