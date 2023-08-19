import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DeleteStazioneForestaleComponent } from './delete-stazione-forestale.component';

describe('DeleteStazioneForestaleComponent', () => {
  let component: DeleteStazioneForestaleComponent;
  let fixture: ComponentFixture<DeleteStazioneForestaleComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DeleteStazioneForestaleComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DeleteStazioneForestaleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
