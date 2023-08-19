import { Component, OnInit, Inject } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { GestioneControlloriService } from 'src/app/services/gestione-controllori.service';
import { Controllore } from 'src/app/model/controllore';

@Component({
  selector: 'app-update-controllore',
  templateUrl: './update-controllore.component.html',
  styleUrls: ['./update-controllore.component.css']
})
export class UpdateControlloreComponent implements OnInit {
  formControl = new FormControl('', [Validators.required]);


  constructor(public dialogRef: MatDialogRef<UpdateControlloreComponent>, @Inject(MAT_DIALOG_DATA) public data: Controllore,
              public controlloriService: GestioneControlloriService) { }

  ngOnInit() {
  }

  getErrorMessage() {
    return this.formControl.hasError('required') ? 'Required field' :
      this.formControl.hasError('email') ? 'Not a valid email' :
        '';
  }


  public confirmEdit(): void {
    this.controlloriService.updateControllore(this.data as Controllore)
    .subscribe(hero => {
      // do something if you want
    });

  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  submit() {}
}


