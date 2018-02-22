import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OvertimeHistoryComponent } from './overtime-history.component';

describe('OvertimeHistoryComponent', () => {
  let component: OvertimeHistoryComponent;
  let fixture: ComponentFixture<OvertimeHistoryComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OvertimeHistoryComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OvertimeHistoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
