import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GesioneRegoleChecklistComponent } from './gesione-regole-checklist.component';

describe('GesioneRegoleChecklistComponent', () => {
  let component: GesioneRegoleChecklistComponent;
  let fixture: ComponentFixture<GesioneRegoleChecklistComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GesioneRegoleChecklistComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GesioneRegoleChecklistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
