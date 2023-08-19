import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddRegolaChecklistComponent } from './add-regola-checklist.component';

describe('AddRegolaChecklistComponent', () => {
  let component: AddRegolaChecklistComponent;
  let fixture: ComponentFixture<AddRegolaChecklistComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddRegolaChecklistComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddRegolaChecklistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
