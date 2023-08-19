import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AziendaStazioneForestaleComponent } from './azienda-stazione-forestale.component';

describe('AziendaStazioneForestaleComponent', () => {
  let component: AziendaStazioneForestaleComponent;
  let fixture: ComponentFixture<AziendaStazioneForestaleComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AziendaStazioneForestaleComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AziendaStazioneForestaleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
