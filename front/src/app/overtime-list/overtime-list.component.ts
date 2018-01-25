import { Component, OnInit } from '@angular/core';
import { OvertimeService } from '../overtime.service';
import { Overtime } from '../overtime';
import { overrideComponentView } from '@angular/core/src/view/entrypoint';

@Component({
  selector: 'overtime-list',
  templateUrl: './overtime-list.component.html',
  styleUrls: ['./overtime-list.component.css']
})
export class OvertimeListComponent implements OnInit {
  overtimes: Overtime[];

  constructor(private overtimeService: OvertimeService) { }

  ngOnInit() {
    this.loadOvertimes();
  }

  loadOvertimes() {
    this.overtimeService.getOvertimeTable().subscribe(overtimes => {
      this.overtimes = overtimes;
    });
  }

}
