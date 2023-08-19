import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { FormControl, Validators } from '@angular/forms';
import { GestioneUtentiService } from 'src/app/services/gestione-utenti.service';
import { UpdateUserComponent } from '../update-user/update-user.component';
import { Gruppo } from 'src/app/model/gruppo';


@Component({
  selector: 'app-update-group',
  templateUrl: './update-group.component.html',
  styleUrls: ['./update-group.component.css']
})
export class UpdateGroupComponent implements OnInit {

  formControl = new FormControl('', [Validators.required]);

  constructor(public dialogRef: MatDialogRef<UpdateUserComponent>, @Inject(MAT_DIALOG_DATA) public data: Gruppo,
              public dataService: GestioneUtentiService) { }

  ngOnInit() {
  }

  public confirmEdit(): void {
    this.dataService.updateGroup(this.data as Gruppo)
    .subscribe(hero => {
      // do something if you want
    });

  }

  getErrorMessage() {
    return this.formControl.hasError('required') ? 'Required field' :
      this.formControl.hasError('email') ? 'Not a valid email' :
        '';
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  submit() {}

}
