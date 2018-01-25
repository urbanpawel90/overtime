import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { OvertimeService } from './overtime.service';
import { OvertimeListComponent } from './overtime-list/overtime-list.component';
import { HttpClientModule } from '@angular/common/http';


@NgModule({
  declarations: [
    OvertimeListComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule
  ],
  providers: [OvertimeService],
  bootstrap: [OvertimeListComponent]
})
export class AppModule { }
