import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {CustomerModule} from './customer/customer.module';
import { DateFormat } from './date/date-format';
import {UserModule} from './user/user.module';
import {NotFoundComponent} from './notfound.component';
import {HttpModule} from '@angular/http';
import {HttpClientModule} from '@angular/common/http';
import {HomeComponent} from './home/home.component';
import {LoginComponent} from './login/login.component';
import {AuthenticationService} from './login/authentication.service';
import {AuthCanActivate} from './login/auth.canActivate';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {
  MatButtonModule, MatCheckboxModule, MatTableModule, MatPaginatorModule, MatInputModule, MatCardModule, MatDatepickerModule,
  MatListModule, MatSidenavModule, MatIconModule, MatToolbarModule, MatMenuModule, MatTooltipModule, MatProgressBarModule, MatChipsModule
} from '@angular/material';
import {MatNativeDateModule, DateAdapter} from '@angular/material/core';



import {NgPipesModule} from 'ngx-pipes';
@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    NotFoundComponent
  ],
  imports: [
    AppRoutingModule, BrowserAnimationsModule, BrowserModule, CustomerModule, FormsModule, HttpClientModule, HttpModule,
    MatCardModule, MatChipsModule, MatDatepickerModule, MatIconModule, MatInputModule, MatListModule, MatMenuModule, MatNativeDateModule,
    MatProgressBarModule, MatSidenavModule, MatToolbarModule, MatTooltipModule, NgPipesModule, UserModule
  ],
  providers: [AuthenticationService, AuthCanActivate, {provide: DateAdapter, useClass: DateFormat}],
  bootstrap: [AppComponent]
})
export class AppModule {}
