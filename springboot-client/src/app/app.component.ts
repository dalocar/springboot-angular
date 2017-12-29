import {Component} from '@angular/core';
import {AuthenticationService} from './login/authentication.service';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Auth App';
  constructor(private authenticationService: AuthenticationService) {
  }
  isLoggedIn(): boolean {
    return this.authenticationService.isLoggedIn();
  }
}
