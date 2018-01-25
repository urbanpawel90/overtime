import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OvertimeListComponent } from './overtime-list.component';

describe('OvertimeListComponent', () => {
  let component: OvertimeListComponent;
  let fixture: ComponentFixture<OvertimeListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OvertimeListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OvertimeListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
