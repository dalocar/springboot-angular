import {Customer} from './customer/customer';
import {CustomerService} from './customer/customer.service';
import {InvoiceService} from './customer/invoice-detail/invoice.service';
import {Component} from '@angular/core';
import {AuthenticationService} from './login/authentication.service';
import {User} from './user/user';
import {UserService} from './user/user.service';
import {DomSanitizer} from '@angular/platform-browser';
import {MatChip} from '@angular/material/chips';
import {MatInput} from '@angular/material/input';
import {MatMenu} from '@angular/material/menu';
import {MatToolbar} from '@angular/material/toolbar';
import {MatTooltip} from '@angular/material/tooltip';
import {Router} from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [UserService, CustomerService, InvoiceService]
})
export class AppComponent {
  title = 'Auth App';
  opened = true;
  selected = 'customers';
  private users: User[];
  private customers: Customer[];
  private filteredCustomers: Customer[];

  constructor(private router: Router, private authenticationService: AuthenticationService,
    sanitizer: DomSanitizer,
    private userService: UserService,
    private customerService: CustomerService) {
  }

  isLoggedIn(): boolean {
    const isLoggedIn = this.authenticationService.isLoggedIn();
    this.opened = isLoggedIn;
    if (!isLoggedIn) {
    }
    return isLoggedIn;
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
      this.router.navigate(['/customers/edit', customer.id]);
    }
  }

  editUserPage(user: User) {
    if (user) {
      this.router.navigate(['/users/edit', user.id]);
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
        this.filteredCustomers = this.customers;
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
      this.filteredCustomers = this.customers.filter(customer =>
        customer.name.concat(customer.address).toLowerCase().includes(filterValue));
    }
  }

}
