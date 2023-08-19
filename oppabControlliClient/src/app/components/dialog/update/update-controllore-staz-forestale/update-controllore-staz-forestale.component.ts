import { GestioneStazioniForestaliService } from 'src/app/services/gestione-stazioni-forestali.service';
import { ControlloreStazioneForestale } from 'src/app/model/controlloreStazForestale';
import { Component, OnInit, Inject } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { GestioneControlloriService } from 'src/app/services/gestione-controllori.service';
import { Controllore } from 'src/app/model/controllore';
import { StazioneForestale } from 'src/app/model/stazioneForestale';
import {MomentDateAdapter} from '@angular/material-moment-adapter';
import {DateAdapter,  MAT_DATE_LOCALE} from '@angular/material/core';
import {MatDatepicker} from '@angular/material/datepicker';
import * as _moment from 'moment';
import { Moment } from 'moment';

@Component({
  selector: 'app-update-controllore-staz-forestale',
  templateUrl: './update-controllore-staz-forestale.component.html',
  styleUrls: ['./update-controllore-staz-forestale.component.css'],
  providers: [
    {provide: DateAdapter, useClass: MomentDateAdapter, deps: [MAT_DATE_LOCALE]}
  ]
})
export class UpdateControlloreStazForestaleComponent implements OnInit {

  formControl = new FormControl('', [Validators.required]);

  controllori: Controllore[];
  stazioniForestali: StazioneForestale[];
  controlloriStazioneForestali: ControlloreStazioneForestale[];

  // rimuovo i gruppi del selectedUser. L'elenco completo lo memorizzo in usersGroups facendo un'unica richiesta http
  filteredStazioniForestali: StazioneForestale[];

  selectedControllore = null;
  selectedStazioneForestale = null;

  selected = null;


  constructor(public dialogRef: MatDialogRef<UpdateControlloreStazForestaleComponent>, @Inject(MAT_DIALOG_DATA)
              public data: ControlloreStazioneForestale,
              public controlloriService: GestioneControlloriService,
              public stazioniForestaliService: GestioneStazioniForestaliService) { }

  ngOnInit() {
    this.selected = 'X';
    this.initializeData();
  }

  initializeData(): void {
    this.controlloriService.getControllori().subscribe(response => {
        this.controllori = response;
      });

    this.stazioniForestaliService.getStazioniForestali()
      .subscribe(response => {
        this.stazioniForestali = response;
    });

    this.controlloriService.getControlloriStazForestali()
    .subscribe(response => {
      this.controlloriStazioneForestali = response;
  });

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
//    this.data.controllore = this.selectedControllore;
//    this.data.stazioneForestale = this.selectedStazioneForestale;

    this.controlloriService.updateControlloreStazioneForestale(this.data as ControlloreStazioneForestale)
    .subscribe(hero => {
      // do something if you want
    });

  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  submit() {}
}
