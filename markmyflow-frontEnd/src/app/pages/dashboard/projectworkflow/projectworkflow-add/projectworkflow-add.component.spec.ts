import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProjectworkflowAddComponent } from './projectworkflow-add.component';

describe('ProjectworkflowAddComponent', () => {
  let component: ProjectworkflowAddComponent;
  let fixture: ComponentFixture<ProjectworkflowAddComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProjectworkflowAddComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProjectworkflowAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
