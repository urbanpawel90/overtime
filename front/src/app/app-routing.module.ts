import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { OvertimeListComponent } from './overtime-list/overtime-list.component';
import { OvertimeReportComponent } from './overtime-report/overtime-report.component';

const routes: Routes = [
  { path: '', redirectTo: '/list', pathMatch: 'full' },
  { path: 'list', component: OvertimeListComponent },
  { path: 'report', component: OvertimeReportComponent }
]

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
