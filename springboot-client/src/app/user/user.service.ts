import {Injectable} from '@angular/core';
import {User} from './user';
import {AuthenticationService} from '../login/authentication.service';
import {Headers, Http, Response} from '@angular/http';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import {Observable} from 'rxjs/Observable';

@Injectable()
export class UserService {
  private apiUrl = 'http://localhost:8080/user';

  private headers = new Headers({
    'Content-Type': 'application/json',
    'Authorization': 'Bearer ' + this.authenticationService.getToken()
  });
  constructor(private http: Http, private authenticationService: AuthenticationService) {
  }

  findAll(): Observable<User[]> {
    return this.http.get(this.apiUrl, {headers: this.headers})
      .map((res: Response) => res.json())
      .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
  }

  findById(id: number): Observable<User> {
    return this.http.get(this.apiUrl + '/' + id, {headers: this.headers})
      .map((res: Response) => res.json())
      .catch((error: any) => Observable.throw(error.json().error || 'Error'));
  }

  saveUser(user: User): Observable<User> {
    return this.http.post(this.apiUrl, user, {headers: this.headers})
      .map((res: Response) => res.json())
      .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
  }

  deleteUserById(id: number): Observable<boolean> {
    return this.http.delete(this.apiUrl + '/' + id, {headers: this.headers})
      .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
  }

  updateUser(user: User): Observable<User> {
    return this.http.put(this.apiUrl, user, {headers: this.headers})
      .map((res: Response) => res.json())
      .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
  }
}
