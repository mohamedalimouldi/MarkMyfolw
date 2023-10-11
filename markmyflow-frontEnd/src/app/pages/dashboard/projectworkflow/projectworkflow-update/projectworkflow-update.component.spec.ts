import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProjectworkflowUpdateComponent } from './projectworkflow-update.component';

describe('ProjectworkflowUpdateComponent', () => {
  let component: ProjectworkflowUpdateComponent;
  let fixture: ComponentFixture<ProjectworkflowUpdateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProjectworkflowUpdateComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProjectworkflowUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
