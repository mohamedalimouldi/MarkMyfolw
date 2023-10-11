import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BuySubComponent } from './buy-sub.component';

describe('BuySubComponent', () => {
  let component: BuySubComponent;
  let fixture: ComponentFixture<BuySubComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BuySubComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BuySubComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
