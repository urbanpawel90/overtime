import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { Overtime } from './overtime';

@Injectable()
export class OvertimeService {
  private static overtimeUrl = 'api/overtime';

  constructor(private http: HttpClient) { }

  getOvertimeTable(): Observable<Overtime[]> {
    return this.http.get<Overtime[]>(OvertimeService.overtimeUrl);
  }
}
