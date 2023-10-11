import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateestComponent } from './updateest.component';

describe('UpdateestComponent', () => {
  let component: UpdateestComponent;
  let fixture: ComponentFixture<UpdateestComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UpdateestComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UpdateestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
