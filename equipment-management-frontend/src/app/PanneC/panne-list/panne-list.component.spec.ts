import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PanneListComponent } from './panne-list.component';

describe('PanneListComponent', () => {
  let component: PanneListComponent;
  let fixture: ComponentFixture<PanneListComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PanneListComponent]
    });
    fixture = TestBed.createComponent(PanneListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
