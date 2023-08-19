import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateChecklistTemplateComponent } from './update-checklist-template.component';

describe('UpdateChecklistTemplateComponent', () => {
  let component: UpdateChecklistTemplateComponent;
  let fixture: ComponentFixture<UpdateChecklistTemplateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UpdateChecklistTemplateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdateChecklistTemplateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
