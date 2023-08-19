import { ControlloreStazioneForestale } from './../../../../model/controlloreStazForestale';
import { Controllore } from './../../../../model/controllore';
import { Component, OnInit, Inject } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { StazioneForestale } from 'src/app/model/stazioneForestale';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { GestioneControlloriService } from 'src/app/services/gestione-controllori.service';
import { GestioneStazioniForestaliService } from 'src/app/services/gestione-stazioni-forestali.service';
import {MomentDateAdapter} from '@angular/material-moment-adapter';
import {DateAdapter,  MAT_DATE_LOCALE} from '@angular/material/core';
import {MatDatepicker} from '@angular/material/datepicker';
import * as _moment from 'moment';
import { Moment } from 'moment';

@Component({
  selector: 'app-add-controllore-staz-forestale',
  templateUrl: './add-controllore-staz-forestale.component.html',
  styleUrls: ['./add-controllore-staz-forestale.component.css'],
  providers: [
    {provide: DateAdapter, useClass: MomentDateAdapter, deps: [MAT_DATE_LOCALE]}
  ]
})
export class AddControlloreStazForestaleComponent implements OnInit {

  formControl = new FormControl('', [Validators.required]);
  controllori: Controllore[];
  stazioniForestali: StazioneForestale[];
  controlloriStazioneForestali: ControlloreStazioneForestale[];

  // rimuovo i gruppi del selectedUser. L'elenco completo lo memorizzo in usersGroups facendo un'unica richiesta http
  filteredStazioniForestali: StazioneForestale[];

  selectedControllore = null;
  selectedStazioneForestale = null;


  constructor(
    public dialogRef: MatDialogRef<AddControlloreStazForestaleComponent>,
    @Inject(MAT_DIALOG_DATA) public data: ControlloreStazioneForestale,
    public controlloriService: GestioneControlloriService,
    public stazioniForestaliService: GestioneStazioniForestaliService) {}

  ngOnInit() {
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
      this.formControl.hasError('email') ? 'Not a valid email' : '';
  }

  updateStazioniForestaliList(event) {
    if (event.isUserInput) {
      this.filteredStazioniForestali = this.stazioniForestali;
      const currentIds: number[] =
            this.controlloriStazioneForestali.filter(
              x => x.controllore.idControllore === event.source.value.idControllore).map(
              y => y.stazioneForestale.idStazioneForestale);
      this.filteredStazioniForestali = this.stazioniForestali.filter(
              obj => !currentIds.includes(obj.idStazioneForestale));
    }
  }

  public confirmAdd(): void {
    this.data.controllore = this.selectedControllore;
    this.data.stazioneForestale = this.selectedStazioneForestale;
    this.controlloriService.addControlloreStazioneForestale(this.data as ControlloreStazioneForestale)
    .subscribe(hero => {
      // do something if you want
    });
  }

  setAnnoValiditaInizio(normalizedYear: Moment, datepicker: MatDatepicker<Moment>) {
    this.data.annoValiditaInizio = normalizedYear.year();
    datepicker.close();
  }

  setAnnoValiditaFine(normalizedYear: Moment, datepicker: MatDatepicker<Moment>) {
    this.data.annoValiditaFine = normalizedYear.year();
    datepicker.close();
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  submit() {}
}
