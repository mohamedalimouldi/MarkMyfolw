import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProjectworkflowDetailsComponent } from './projectworkflow-details.component';

describe('ProjectworkflowDetailsComponent', () => {
  let component: ProjectworkflowDetailsComponent;
  let fixture: ComponentFixture<ProjectworkflowDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProjectworkflowDetailsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProjectworkflowDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
