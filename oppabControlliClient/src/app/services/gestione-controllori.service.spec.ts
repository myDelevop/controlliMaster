import { TestBed } from '@angular/core/testing';

import { GestioneControlloriService } from './gestione-controllori.service';

describe('GestioneControlloriService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: GestioneControlloriService = TestBed.get(GestioneControlloriService);
    expect(service).toBeTruthy();
  });
});
