import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddControlloreComponent } from './add-controllore.component';

describe('AddControlloreComponent', () => {
  let component: AddControlloreComponent;
  let fixture: ComponentFixture<AddControlloreComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddControlloreComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddControlloreComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
