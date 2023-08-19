import { TestBed } from '@angular/core/testing';

import { RegoleChecklistService } from './regole-checklist.service';

describe('RegoleChecklistService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: RegoleChecklistService = TestBed.get(RegoleChecklistService);
    expect(service).toBeTruthy();
  });
});
