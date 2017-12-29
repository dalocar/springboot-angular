import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {UserListComponent} from './user-list/user-list.component';
import {UserCreateComponent} from './user-create/user-create.component';
import {AuthCanActivate} from '../login/auth.canActivate';

const routes: Routes = [
  {
    path: 'users',
    children: [
      {path: 'list', component: UserListComponent, canActivate: [AuthCanActivate]},
      {path: 'create', component: UserCreateComponent, canActivate: [AuthCanActivate]},
      {path: 'edit/:id', component: UserCreateComponent, canActivate: [AuthCanActivate]}
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserRoutingModule {}
