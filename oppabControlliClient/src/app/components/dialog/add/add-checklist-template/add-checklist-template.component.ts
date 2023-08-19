import { GestioneAnagraficheService } from 'src/app/services/gestione-anagrafiche.service';
import { GestioneChecklistTemplateService } from './../../../../services/gestione-checklist-template.service';
import { ChecklistTemplate } from 'src/app/model/checklistTemaplate';
import { Component, OnInit, Inject } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import {MomentDateAdapter} from '@angular/material-moment-adapter';
import {DateAdapter,  MAT_DATE_LOCALE} from '@angular/material/core';
import {MatDatepicker} from '@angular/material/datepicker';
import * as _moment from 'moment';
import { Moment } from 'moment';


@Component({
  selector: 'app-add-checklist-template',
  templateUrl: './add-checklist-template.component.html',
  styleUrls: ['./add-checklist-template.component.css'],
  providers: [
    {provide: DateAdapter, useClass: MomentDateAdapter, deps: [MAT_DATE_LOCALE]}
  ]
})
export class AddChecklistTemplateComponent implements OnInit {

  tipiModelliIT: string[];
  tipiModelliDE: string[];
  selectedTipoModelloIT: string;
  selectedTipoModelloDE: string;

  formControl = new FormControl('', [Validators.required, Validators.email]);

  constructor(public dialogRef: MatDialogRef<AddChecklistTemplateComponent>, @Inject(MAT_DIALOG_DATA) public data: ChecklistTemplate,
              public dataService: GestioneChecklistTemplateService,
              public gestioneAnagraficheService: GestioneAnagraficheService) {}

  ngOnInit() {
    this.gestioneAnagraficheService.getAnagraficheForChiave('REGOLE_TIPO_MODELLO', 'IT').subscribe(res => {
      this.tipiModelliIT = res;
    });

    this.gestioneAnagraficheService.getAnagraficheForChiave('REGOLE_TIPO_MODELLO', 'DE').subscribe(res => {
      this.tipiModelliDE = res;
    });
  }

  getErrorMessage() {
    return this.formControl.hasError('required') ? 'Required field' :
      this.formControl.hasError('email') ? 'Not a valid email' : '';
  }


  setAnno(normalizedYear: Moment, datepicker: MatDatepicker<Moment>) {
    this.data.anno = normalizedYear.year();
    datepicker.close();
  }

  setCampagna(normalizedYear: Moment, datepicker: MatDatepicker<Moment>) {
    this.data.campagna = normalizedYear.year();
    datepicker.close();
  }

  public confirmAdd(): void {
    this.data.idControllo = 1;
    this.data.tipoModelloIT = this.selectedTipoModelloIT;
    this.data.tipoModelloDE = this.selectedTipoModelloDE;
    this.dataService.addChecklistTemplate(this.data as ChecklistTemplate)
    .subscribe(hero => {
      // do something if you want
    });
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  submit() {}
}
