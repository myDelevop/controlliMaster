import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ChecklistTemplateComponent } from './checklist-template.component';

describe('ChecklistTemplateComponent', () => {
  let component: ChecklistTemplateComponent;
  let fixture: ComponentFixture<ChecklistTemplateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ChecklistTemplateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ChecklistTemplateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
