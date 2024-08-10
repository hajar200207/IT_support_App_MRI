import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PanneSearchComponent } from './panne-search.component';

describe('PanneSearchComponent', () => {
  let component: PanneSearchComponent;
  let fixture: ComponentFixture<PanneSearchComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PanneSearchComponent]
    });
    fixture = TestBed.createComponent(PanneSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
