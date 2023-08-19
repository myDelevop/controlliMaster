import { TestBed } from '@angular/core/testing';

import { GestioneStazioniForestaliService } from './gestione-stazioni-forestali.service';

describe('GestioneStazioniForestaliService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: GestioneStazioniForestaliService = TestBed.get(GestioneStazioniForestaliService);
    expect(service).toBeTruthy();
  });
});
