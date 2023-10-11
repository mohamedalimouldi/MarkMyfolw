import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PassagelistComponent } from './passagelist.component';

describe('PassagelistComponent', () => {
  let component: PassagelistComponent;
  let fixture: ComponentFixture<PassagelistComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PassagelistComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PassagelistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
