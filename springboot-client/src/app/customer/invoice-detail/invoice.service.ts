import {AuthenticationService} from '../../login/authentication.service';
import {Injectable} from '@angular/core';
import {Headers, Http, Response} from '@angular/http';
import {Observable} from 'rxjs/Observable';

@Injectable()
export class InvoiceService {

  private apiUrl = 'http://localhost:8080/invoice';
  private apiCustomerUrl = 'http://localhost:8080/customer';

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


  findByYearAndCustomerId(year: number, customerId: number, page: number, size: number) {
    console.log('Invoice service.findByYearAndCustomerId');
    return this.http.get(this.apiCustomerUrl + '/' + customerId + '/invoices/' + year,
      {headers: this.headers, params: {page: page, size: size}})
      .map((res: Response) => res.json())
      .catch((error: any) => Observable.throw(error.json().error || 'Error'));
  }
}
