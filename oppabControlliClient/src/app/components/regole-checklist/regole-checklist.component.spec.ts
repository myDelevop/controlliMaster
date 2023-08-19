import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RegoleChecklistComponent } from './regole-checklist.component';

describe('RegoleChecklistComponent', () => {
  let component: RegoleChecklistComponent;
  let fixture: ComponentFixture<RegoleChecklistComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RegoleChecklistComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RegoleChecklistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
