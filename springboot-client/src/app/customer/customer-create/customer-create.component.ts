import {Component, OnDestroy, ViewChild, OnInit, ChangeDetectorRef, AfterViewInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {CustomerService} from '../customer.service';
import {Customer} from '../customer';
import {DialogConfirmationComponent} from '../customer-list/dialog-confirmation/dialog-confirmation.component';
import {Invoice} from '../invoice';
import {InvoiceDetailComponent} from '../invoice-detail/invoice-detail.component';
import {InvoiceService} from '../invoice-detail/invoice.service';
import {ActivatedRoute, Router} from '@angular/router';
import {MatTableDataSource, MatTab, MatTabGroup, MatTabChangeEvent} from '@angular/material';
import {MatDialog} from '@angular/material/dialog';
import {MatPaginator} from '@angular/material/paginator';


@Component({
  selector: 'app-customer-create',
  templateUrl: './customer-create.component.html',
  styleUrls: ['./customer-create.component.css'],
  providers: [CustomerService]
})
export class CustomerCreateComponent implements AfterViewInit {
  @ViewChild(MatPaginator) paginator: MatPaginator;

  id: number;
  customer: Customer;
  customerForm: FormGroup;
  private sub: any;
  dataSource;
  years: number[];
  currentPage: number;
  currentTab: number;
  displayedColumns = ['id', 'year', 'date', 'actions'];

  constructor(private route: ActivatedRoute,
    private router: Router,
    private cd: ChangeDetectorRef,
    private customerService: CustomerService,
    private invoiceService: InvoiceService,
    private dialog: MatDialog) {

    this.customerForm = new FormGroup({
      name: new FormControl('', Validators.required),
      address: new FormControl('', Validators.required),
      cif: new FormControl('', Validators.required),
      city: new FormControl('', [Validators.required]),
      fax: new FormControl(''),
      phone: new FormControl(''),
      postCode: new FormControl(''),
      province: new FormControl(''),
      showInList: new FormControl(''),
    });
    this.dataSource = new MatTableDataSource<Invoice>([]);
    this.currentPage= 0;
    this.currentTab= 0;
  }


  ngAfterViewInit(): void {
    this.dataSource.paginator = this.paginator;
    this.sub = this.route.params.subscribe(params => {
      this.id = params['id'];
      this.customerService.findById(this.id).subscribe(
        customer => {
          // this.id = customer.id;
          this.customerForm.patchValue({
            id: this.id,
            name: customer.name,
            address: customer.address,
            cif: customer.cif,
            city: customer.city,
            fax: customer.fax,
            phone: customer.phone,
            postCode: customer.postCode,
            province: customer.province,
            showInList: customer.showInList
          });
          this.customerService.invoicingYears(customer.id).subscribe(years => {
            this.years = years;
            this.currentTab = years[0];
            this.invoiceService.findByYearAndCustomerId(years[0], customer.id, 0, 5).subscribe(
              invoices => {
                this.dataSource = new MatTableDataSource<Invoice>(invoices.invoices);
                this.paginator.length = invoices.total;
              }, error => {
                this.dataSource = new MatTableDataSource<Invoice>([]);
                this.paginator.length = 0;
              });
          });
          console.log(this.dataSource);
        }, error => {
          this.router.navigate(['login']);
          console.log(error);
        }
      );
    });

    this.cd.detectChanges();
  }

  onSubmit() {
    if (this.customerForm.valid) {
      if (this.id) {
        const customer: Customer = new Customer(this.id,
          this.customerForm.controls['name'].value,
          this.customerForm.controls['address'].value,
          this.customerForm.controls['postCode'].value,
          this.customerForm.controls['city'].value,
          this.customerForm.controls['province'].value,
          this.customerForm.controls['cif'].value,
          this.customerForm.controls['phone'].value,
          this.customerForm.controls['fax'].value,
          this.customerForm.controls['showInList'].value, []);
        this.customerService.updateCustomer(customer).subscribe();
      } else {
        const customer: Customer = new Customer(null,
          this.customerForm.controls['name'].value,
          this.customerForm.controls['address'].value,
          this.customerForm.controls['postCode'].value,
          this.customerForm.controls['city'].value,
          this.customerForm.controls['province'].value,
          this.customerForm.controls['cif'].value,
          this.customerForm.controls['phone'].value,
          this.customerForm.controls['fax'].value,
          this.customerForm.controls['showInList'].value, []);
        this.customerService.saveCustomer(customer).subscribe();
      }
      this.customerForm.reset();
      this.router.navigate(['/customers/list']);
    }
  }

  redirectCustomerPage() {
    this.router.navigate(['/landing']);
  }

  editInvoicePage(invoice: Invoice) {
    const path = '/invoices/' + invoice.year + '/' + invoice.id;
    this.router.navigate([path]);
  }

  tabChanged = (tabChangeEvent: MatTabChangeEvent): void => {

    this.invoiceService.findByYearAndCustomerId(this.years[tabChangeEvent.index], this.id, 0, 5).subscribe(
      invoices => {
        this.dataSource = new MatTableDataSource<Invoice>(invoices.invoices);
      });
  }

  onPaginationChange(event) {
    this.invoiceService.findByYearAndCustomerId(this.currentTab, this.id, event.pageIndex, 5).subscribe(
      invoices => {
        this.dataSource = new MatTableDataSource<Invoice>(invoices.invoices);
      });
  }

  openConfirmation(element) {
    const dialogRef = this.dialog.open(DialogConfirmationComponent, {data: element});

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        //        this.deleteCustomer(customer);
      }
    });
  }
}
