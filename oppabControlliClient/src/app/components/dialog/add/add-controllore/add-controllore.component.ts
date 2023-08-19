import { Controllore } from './../../../../model/controllore';
import { Component, OnInit, Inject } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { GestioneControlloriService } from 'src/app/services/gestione-controllori.service';

@Component({
  selector: 'app-add-controllore',
  templateUrl: './add-controllore.component.html',
  styleUrls: ['./add-controllore.component.css']
})
export class AddControlloreComponent implements OnInit {

  formControl = new FormControl('', [Validators.required]);

  constructor(public dialogRef: MatDialogRef<AddControlloreComponent>, @Inject(MAT_DIALOG_DATA) public data: Controllore,
              public controlloriService: GestioneControlloriService) { }

  ngOnInit() {
  }

  getErrorMessage() {
    return this.formControl.hasError('required') ? 'Required field' :
      this.formControl.hasError('email') ? 'Not a valid email' : '';
  }

  public confirmAdd(): void {
    this.controlloriService.addControllore(this.data as Controllore)
    .subscribe(hero => {
      // do something if you want
    });
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  submit() {}

}
