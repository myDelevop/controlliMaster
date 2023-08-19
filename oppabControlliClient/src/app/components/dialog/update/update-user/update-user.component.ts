import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { FormControl, Validators } from '@angular/forms';
import { Utente } from 'src/app/model/utente';
import { GestioneUtentiService } from 'src/app/services/gestione-utenti.service';

@Component({
  selector: 'app-update-user',
  templateUrl: './update-user.component.html',
  styleUrls: ['./update-user.component.css']
})
export class UpdateUserComponent implements OnInit {

  formControl = new FormControl('', [Validators.required]);

  constructor(public dialogRef: MatDialogRef<UpdateUserComponent>, @Inject(MAT_DIALOG_DATA) public data: Utente,
              public dataService: GestioneUtentiService) { }

  ngOnInit() {}


  getErrorMessage() {
    return this.formControl.hasError('required') ? 'Required field' :
      this.formControl.hasError('email') ? 'Not a valid email' :
        '';
  }


  public confirmEdit(): void {
    this.dataService.updateUser(this.data as Utente)
    .subscribe(hero => {
      // do something if you want
    });

  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  submit() {}
}
