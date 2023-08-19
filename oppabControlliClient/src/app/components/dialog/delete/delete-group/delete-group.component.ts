import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { DeleteUserComponent } from '../delete-user/delete-user.component';
import { Gruppo } from 'src/app/model/gruppo';
import { GestioneUtentiService } from 'src/app/services/gestione-utenti.service';

@Component({
  selector: 'app-delete-group',
  templateUrl: './delete-group.component.html',
  styleUrls: ['./delete-group.component.css']
})
export class DeleteGroupComponent implements OnInit {

  constructor(public dialogRef: MatDialogRef<DeleteUserComponent>, @Inject(MAT_DIALOG_DATA) public data: Gruppo,
              public dataService: GestioneUtentiService) { }

  ngOnInit() {
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  confirmDelete(): void {
    this.dataService.deleteGroup(this.data)
      .subscribe(response => {
        // do something if you want
      });
  }

}
