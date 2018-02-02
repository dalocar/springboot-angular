import {Component, ViewChild, AfterViewInit} from '@angular/core';
import {Router} from '@angular/router';
import {DataSource} from '@angular/cdk/collections';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/observable/of';
import {animate, state, style, transition, trigger} from '@angular/animations';

import {Customer} from '../customer';
import {CustomerList} from '../customer.list';
import {CustomerService} from '../customer.service';
import {DialogConfirmationComponent} from './dialog-confirmation/dialog-confirmation.component';

import {
  MatDialog, MatDialogRef, MatRippleModule, MatExpansionModule, MatPaginator,
  MatTable, MatSort, MatTableDataSource, PageEvent
} from '@angular/material';

@Component({
  selector: 'app-customer-list',
  templateUrl: './customer-list.component.html',
  styleUrls: ['./customer-list.component.css'],
  providers: [CustomerService],
  animations: [
    trigger('detailExpand', [
      state('collapsed', style({height: '0px', minHeight: '0', visibility: 'hidden'})),
      state('expanded', style({height: '*', visibility: 'visible'})),
      transition('expanded <=> collapsed', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ])
  ],
})
export class CustomerListComponent implements AfterViewInit {

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  displayedColumns = ['id', 'name', 'actions'];
  dataSource = new MatTableDataSource<Customer>([]);
  pageEvent: PageEvent;
  expandedElement: any;
  isExpansionDetailRow = (i, row) => row.hasOwnProperty('detailRow');

  constructor(private router: Router, private customerService: CustomerService,
    private dialog: MatDialog) {}

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
    this.sort.active = 'id';
    this.dataSource.sort = this.sort;
    this.getAllCustomers(0, 10, 'id', null);
  }

  getAllCustomers(pageIndex: number, pageSize: number, sortColumn: string, sortDirection: string) {
    console.log('Here');
    this.customerService.findAll(pageIndex, pageSize, sortColumn, sortDirection).subscribe(
      customers => {
        this.dataSource = new MatTableDataSource<Customer>(customers.customers);
        this.paginator.length = customers.total;
      },
      err => {
        this.router.navigate(['login']);
        console.log('Error here: ' + err);
      }

    );
  }

  onMatSortChange(event) {
    this.getAllCustomers(this.paginator.pageIndex, this.paginator.pageSize, event.active, event.direction);
  }

  onPaginationChange(event) {
    console.log('OnPagination');
    console.log(event);
    this.getAllCustomers(event.pageIndex, event.pageSize, this.sort.active, this.sort.direction);
  }

  openConfirmation(customer: Customer): void {
    const dialogRef = this.dialog.open(DialogConfirmationComponent, {data: customer});

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.deleteCustomer(customer);
      }
    });
  }

  redirectNewCustomerPage() {
    this.router.navigate(['/customers/create']);
  }

  editCustomerPage(customer: Customer) {
    if (customer) {
      this.router.navigate(['/customers/edit', customer.id]);
    }
  }

  deleteCustomer(customer: Customer) {
    if (customer) {
      this.customerService.deleteCustomerById(customer.id).subscribe(
        res => {
          this.getAllCustomers(this.pageEvent.pageIndex, this.pageEvent.pageSize, this.sort.active, this.sort.direction);
          this.router.navigate(['customers/list']);
          console.log('done');
        },
        err => {
          console.log(err);
        }
      );
    }
  }
}

// export class ExampleDataSource extends DataSource<any> {
//
//  data: Customer[];
//
//  constructor(data: Customer[]) {
//    super();
//    this.data = data;
//  }
//  /** Connect function called by the table to retrieve one stream containing the data to render. */
//  connect(): Observable<Customer[]> {
//    const rows = [];
//    console.log('Here!!');
//    this.data.forEach(element => rows.push(element, {detailRow: true, element}));
//    console.log(rows);
//    return Observable.of(rows);
//  }
//
//  disconnect() {}
// }
