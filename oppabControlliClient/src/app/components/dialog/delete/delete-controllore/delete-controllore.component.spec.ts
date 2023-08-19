import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DeleteControlloreComponent } from './delete-controllore.component';

describe('DeleteControlloreComponent', () => {
  let component: DeleteControlloreComponent;
  let fixture: ComponentFixture<DeleteControlloreComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DeleteControlloreComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DeleteControlloreComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
