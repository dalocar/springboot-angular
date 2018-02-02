import {Customer} from '../../customer';
import {Component, Inject} from '@angular/core';
import {MatTableDataSource, MatPaginator, MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material';
@Component({
  selector: 'app-dialog-confirmation',
  templateUrl: './dialog-confirmation.component.html',
  styleUrls: ['./dialog-confirmation.component.css']
})
export class DialogConfirmationComponent {

  constructor(@Inject(MAT_DIALOG_DATA) public data: any) {

  }

}
