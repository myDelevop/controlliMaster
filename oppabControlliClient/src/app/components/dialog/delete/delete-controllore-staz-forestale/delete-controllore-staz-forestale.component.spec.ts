import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DeleteControlloreStazForestaleComponent } from './delete-controllore-staz-forestale.component';

describe('DeleteControlloreStazForestaleComponent', () => {
  let component: DeleteControlloreStazForestaleComponent;
  let fixture: ComponentFixture<DeleteControlloreStazForestaleComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DeleteControlloreStazForestaleComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DeleteControlloreStazForestaleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
