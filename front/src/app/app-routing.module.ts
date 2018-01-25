import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { OvertimeListComponent } from './overtime-list/overtime-list.component';

const routes: Routes = [
  { path: 'list', component: OvertimeListComponent}
]

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
