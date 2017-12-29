import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {UserRoutingModule} from './user-routing.module';
import {UserCreateComponent} from './user-create/user-create.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {UserListComponent} from './user-list/user-list.component';
import {AuthCanActivate} from '../login/auth.canActivate';

@NgModule({
  imports: [
    CommonModule,
    UserRoutingModule,
    FormsModule,
    ReactiveFormsModule
  ],
  declarations: [UserCreateComponent, UserListComponent],
  providers: [AuthCanActivate]
})
export class UserModule {}
