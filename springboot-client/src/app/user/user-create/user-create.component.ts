import {Component, OnDestroy, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {UserService} from '../user.service';
import {User} from '../user';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-user-create',
  templateUrl: './user-create.component.html',
  styleUrls: ['./user-create.component.css'],
  providers: [UserService]
})
export class UserCreateComponent implements OnInit {

  id: number;
  user: User;
  userForm: FormGroup;
  private sub: any;

  constructor(private route: ActivatedRoute,
    private router: Router,
    private userService: UserService) {
    this.userForm = new FormGroup({
      firstName: new FormControl('', Validators.required),
      lastName: new FormControl('', Validators.required),
      userName: new FormControl('', Validators.required),
      email: new FormControl('', [
        Validators.required,
        Validators.pattern('[^ @]*@[^ @]*')
      ]),
      password: new FormControl('', Validators.required),
    });
  }

  ngOnInit() {
    console.log('onInit');
    this.sub = this.route.params.subscribe(params => {
      this.id = params['id'];
    });

    if (this.id) {
      this.userService.findById(this.id).subscribe(
        user => {
          this.id = user.id;
          this.userForm.patchValue({
            firstName: user.firstName,
            lastName: user.lastName,
            email: user.email,
            userName: user.userName
          });
        }, error => {
          this.router.navigate(['login']);
          console.log(error);
        }
      );

    }
  }

  onSubmit() {
    if (this.userForm.valid) {
      if (this.id) {
        const user: User = new User(this.id,
          this.userForm.controls['firstName'].value,
          this.userForm.controls['lastName'].value,
          this.userForm.controls['userName'].value,
          this.userForm.controls['email'].value,
          this.userForm.controls['password'].value);
        this.userService.updateUser(user).subscribe();
      } else {
        const user: User = new User(null,
          this.userForm.controls['firstName'].value,
          this.userForm.controls['lastName'].value,
          this.userForm.controls['userName'].value,
          this.userForm.controls['email'].value,
          this.userForm.controls['password'].value);
        this.userService.saveUser(user).subscribe();
      }
      this.userForm.reset();
      this.router.navigate(['/users/list']);
    }
  }

  redirectUserPage() {
    this.router.navigate(['/users/list']);
  }
}
