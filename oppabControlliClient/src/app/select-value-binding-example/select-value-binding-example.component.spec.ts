import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SelectValueBindingExampleComponent } from './select-value-binding-example.component';

describe('SelectValueBindingExampleComponent', () => {
  let component: SelectValueBindingExampleComponent;
  let fixture: ComponentFixture<SelectValueBindingExampleComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SelectValueBindingExampleComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SelectValueBindingExampleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
