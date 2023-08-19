import { TestBed } from '@angular/core/testing';

import { AssegnazioneControlliService } from './assegnazione-controlli.service';

describe('AssegnazioneControlliService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: AssegnazioneControlliService = TestBed.get(AssegnazioneControlliService);
    expect(service).toBeTruthy();
  });
});
