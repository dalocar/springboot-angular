import {LoaderService} from '../loader/loader.service';
import {AuthenticationService} from '../login/authentication.service';
import {Injectable} from '@angular/core';
import {Customer} from './customer';
import {CustomerList} from './customer.list';
import {Headers, Http, Response} from '@angular/http';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import {Observable} from 'rxjs/Observable';

@Injectable()
export class CustomerService {
  private apiUrl = 'http://localhost:8080/customer';

  private headers = new Headers({
    'Content-Type': 'application/json',
    'Authorization': 'Bearer ' + this.authenticationService.getToken()
  });
  constructor(private http: Http, private authenticationService: AuthenticationService) {
  }

  findAll(page: number, size: number, sortColumn: string, sortDirection: string): Observable<CustomerList> {
    return this.http.get(this.apiUrl,
      {headers: this.headers, params: {page: page, size: size, sortColumn: sortColumn, sortDirection: sortDirection}})
      .map((res: Response) => res.json())
      .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
  }

  getAll(filter: string): Observable<Customer[]> {
    return this.http.get(this.apiUrl + '/all',
      {headers: this.headers, params: {filter: filter}})
      .map((res: Response) => res.json())
      .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
  }

  findById(id: number): Observable<Customer> {
    return this.http.get(this.apiUrl + '/' + id, {headers: this.headers})
      .map((res: Response) => res.json())
      .catch((error: any) => Observable.throw(error.json().error || 'Error'));
  }

  saveCustomer(customer: Customer): Observable<Customer> {
    return this.http.post(this.apiUrl, customer, {headers: this.headers})
      .map((res: Response) => res.json())
      .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
  }

  deleteCustomerById(id: number): Observable<boolean> {
    return this.http.delete(this.apiUrl + '/' + id, {headers: this.headers})
      .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
  }

  updateCustomer(customer: Customer): Observable<Customer> {
    return this.http.put(this.apiUrl, customer, {headers: this.headers})
      .map((res: Response) => res.json())
      .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
  }
}
