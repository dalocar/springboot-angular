import {NgModule} from '@angular/core';
import {Routes, RouterModule, PreloadAllModules} from '@angular/router';
import {UserListComponent} from './user/user-list/user-list.component';
import {CustomerListComponent} from './customer/customer-list/customer-list.component';
import {InvoiceDetailComponent} from './customer/invoice-detail/invoice-detail.component';
import { LandingComponent } from './landing/landing.component';
import {LoginComponent} from './login/login.component';
import {NotFoundComponent} from './notfound.component';
const routes: Routes = [
  {path: '', redirectTo: '/login', pathMatch: 'full'},
  {path: 'login', component: LoginComponent},
  {path: 'icons', redirectTo: '/icons'},
  {path: 'user', redirectTo: '/users'},
  {path: 'customer', redirectTo: '/customers'},
  {path: 'invoice', redirectTo: '/invoices'},
  {path: 'landing', component: LandingComponent}
  //  {path: '**', component: NotFoundComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}

export const AppRouting = RouterModule.forRoot(routes, {
  useHash: true,
  preloadingStrategy: PreloadAllModules
});

