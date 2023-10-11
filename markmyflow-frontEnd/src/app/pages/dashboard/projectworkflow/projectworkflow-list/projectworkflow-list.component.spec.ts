import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProjectworkflowListComponent } from './projectworkflow-list.component';

describe('ProjectworkflowListComponent', () => {
  let component: ProjectworkflowListComponent;
  let fixture: ComponentFixture<ProjectworkflowListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProjectworkflowListComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProjectworkflowListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
