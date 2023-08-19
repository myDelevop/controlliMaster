import { TestBed } from '@angular/core/testing';

import { GestioneAnagraficheService } from './gestione-anagrafiche.service';

describe('GestioneAnagraficheServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: GestioneAnagraficheService = TestBed.get(GestioneAnagraficheService);
    expect(service).toBeTruthy();
  });
});
