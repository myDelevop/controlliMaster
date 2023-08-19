import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateStazioneForestaleComponent } from './update-stazione-forestale.component';

describe('UpdateStazioneForestaleComponent', () => {
  let component: UpdateStazioneForestaleComponent;
  let fixture: ComponentFixture<UpdateStazioneForestaleComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UpdateStazioneForestaleComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdateStazioneForestaleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
