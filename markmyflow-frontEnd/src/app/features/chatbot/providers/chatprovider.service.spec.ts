import { TestBed } from '@angular/core/testing';

import { ChatproviderService } from './chatprovider.service';

describe('ChatproviderService', () => {
  let service: ChatproviderService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ChatproviderService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
