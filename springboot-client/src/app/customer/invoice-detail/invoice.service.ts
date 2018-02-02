import {AuthenticationService} from '../../login/authentication.service';
import {Injectable} from '@angular/core';
import {Headers, Http, Response} from '@angular/http';
import {Observable} from 'rxjs/Observable';

@Injectable()
export class InvoiceService {

  private apiUrl = 'http://localhost:8080/invoice';

  private headers = new Headers({
    'Content-Type': 'application/json',
    'Authorization': 'Bearer ' + this.authenticationService.getToken()
  });
  constructor(private http: Http, private authenticationService: AuthenticationService) {
  }

  findByIdAndYear(id: number, year: number) {
    console.log('Invoice service.findByIdAndYear');
    return this.http.get(this.apiUrl + '/' + year + '/' + id, {headers: this.headers})
      .map((res: Response) => res.json())
      .catch((error: any) => Observable.throw(error.json().error || 'Error'));
  }
}
