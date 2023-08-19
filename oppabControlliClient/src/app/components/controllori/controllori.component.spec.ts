import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ControlloriComponent } from './controllori.component';

describe('ControlloriComponent', () => {
  let component: ControlloriComponent;
  let fixture: ComponentFixture<ControlloriComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ControlloriComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ControlloriComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
