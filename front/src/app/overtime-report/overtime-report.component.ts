import { Component, OnInit } from '@angular/core';
import { Overtime } from '../overtime';
import { Location } from '@angular/common';
import { OvertimeService } from '../overtime.service';

@Component({
  selector: 'app-overtime-report',
  templateUrl: './overtime-report.component.html'
})
export class OvertimeReportComponent {
  overtime: Overtime;
  comment: string;

  constructor(private location: Location, private overtimeService: OvertimeService) {
    this.overtime = new Overtime(new Date(Date.now()), 1);
  }

  onSave() {
    this.reportOvertime(this.overtime);
  }

  onReduce() {
    this.reportOvertime(this.overtime.withNegativeHours());
  }

  reportOvertime(overtime: Overtime) {
    this.overtimeService.reportOvertime(overtime, this.comment)
      .subscribe(_ => {
        this.location.back();
      }, err => {
        console.log(err);
      });
  }
}
