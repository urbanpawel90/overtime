import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { Overtime } from './overtime';
import { Summary } from './summary';

@Injectable()
export class OvertimeService {
  private static overtimeUrl = 'api/overtime';
  private static summaryUrl = 'api/summary';

  constructor(private http: HttpClient) { }

  getOvertimeTable(): Observable<Overtime[]> {
    return this.http.get<Overtime[]>(OvertimeService.overtimeUrl);
  }

  getOvertimeSummary(): Observable<Summary> {
    return this.http.get<Summary>(OvertimeService.summaryUrl);
  }

  reportOvertime(overtime: Overtime, comment: string = null): Observable<any> {
    return this.http.post<any>(OvertimeService.overtimeUrl, JSON.stringify({...overtime, comment}), {
      headers: {
        "Content-Type": "application/json"
      }
    });
  }
}
