import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { OvertimeService } from './overtime.service';
import { OvertimeListComponent } from './overtime-list/overtime-list.component';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { RouterModule } from '@angular/router';
import { AppComponent } from './app.component';


@NgModule({
  declarations: [
    AppComponent,
    OvertimeListComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [OvertimeService],
  bootstrap: [AppComponent]
})
export class AppModule { }
