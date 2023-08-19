import { AssegnazioneControlliService } from 'src/app/services/assegnazione-controlli.service';
import { GestioneStazioniForestaliService } from 'src/app/services/gestione-stazioni-forestali.service';
import { GestioneAziendaService } from './../../../../services/gestione-azienda.service';
import { AziendaStazioneForestale } from 'src/app/model/AziendaStazioneForestale';
import { Azienda } from 'src/app/model/azienda';
import { Component, OnInit, Inject } from '@angular/core';


import {MomentDateAdapter} from '@angular/material-moment-adapter';
import {DateAdapter,  MAT_DATE_LOCALE} from '@angular/material/core';
import {MatDatepicker} from '@angular/material/datepicker';
import * as _moment from 'moment';
import { Moment } from 'moment';
import { FormControl, Validators } from '@angular/forms';
import { StazioneForestale } from 'src/app/model/stazioneForestale';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';

@Component({
  selector: 'app-add-azienda-staz-forestale',
  templateUrl: './add-azienda-staz-forestale.component.html',
  styleUrls: ['./add-azienda-staz-forestale.component.css'],
  providers: [
    {provide: DateAdapter, useClass: MomentDateAdapter, deps: [MAT_DATE_LOCALE]}
  ]
})
export class AddAziendaStazForestaleComponent implements OnInit {

  formControl = new FormControl('', [Validators.required]);
  stazioniForestali: StazioneForestale[];
  aziende: Azienda[];

  aziendeStazioneForestali: AziendaStazioneForestale[];

  // rimuovo i gruppi del selectedUser. L'elenco completo lo memorizzo in usersGroups facendo un'unica richiesta http
  filteredAziende: Azienda[];

  selectedStazioneForestale = null;
  selectedAzienda = null;


  constructor(
    public dialogRef: MatDialogRef<AddAziendaStazForestaleComponent>,
    @Inject(MAT_DIALOG_DATA) public data: AziendaStazioneForestale,
    public stazioniForestaliService: GestioneStazioniForestaliService,
    public assegnazioneControlliService: AssegnazioneControlliService,
    public aziendaService: GestioneAziendaService) {}

  ngOnInit() {
    this.initializeData();
  }

  initializeData(): void {
    this.stazioniForestaliService.getStazioniForestali().subscribe(response => {
        this.stazioniForestali = response;
      });

    this.aziendaService.getAziende().subscribe(response => {
      this.aziende = response;
    });

    this.assegnazioneControlliService.getAziendeStazioniForestali()
    .subscribe(response => {
      this.aziendeStazioneForestali = response;
  });

  }

  getErrorMessage() {
    return this.formControl.hasError('required') ? 'Required field' :
      this.formControl.hasError('email') ? 'Not a valid email' : '';
  }

  updateAziendeList(event) {
    if (event.isUserInput) {
      this.filteredAziende = this.aziende;
      const currentIds: number[] =
            this.aziendeStazioneForestali.filter(
              x => x.stazioneForestale.idStazioneForestale === event.source.value.idStazioneForestale).map(
              y => y.azienda.idAzienda);
      this.filteredAziende = this.aziende.filter(
              obj => !currentIds.includes(obj.idAzienda));
    }
  }

  public confirmAdd(): void {
    this.data.stazioneForestale = this.selectedStazioneForestale;
    this.data.azienda = this.selectedAzienda;
    this.assegnazioneControlliService.addAziendaStazioneForestale(this.data as AziendaStazioneForestale)
    .subscribe(hero => {
      // do something if you want
    });
  }

  setCampagna(normalizedYear: Moment, datepicker: MatDatepicker<Moment>) {
    this.data.campagna = normalizedYear.year();
    datepicker.close();
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  submit() {}
}
