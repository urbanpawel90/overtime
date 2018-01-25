import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OvertimeReportComponent } from './overtime-report.component';

describe('OvertimeReportComponent', () => {
  let component: OvertimeReportComponent;
  let fixture: ComponentFixture<OvertimeReportComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OvertimeReportComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OvertimeReportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
