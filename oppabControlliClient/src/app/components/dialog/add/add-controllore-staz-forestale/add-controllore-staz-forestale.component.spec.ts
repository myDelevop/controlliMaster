import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddControlloreStazForestaleComponent } from './add-controllore-staz-forestale.component';

describe('AddControlloreStazForestaleComponent', () => {
  let component: AddControlloreStazForestaleComponent;
  let fixture: ComponentFixture<AddControlloreStazForestaleComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddControlloreStazForestaleComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddControlloreStazForestaleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
