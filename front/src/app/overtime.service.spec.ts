import { TestBed, inject } from '@angular/core/testing';

import { OvertimeService } from './overtime.service';

describe('OvertimeService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [OvertimeService]
    });
  });

  it('should be created', inject([OvertimeService], (service: OvertimeService) => {
    expect(service).toBeTruthy();
  }));
});
