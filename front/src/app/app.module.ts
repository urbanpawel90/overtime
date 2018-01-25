import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { OvertimeService } from './overtime.service';
import { OvertimeListComponent } from './overtime-list/overtime-list.component';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { RouterModule } from '@angular/router';
import { AppComponent } from './app.component';
import { OvertimeReportComponent } from './overtime-report/overtime-report.component';
import { FormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent,
    OvertimeListComponent,
    OvertimeReportComponent
  ],
  imports: [
    FormsModule,
    BrowserModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [OvertimeService],
  bootstrap: [AppComponent]
})
export class AppModule { }
