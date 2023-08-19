import { AziendaStazioneForestale } from './../../../../model/AziendaStazioneForestale';
import { Azienda } from './../../../../model/azienda';
import { Component, OnInit, Inject } from '@angular/core';
import {MomentDateAdapter} from '@angular/material-moment-adapter';
import {DateAdapter,  MAT_DATE_LOCALE} from '@angular/material/core';
import {MatDatepicker} from '@angular/material/datepicker';
import * as _moment from 'moment';
import { Moment } from 'moment';
import { FormControl, Validators } from '@angular/forms';
import { StazioneForestale } from 'src/app/model/stazioneForestale';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { GestioneStazioniForestaliService } from 'src/app/services/gestione-stazioni-forestali.service';
import { AssegnazioneControlliService } from 'src/app/services/assegnazione-controlli.service';
import { GestioneAziendaService } from 'src/app/services/gestione-azienda.service';


@Component({
  selector: 'app-update-azienda-staz-forestale',
  templateUrl: './update-azienda-staz-forestale.component.html',
  styleUrls: ['./update-azienda-staz-forestale.component.css'],
  providers: [
    {provide: DateAdapter, useClass: MomentDateAdapter, deps: [MAT_DATE_LOCALE]}
  ]
})
export class UpdateAziendaStazForestaleComponent implements OnInit {
  formControl = new FormControl('', [Validators.required]);

  stazioniForestali: StazioneForestale[];
  aziende: Azienda[];
  aziendeStazioneForestali: AziendaStazioneForestale[];

  // rimuovo i gruppi del selectedUser. L'elenco completo lo memorizzo in usersGroups facendo un'unica richiesta http
  filteredAziende: Azienda[];

  selectedStazioneForestale = null;
  selectedAzienda = null;

  selected = null;


  constructor(public dialogRef: MatDialogRef<UpdateAziendaStazForestaleComponent>, @Inject(MAT_DIALOG_DATA)
              public data: AziendaStazioneForestale,
              // public stazioniForestaliService: GestioneStazioniForestaliService,
              public assegnazioneControlliService: AssegnazioneControlliService,
              /* public aziendaService: GestioneAziendaService */) { }

  ngOnInit() {
    this.selected = 'X';
    this.initializeData();
  }

  initializeData(): void {/*
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
  });*/

  }

  getErrorMessage() {
    return this.formControl.hasError('required') ? 'Required field' :
      this.formControl.hasError('email') ? 'Not a valid email' :
        '';
  }

  setCampagna(normalizedYear: Moment, datepicker: MatDatepicker<Moment>) {
    this.data.campagna = normalizedYear.year();
    datepicker.close();
  }


  public confirmEdit(): void {
//    this.data.controllore = this.selectedControllore;
//    this.data.stazioneForestale = this.selectedStazioneForestale;

    this.assegnazioneControlliService.updateAziendaStazioneForestale(this.data as AziendaStazioneForestale)
    .subscribe(hero => {
      // do something if you want
    });

  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  submit() {}
}
