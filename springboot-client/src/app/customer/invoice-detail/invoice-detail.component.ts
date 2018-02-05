import {DialogConfirmationComponent} from '../customer-list/dialog-confirmation/dialog-confirmation.component';
import {LineInvoice} from '../lineinvoice';
import {DialogEditLineComponent} from './dialog-edit-line/dialog-edit-line.component';
import {InvoiceService} from './invoice.service';
import {Component, Inject, OnInit} from '@angular/core';
import {FormGroup, FormControl} from '@angular/forms';
import {MatDatepicker} from '@angular/material';
import {DateAdapter} from '@angular/material/core';
import {MatDialog} from '@angular/material/dialog';
import {MatTableDataSource} from '@angular/material/table';
import {ActivatedRoute, Router} from '@angular/router';
import { Location } from '@angular/common';


@Component({
  selector: 'app-invoice-detail',
  templateUrl: './invoice-detail.component.html',
  styleUrls: ['./invoice-detail.component.css'],
  providers: [InvoiceService]
})
export class InvoiceDetailComponent implements OnInit {

  invoiceForm: FormGroup;
  date = new FormControl(new Date());
  private sub: any;
  id: number;
  year: number;
  dataSource;
  displayedColumns = ['lineNumber', 'amount', 'description', 'price', 'vat', 'total', 'actions'];


  constructor(private route: ActivatedRoute, private router: Router, private invoiceService: InvoiceService,
    private dialog: MatDialog, private location: Location) {
    this.invoiceForm = new FormGroup({
      id: new FormControl(''),
      year: new FormControl(''),
      invoiceDate: new FormControl('')
    });
    this.dataSource = new MatTableDataSource<LineInvoice>([]);
  }

  ngOnInit(): void {
    this.sub = this.route.params.subscribe(params => {
      console.log('InvoiceDetailComponent.ngOnInit');
      this.id = params['id'];
      this.year = params['year'];
      this.invoiceService.findByIdAndYear(this.id, this.year).subscribe(
        invoice => {
          // this.id = customer.id;
          this.invoiceForm.patchValue({
            id: this.id,
            year: this.year,
            invoiceDate: (invoice.date)
          });
          this.dataSource = new MatTableDataSource<LineInvoice>(invoice.lines);
          console.log(this.invoiceForm.controls['invoiceDate']);
        }, error => {
          this.router.navigate(['login']);
          console.log(error);
        }
      );
    });
  }
  onSubmit() {

  }

  goBack() {
    this.location.back();
  }

  editCustomerPage(element) {
    const dialogRef = this.dialog.open(DialogEditLineComponent, {data: element});
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
