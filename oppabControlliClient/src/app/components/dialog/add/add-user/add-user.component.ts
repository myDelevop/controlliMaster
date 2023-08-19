import { Component, OnInit, Inject } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { Utente } from 'src/app/model/utente';
import { GestioneUtentiService } from 'src/app/services/gestione-utenti.service';

@Component({
  selector: 'app-add-user',
  templateUrl: './add-user.component.html',
  styleUrls: ['./add-user.component.css']
})

export class AddUserComponent implements OnInit {
  formControl = new FormControl('', [Validators.required, Validators.email]);

  constructor(public dialogRef: MatDialogRef<AddUserComponent>, @Inject(MAT_DIALOG_DATA) public data: Utente,
              public dataService: GestioneUtentiService) {}

  ngOnInit() {}

  getErrorMessage() {
    return this.formControl.hasError('required') ? 'Required field' :
      this.formControl.hasError('email') ? 'Not a valid email' : '';
  }

  public confirmAdd(): void {
    this.dataService.addUser(this.data as Utente)
    .subscribe(hero => {
      // do something if you want
    });
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  submit() {}

}
