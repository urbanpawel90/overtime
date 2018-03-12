import { Component, OnInit, Input } from '@angular/core';
import { Overtime } from '../overtime';

@Component({
  selector: 'app-overtime-history',
  templateUrl: './overtime-history.component.html'
})
export class OvertimeHistoryComponent {
  @Input() overtime: Overtime;
  @Input() onClose: Function;
}
