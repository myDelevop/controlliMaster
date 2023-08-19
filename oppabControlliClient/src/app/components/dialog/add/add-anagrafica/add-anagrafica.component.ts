import { GestioneAnagraficheService } from './../../../../services/gestione-anagrafiche.service';
import { Anagrafica } from './../../../../model/anagrafica';
import { Component, OnInit, Inject } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import {MomentDateAdapter} from '@angular/material-moment-adapter';
import {DateAdapter, MAT_DATE_FORMATS, MAT_DATE_LOCALE} from '@angular/material/core';
import {MatDatepicker} from '@angular/material/datepicker';
import * as _moment from 'moment';
import { Moment } from 'moment';

@Component({
  selector: 'app-add-anagrafica',
  templateUrl: './add-anagrafica.component.html',
  styleUrls: ['./add-anagrafica.component.css'],
  providers: [
    {provide: DateAdapter, useClass: MomentDateAdapter, deps: [MAT_DATE_LOCALE]}
  ]
})
export class AddAnagraficaComponent implements OnInit {

  formControl = new FormControl([Validators.required, Validators.email]);
  dateFormControl = new FormControl(_moment());

  constructor(public dialogRef: MatDialogRef<AddAnagraficaComponent>, @Inject(MAT_DIALOG_DATA) public data: Anagrafica,
              public dataService: GestioneAnagraficheService) {}

  ngOnInit() {}

  getErrorMessage() {
    return this.formControl.hasError('required') ? 'Required field' :
      this.formControl.hasError('email') ? 'Not a valid email' : '';
  }

  setAnnoValiditaInizio(normalizedYear: Moment, datepicker: MatDatepicker<Moment>) {
    this.data.annoValiditaInizio = normalizedYear.year();
    datepicker.close();
  }

  setAnnoValiditaFine(normalizedYear: Moment, datepicker: MatDatepicker<Moment>) {
    this.data.annoValiditaFine = normalizedYear.year();
    datepicker.close();
  }


  public confirmAdd(): void {
    this.dataService.addAnagrafica(this.data as Anagrafica)
    .subscribe(hero => {
    });
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  submit() {}

}
