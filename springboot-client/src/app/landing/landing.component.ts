import {Customer} from '../customer/customer';
import {CustomerService} from '../customer/customer.service';
import {InvoiceService} from '../customer/invoice-detail/invoice.service';
import {AuthenticationService} from '../login/authentication.service';
import {User} from '../user/user';
import {UserService} from '../user/user.service';
import {Component, OnInit} from '@angular/core';
import {MatIconRegistry} from '@angular/material/icon';
import {DomSanitizer} from '@angular/platform-browser';
import {Router} from '@angular/router';

@Component({
  selector: 'app-landing',
  templateUrl: './landing.component.html',
  styleUrls: ['./landing.component.css']
})
export class LandingComponent implements OnInit {

  opened = true;
  selected = 'customers';
  private users: User[];
  private customers: Customer[];

  constructor(private router: Router, private authenticationService: AuthenticationService,
    iconRegistry: MatIconRegistry,
    sanitizer: DomSanitizer,
    private userService: UserService,
    private invoiceService: InvoiceService,
    private customerService: CustomerService) {}

  ngOnInit() {
  }

  selectCategory(category) {
    console.log(category);
    this.selected = category;
    if (category === 'users') {
      this.getAllUsers();
    }
    if (category === 'customers') {
      this.getAllCustomers('');
    }
  }

  editCustomerPage(customer: Customer) {
    if (customer) {
      this.router.navigate(['/application/customers/edit', customer.id]);
    }
  }

  getAllUsers() {
    this.userService.findAll().subscribe(
      users => {
        this.users = users;
      },
      err => {
        console.log(err);
      }
    );
  }

  getAllCustomers(filter: string) {
    console.log('Here');
    this.customerService.getAll(filter).subscribe(
      customers => {
        this.customers = customers;
      },
      err => {
        console.log('Error here: ' + err);
      }

    );
  }
  applyFilter(filterValue: string) {
    filterValue = filterValue.trim();
    filterValue = filterValue.toLowerCase();
    if (filterValue.length > 3 || filterValue.length === 0) {
      this.getAllCustomers(filterValue);
    }
  }

  navigateTest() {
    console.log('NavigateTest');
    this.router.navigate(['/application/test)']);
  }
}
