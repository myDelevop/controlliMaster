import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { Utente } from 'src/app/model/utente';
import { GestioneUtentiService } from 'src/app/services/gestione-utenti.service';


@Component({
  selector: 'app-delete-user',
  templateUrl: './delete-user.component.html',
  styleUrls: ['./delete-user.component.css']
})
export class DeleteUserComponent implements OnInit {

  constructor(public dialogRef: MatDialogRef<DeleteUserComponent>, @Inject(MAT_DIALOG_DATA) public data: Utente,
              public dataService: GestioneUtentiService) { }

  ngOnInit() {}

  onNoClick(): void {
    this.dialogRef.close();
  }

  confirmDelete(): void {
    this.dataService.deleteUser(this.data)
      .subscribe(response => {
        // do something if you want
      });
  }
}
