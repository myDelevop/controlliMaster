import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateControlloreComponent } from './update-controllore.component';

describe('UpdateControlloreComponent', () => {
  let component: UpdateControlloreComponent;
  let fixture: ComponentFixture<UpdateControlloreComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UpdateControlloreComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdateControlloreComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
