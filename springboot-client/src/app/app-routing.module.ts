import {NgModule} from '@angular/core';
import {Routes, RouterModule, PreloadAllModules} from '@angular/router';
import {UserListComponent} from './user/user-list/user-list.component';
import {LoginComponent} from './login/login.component';
import {NotFoundComponent} from './notfound.component';
const routes: Routes = [
  {path: '', redirectTo: '/login', pathMatch: 'full'},
  {path: 'login', component: LoginComponent},
  {path: 'user', redirectTo: '/users'}
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
