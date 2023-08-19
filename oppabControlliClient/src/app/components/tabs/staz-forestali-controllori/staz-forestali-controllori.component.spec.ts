import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StazForestaliControlloriComponent } from './staz-forestali-controllori.component';

describe('StazForestaliControlloriComponent', () => {
  let component: StazForestaliControlloriComponent;
  let fixture: ComponentFixture<StazForestaliControlloriComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StazForestaliControlloriComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StazForestaliControlloriComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
