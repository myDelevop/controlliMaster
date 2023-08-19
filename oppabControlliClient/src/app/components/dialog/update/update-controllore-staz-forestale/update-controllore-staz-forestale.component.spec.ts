import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateControlloreStazForestaleComponent } from './update-controllore-staz-forestale.component';

describe('UpdateControlloreStazForestaleComponent', () => {
  let component: UpdateControlloreStazForestaleComponent;
  let fixture: ComponentFixture<UpdateControlloreStazForestaleComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UpdateControlloreStazForestaleComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdateControlloreStazForestaleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
