import {Component, OnDestroy, AfterViewInit, OnInit, ChangeDetectorRef} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {CustomerService} from '../customer.service';
import {Customer} from '../customer';
import {DialogConfirmationComponent} from '../customer-list/dialog-confirmation/dialog-confirmation.component';
import {Invoice} from '../invoice';
import {InvoiceDetailComponent} from '../invoice-detail/invoice-detail.component';
import {ActivatedRoute, Router} from '@angular/router';
import {MatTableDataSource} from '@angular/material';
import {MatDialog} from '@angular/material/dialog';


@Component({
  selector: 'app-customer-create',
  templateUrl: './customer-create.component.html',
  styleUrls: ['./customer-create.component.css'],
  providers: [CustomerService]
})
export class CustomerCreateComponent implements OnInit {

  id: number;
  customer: Customer;
  customerForm: FormGroup;
  private sub: any;
  dataSource;

  displayedColumns = ['id', 'year', 'date'];

  constructor(private route: ActivatedRoute,
    private router: Router,
    private cd: ChangeDetectorRef,
    private customerService: CustomerService,
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
  }


  ngOnInit(): void {

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
          this.dataSource = new MatTableDataSource<Invoice>(customer.invoices);
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
    this.router.navigate(['/customers/list']);
  }

  selectRow(row) {
    const path = '/invoices/' + row.year + '/' + row.id;
    console.log(path);
    this.router.navigate([path]);
  }
}
