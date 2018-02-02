import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {CustomerListComponent} from './customer-list/customer-list.component';
import {CustomerCreateComponent} from './customer-create/customer-create.component';
import {AuthCanActivate} from '../login/auth.canActivate';
import { InvoiceDetailComponent } from './invoice-detail/invoice-detail.component';

const routes: Routes = [
  {
    path: 'customers',
    children: [
      {path: 'list', component: CustomerListComponent, canActivate: [AuthCanActivate]},
      {path: 'create', component: CustomerCreateComponent, canActivate: [AuthCanActivate]},
      {path: 'edit/:id', component: CustomerCreateComponent, canActivate: [AuthCanActivate]}
    ]
  },
 {
   path: 'invoices',
   children: [
      { path: ':year/:id', component: InvoiceDetailComponent, canActivate: [AuthCanActivate]}
   ]
 }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CustomerRoutingModule {}
