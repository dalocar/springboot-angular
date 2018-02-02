import {DateFormat} from '../date/date-format';
import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {CustomerRoutingModule} from './customer-routing.module';
import {CustomerCreateComponent} from './customer-create/customer-create.component';
import {FormsModule, ReactiveFormsModule, FormBuilder, FormGroup} from '@angular/forms';
import {CustomerListComponent} from './customer-list/customer-list.component';
import {AuthCanActivate} from '../login/auth.canActivate';
import {NgxPaginationModule} from 'ngx-pagination';
import {
  MatButtonModule, MatCheckboxModule, MatTableModule, MatPaginatorModule, MatSortModule
  , MatInputModule, MatCardModule, MatDatepickerModule
  , MatListModule, MatSidenavModule
  , MatDialogModule, MatProgressBarModule, MatRippleModule, MatIconModule
  , MatGridListModule
} from '@angular/material';
import {DialogConfirmationComponent} from './customer-list/dialog-confirmation/dialog-confirmation.component';
import {InvoiceDetailComponent} from './invoice-detail/invoice-detail.component';
import {DateAdapter} from '@angular/material/core';



@NgModule({
  imports: [
    CommonModule,
    CustomerRoutingModule,
    FormsModule,
    MatButtonModule,
    MatCardModule,
    MatCheckboxModule,
    MatDatepickerModule,
    MatDialogModule,
    MatGridListModule,
    MatIconModule,
    MatInputModule,
    MatListModule,
    MatPaginatorModule,
    MatProgressBarModule,
    MatRippleModule,
    MatSidenavModule,
    MatSortModule,
    MatTableModule,
    NgxPaginationModule,
    ReactiveFormsModule
  ],
  declarations: [CustomerCreateComponent, CustomerListComponent, DialogConfirmationComponent, InvoiceDetailComponent],
  providers: [AuthCanActivate],
  entryComponents: [DialogConfirmationComponent, InvoiceDetailComponent]
})
export class CustomerModule {
  constructor(private dateAdapter: DateAdapter<Date>) {
    dateAdapter.setLocale('es-es'); // DD/MM/YYYY
  }

}