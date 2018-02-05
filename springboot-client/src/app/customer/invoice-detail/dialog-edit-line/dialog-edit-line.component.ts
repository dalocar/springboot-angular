import {Customer} from '../../customer';
import {Component, Inject} from '@angular/core';
import {MatTableDataSource, MatPaginator, MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material';
@Component({
  selector: 'app-dialog-confirmation',
  templateUrl: './dialog-edit-line.component.html',
  styleUrls: ['./dialog-edit-line.component.css']
})
export class DialogEditLineComponent {

  constructor(@Inject(MAT_DIALOG_DATA) public data: any) {

  }

}
