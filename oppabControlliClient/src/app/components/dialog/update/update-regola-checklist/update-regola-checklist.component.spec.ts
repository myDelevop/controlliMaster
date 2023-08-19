import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateRegolaChecklistComponent } from './update-regola-checklist.component';

describe('UpdateRegolaChecklistComponent', () => {
  let component: UpdateRegolaChecklistComponent;
  let fixture: ComponentFixture<UpdateRegolaChecklistComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UpdateRegolaChecklistComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdateRegolaChecklistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
