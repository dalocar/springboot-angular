import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {UserModule} from './user/user.module';
import {NotFoundComponent} from './notfound.component';
import {HttpModule} from '@angular/http';
import {HomeComponent} from './home/home.component';
import {LoginComponent} from './login/login.component';
import {AuthenticationService} from './login/authentication.service';
import {AuthCanActivate} from './login/auth.canActivate';
@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    NotFoundComponent
  ],
  imports: [
    BrowserModule, AppRoutingModule, UserModule, HttpModule, FormsModule
  ],
  providers: [AuthenticationService, AuthCanActivate],
  bootstrap: [AppComponent]
})
export class AppModule {}
