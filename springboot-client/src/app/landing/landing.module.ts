import { CustomerModule } from '../customer/customer.module';
import {DateFormat} from '../date/date-format';
import {AuthCanActivate} from '../login/auth.canActivate';
import { UserModule } from '../user/user.module';
import {LandingComponent} from './landing.component';
import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {MatIconModule} from '@angular/material/icon';
import {MatInputModule} from '@angular/material/input';
import { MatListModule } from '@angular/material/list';
import {MatMenuModule} from '@angular/material/menu';
import { MatSidenavModule } from '@angular/material/sidenav';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatTooltipModule} from '@angular/material/tooltip';
import { NgPipesModule } from 'ngx-pipes';


@NgModule({
  imports: [
    CommonModule,
    MatIconModule,
    MatInputModule,
    MatListModule,
    MatMenuModule,
    MatSidenavModule,
    MatToolbarModule,
    MatTooltipModule,
    NgPipesModule,
    UserModule,
    CustomerModule
  ],
  declarations: [LandingComponent],
  providers: [AuthCanActivate]
})
export class LandingModule {
  constructor() {
  }

}
