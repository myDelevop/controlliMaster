import { ChecklistTemplate } from 'src/app/model/checklistTemaplate';
import { GestioneChecklistTemplateService } from 'src/app/services/gestione-checklist-template.service';
import { GestioneAnagraficheService } from './../../../../services/gestione-anagrafiche.service';
import { ChecklistRegola } from 'src/app/model/checklistRegola';
import { ChecklistRegoleService } from './../../../../services/regole-checklist.service';
import { Component, OnInit, Inject } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { Controllo } from 'src/app/model/controllo';
import {MomentDateAdapter} from '@angular/material-moment-adapter';
import {DateAdapter, MAT_DATE_FORMATS, MAT_DATE_LOCALE} from '@angular/material/core';
import {MatDatepicker} from '@angular/material/datepicker';
import * as _moment from 'moment';
import { Moment } from 'moment';

@Component({
  selector: 'app-add-regola-checklist',
  templateUrl: './add-regola-checklist.component.html',
  styleUrls: ['./add-regola-checklist.component.css'],
  providers: [
    {provide: DateAdapter, useClass: MomentDateAdapter, deps: [MAT_DATE_LOCALE]}
  ]
})
export class AddRegolaChecklistComponent implements OnInit {

  formControl = new FormControl('', [Validators.required]);

  tipoControlli: string[];
  tipiCampione: string[];
  misure: string[];
  interventi: string[];
  sottoInterventi: string[];
  checklistTemplates: ChecklistTemplate[];

  selectedTipoControllo: string;
  selectedTipoCampione: string;
  selectedMisura: string;
  selectedIntervento: string;
  selectedSottoIntervento: string;
  selectedChecklistTemplate: ChecklistTemplate;


  constructor(public dialogRef: MatDialogRef<AddRegolaChecklistComponent>, @Inject(MAT_DIALOG_DATA) public data: ChecklistRegola,
              public checklistRegolaService: ChecklistRegoleService, public gestioneAnagraficheService: GestioneAnagraficheService,
              public checklistTemplateService: GestioneChecklistTemplateService) {}

  ngOnInit() {
    this.initializeData();
  }

  initializeData(): void {

    this.gestioneAnagraficheService.getAnagraficheForChiave('REGOLE_TIPO_CONTROLLO', 'IT').subscribe(res => {
      this.tipiCampione = res.sort();
    });

    this.gestioneAnagraficheService.getAnagraficheForChiave('REGOLE_TIPO_CAMPIONE', 'IT').subscribe(res => {
      this.tipoControlli = res.sort();
    });

    this.gestioneAnagraficheService.getListAnagrMisura().subscribe(res => {
      this.misure = res.sort();
    });

    this.checklistTemplateService.getChecklistTemplates().subscribe(res => {
      this.checklistTemplates = res; // .map(x => x.descrIT);
    });

  }

  getErrorMessage() {
    return this.formControl.hasError('required') ? 'Required field' :
      this.formControl.hasError('email') ? 'Not a valid email' : '';
  }

  public confirmAdd(): void {
    this.data.tipoControllo = this.selectedTipoControllo;
    this.data.tipoCampione = this.selectedTipoCampione;
    this.data.misura = this.selectedMisura;
    this.data.intervento = this.selectedIntervento;

    this.data.sottoIntervento = this.selectedSottoIntervento;
    this.data.controllo = new Controllo();

    this.data.controllo.idControllo = 1;
    this.data.idChecklistTemplate = this.selectedChecklistTemplate.idChecklistTemplate;

    this.checklistRegolaService.addChecklistRegola(this.data as ChecklistRegola)
    .subscribe(hero => {
      // do something if you want
    });
  }

  setAnno(normalizedYear: Moment, datepicker: MatDatepicker<Moment>) {
    this.data.anno = normalizedYear.year();
    datepicker.close();
  }

  setCampagna(normalizedYear: Moment, datepicker: MatDatepicker<Moment>) {
    this.data.campagna = normalizedYear.year();
    datepicker.close();
  }


  updateInterventoList(event) {
    if (event.isUserInput) {
      this.gestioneAnagraficheService.getListAnagrIntervento(event.source.value).subscribe(res => {
        this.interventi = res.sort();
        this.sottoInterventi = [];
      });
    }
  }

  updateSottoInterventoList(event) {
    if (event.isUserInput) {
      this.gestioneAnagraficheService.getListAnagrSottoIntervento(event.source.value).subscribe(res => {
        this.sottoInterventi = res.sort();
      });
    }
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  submit() {}
}
