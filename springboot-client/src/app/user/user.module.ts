import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {UserRoutingModule} from './user-routing.module';
import {UserCreateComponent} from './user-create/user-create.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {UserListComponent} from './user-list/user-list.component';
import {AuthCanActivate} from '../login/auth.canActivate';
import {
  MatTableModule, MatIconModule, MatButtonModule, MatCardModule,
  MatInputModule, MatFormFieldModule, MatRippleModule
} from '@angular/material';
import {MatCheckboxModule} from '@angular/material/checkbox';


@NgModule({
  imports: [
    CommonModule,
    UserRoutingModule,
    FormsModule,
    MatButtonModule,
    MatCardModule,
    MatCheckboxModule,
    MatFormFieldModule,
    MatIconModule,
    MatInputModule,
    MatRippleModule,
    MatTableModule,
    ReactiveFormsModule
  ],
  declarations: [UserCreateComponent, UserListComponent],
  providers: [AuthCanActivate]
})
export class UserModule {}
