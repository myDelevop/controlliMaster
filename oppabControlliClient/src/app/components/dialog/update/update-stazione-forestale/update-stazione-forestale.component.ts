import { Component, OnInit, Inject } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { StazioneForestale } from 'src/app/model/stazioneForestale';
import { GestioneStazioniForestaliService } from 'src/app/services/gestione-stazioni-forestali.service';
import {MomentDateAdapter} from '@angular/material-moment-adapter';
import {DateAdapter,  MAT_DATE_LOCALE} from '@angular/material/core';
import {MatDatepicker} from '@angular/material/datepicker';
import * as _moment from 'moment';
import { Moment } from 'moment';

@Component({
  selector: 'app-update-stazione-forestale',
  templateUrl: './update-stazione-forestale.component.html',
  styleUrls: ['./update-stazione-forestale.component.css'],
  providers: [
    {provide: DateAdapter, useClass: MomentDateAdapter, deps: [MAT_DATE_LOCALE]}
  ]
})
export class UpdateStazioneForestaleComponent implements OnInit {

  formControl = new FormControl('', [Validators.required]);


  constructor(public dialogRef: MatDialogRef<UpdateStazioneForestaleComponent>, @Inject(MAT_DIALOG_DATA) public data: StazioneForestale,
              public stazioniForestaliService: GestioneStazioniForestaliService) { }

  ngOnInit() {
  }

  getErrorMessage() {
    return this.formControl.hasError('required') ? 'Required field' :
      this.formControl.hasError('email') ? 'Not a valid email' :
        '';
  }

  setAnnoValiditaInizio(normalizedYear: Moment, datepicker: MatDatepicker<Moment>) {
    this.data.annoValiditaInizio = normalizedYear.year();
    datepicker.close();
  }

  setAnnoValiditaFine(normalizedYear: Moment, datepicker: MatDatepicker<Moment>) {
    this.data.annoValiditaFine = normalizedYear.year();
    datepicker.close();
  }

  public confirmEdit(): void {
    this.stazioniForestaliService.updateStazioneForestale(this.data as StazioneForestale)
    .subscribe(hero => {
      // do something if you want
    });

  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  submit() {}
}
