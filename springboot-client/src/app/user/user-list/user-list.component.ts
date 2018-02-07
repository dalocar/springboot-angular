import {Component, OnInit} from '@angular/core';
import {User} from '../user';
import {UserService} from '../user.service';
import {MatTableDataSource, MatTable, MatIcon} from '@angular/material';
import {Router} from '@angular/router';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css'],
  providers: [UserService]
})
export class UserListComponent implements OnInit {

  private users: User[];
  displayedColumns = ['id', 'username', 'name', 'email', 'actions'];
  dataSource = new MatTableDataSource<User>([]);

  constructor(private router: Router, private userService: UserService) {}

  ngOnInit() {
    this.getAllUsers();
  }

  getAllUsers() {
    this.userService.findAll().subscribe(
      users => {
        this.dataSource.data = users;
        this.users = users;
      },
      err => {
        this.router.navigate(['login']);
        console.log(err);
      }

    );
  }

  redirectNewUserPage() {
    this.router.navigate(['/users/create']);
  }

  editUserPage(user: User) {
    if (user) {
      this.router.navigate(['/users/edit', user.id]);
    }
  }

  deleteUser(user: User) {
    if (user) {
      this.userService.deleteUserById(user.id).subscribe(
        res => {
          this.getAllUsers();
          this.router.navigate(['users/list']);
          console.log('done');
        },
        err => {
          this.router.navigate(['login']);
          console.log(err);
        }
      );
    }
  }
}
