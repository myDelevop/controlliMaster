import { TestBed } from '@angular/core/testing';

import { GestioneAziendaService } from './gestione-azienda.service';

describe('GestioneAziendaService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: GestioneAziendaService = TestBed.get(GestioneAziendaService);
    expect(service).toBeTruthy();
  });
});
