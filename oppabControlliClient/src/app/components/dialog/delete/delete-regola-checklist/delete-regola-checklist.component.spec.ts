import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DeleteRegolaChecklistComponent } from './delete-regola-checklist.component';

describe('DeleteRegolaChecklistComponent', () => {
  let component: DeleteRegolaChecklistComponent;
  let fixture: ComponentFixture<DeleteRegolaChecklistComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DeleteRegolaChecklistComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DeleteRegolaChecklistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
