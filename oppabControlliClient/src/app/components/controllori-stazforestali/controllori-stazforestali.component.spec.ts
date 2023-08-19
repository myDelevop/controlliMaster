import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ControlloriStazforestaliComponent } from './controllori-stazforestali.component';

describe('ControlloriStazforestaliComponent', () => {
  let component: ControlloriStazforestaliComponent;
  let fixture: ComponentFixture<ControlloriStazforestaliComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ControlloriStazforestaliComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ControlloriStazforestaliComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
