import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IntentEditorComponent } from './intent-editor.component';

describe('IntentEditorComponent', () => {
  let component: IntentEditorComponent;
  let fixture: ComponentFixture<IntentEditorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ IntentEditorComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(IntentEditorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
