import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { DeleteUserComponent } from '../delete-user/delete-user.component';
import { GestioneUtentiService } from 'src/app/services/gestione-utenti.service';
import { UtenteGruppo } from 'src/app/model/utenteGruppo';

@Component({
  selector: 'app-delete-user-group',
  templateUrl: './delete-user-group.component.html',
  styleUrls: ['./delete-user-group.component.css']
})
export class DeleteUserGroupComponent implements OnInit {

  constructor(public dialogRef: MatDialogRef<DeleteUserComponent>, @Inject(MAT_DIALOG_DATA) public data: UtenteGruppo,
              public dataService: GestioneUtentiService) { }

  ngOnInit() {}

  onNoClick(): void {
    this.dialogRef.close();
  }

  confirmDelete(): void {
    this.dataService.deleteUserGroup(this.data.id)
      .subscribe(response => {
        // do something if you want
      });
  }
}
