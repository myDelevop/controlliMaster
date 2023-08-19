import { Component, OnInit, Inject } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { AddUserComponent } from '../add-user/add-user.component';
import { Gruppo } from 'src/app/model/gruppo';
import { GestioneUtentiService } from 'src/app/services/gestione-utenti.service';

@Component({
  selector: 'app-add-group',
  templateUrl: './add-group.component.html',
  styleUrls: ['./add-group.component.css']
})
export class AddGroupComponent implements OnInit {
  formControl = new FormControl('', [Validators.required]);

  constructor(public dialogRef: MatDialogRef<AddGroupComponent>, @Inject(MAT_DIALOG_DATA) public data: Gruppo,
              public dataService: GestioneUtentiService) {}

  ngOnInit() {
  }

  getErrorMessage() {
    return this.formControl.hasError('required') ? 'Required field' :
      this.formControl.hasError('email') ? 'Not a valid email' : '';
  }

  public confirmAdd(): void {
    this.dataService.addGroup(this.data as Gruppo)
    .subscribe(gruppo => {
      // do something if you want
    });
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  submit() {}
}
