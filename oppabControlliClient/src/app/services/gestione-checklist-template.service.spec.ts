import { TestBed } from '@angular/core/testing';

import { GestioneChecklistTemplateService } from './gestione-checklist-template.service';

describe('GestioneChecklistTemplateService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: GestioneChecklistTemplateService = TestBed.get(GestioneChecklistTemplateService);
    expect(service).toBeTruthy();
  });
});
