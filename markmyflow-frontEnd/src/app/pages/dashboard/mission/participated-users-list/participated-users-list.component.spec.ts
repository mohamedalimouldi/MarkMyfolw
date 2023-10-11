import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ParticipatedUsersListComponent } from './participated-users-list.component';

describe('ParticipatedUsersListComponent', () => {
  let component: ParticipatedUsersListComponent;
  let fixture: ComponentFixture<ParticipatedUsersListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ParticipatedUsersListComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ParticipatedUsersListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
