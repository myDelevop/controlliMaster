import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddStazioneForestaleComponent } from './add-stazione-forestale.component';

describe('AddStazioneForestaleComponent', () => {
  let component: AddStazioneForestaleComponent;
  let fixture: ComponentFixture<AddStazioneForestaleComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddStazioneForestaleComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddStazioneForestaleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
