import { TestBed } from '@angular/core/testing';

import { GestioneControlliService } from './gestione-controlli.service';

describe('GestioneControlliService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: GestioneControlliService = TestBed.get(GestioneControlliService);
    expect(service).toBeTruthy();
  });
});
