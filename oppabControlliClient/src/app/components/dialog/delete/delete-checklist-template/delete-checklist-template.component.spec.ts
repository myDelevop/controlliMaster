import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DeleteChecklistTemplateComponent } from './delete-checklist-template.component';

describe('DeleteChecklistTemplateComponent', () => {
  let component: DeleteChecklistTemplateComponent;
  let fixture: ComponentFixture<DeleteChecklistTemplateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DeleteChecklistTemplateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DeleteChecklistTemplateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
