import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChatbubbleComponent } from './chatbubble.component';

describe('ChatbubbleComponent', () => {
  let component: ChatbubbleComponent;
  let fixture: ComponentFixture<ChatbubbleComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ChatbubbleComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ChatbubbleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
