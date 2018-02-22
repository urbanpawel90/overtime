import { Component, OnInit } from '@angular/core';
import { OvertimeService } from '../overtime.service';
import { Overtime } from '../overtime';
import { overrideComponentView } from '@angular/core/src/view/entrypoint';
import { Summary } from '../summary';
import { Observable } from 'rxjs/Observable';
import { combineLatest } from 'rxjs/observable/combineLatest';

@Component({
  selector: 'overtime-list',
  templateUrl: './overtime-list.component.html',
  styleUrls: ['./overtime-list.component.css']
})
export class OvertimeListComponent implements OnInit {
  overtimes: Overtime[];
  summary: Summary;
  selectedOvertime: Overtime;

  constructor(private overtimeService: OvertimeService) { }

  ngOnInit() {
    this.loadOvertimes();
  }

  loadOvertimes() {
    combineLatest(this.overtimeService.getOvertimeTable(), this.overtimeService.getOvertimeSummary(), (table, summary) => { return { table, summary } })
      .subscribe(combined => {
        this.overtimes = combined.table;
        this.summary = combined.summary;
        this.selectedOvertime = null;
      });
  }

  onItemClick(overtime: Overtime) {
    this.selectedOvertime = overtime;
  }

  dismissDetails() {
    this.selectedOvertime = null;
  }
}
