import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EquipmentFormComponent } from './equipment-form.component';

describe('EquipmentFormComponent', () => {
  let component: EquipmentFormComponent;
  let fixture: ComponentFixture<EquipmentFormComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EquipmentFormComponent]
    });
    fixture = TestBed.createComponent(EquipmentFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
