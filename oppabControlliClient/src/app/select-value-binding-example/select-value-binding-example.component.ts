import { FormControl, FormGroup, FormBuilder } from '@angular/forms';
import { Azienda } from './../model/azienda';
import { Component, OnInit } from '@angular/core';
import { StazioneForestale } from '../model/stazioneForestale';
import { AziendaStazioneForestale } from '../model/AziendaStazioneForestale';

@Component({
  selector: 'app-select-value-binding-example',
  templateUrl: './select-value-binding-example.component.html',
  styleUrls: ['./select-value-binding-example.component.css']
})

export class SelectValueBindingExampleComponent {
  stazioniForestali: StazioneForestale[] = [];
  aziende: Azienda[] = [];
  aziendeStazioniForestali: AziendaStazioneForestale[] = [];

  /* vedere updateDynamicCuaa --> usare quello oppure mettere la logica alla fine?
  tramite il formControl ??? [formControl]="stazForestaliSelezionate" nel file html*/
  stazForestaliSelezionate = new FormControl();
  aziendeSelezionate = new FormControl();

  azStazForFiltered: AziendaStazioneForestale[] = [];

/*  public constructor() {
    let i;
    for (i = 0; i < 17; i++) {
      this.stazioniForestali.push(new StazioneForestale(i, i, 'Stazione -> ' + i));
    }
    for (i = 0; i < 47; i++) {
      this.aziende.push(new Azienda(i, 'cuaa ' + i, 'denominazione ' + i, 'SPA'));
    }
    for (i = 0; i < 43; i++) {
      if (i % 2 === 0) {
        this.aziendeStazioniForestali.push(new AziendaStazioneForestale(i, this.aziende[i], this.stazioniForestali[1], 2001));
      } else {
        this.aziendeStazioniForestali.push(new AziendaStazioneForestale(i, this.aziende[i], this.stazioniForestali[0], 2001));
      }
    }
  }

  todoXXX(event) {  }

  updateDynamicCuaa(event) {
    /*
      viene chiamato ogni volta che viene selezionato/deselezionata qualche stazione forestale
      dalla text area. In caso di selezione event.source.selected dobbiamo aggiungere altrimenti
      rimuovere.
      Questo permette di aggiornare i risultati in maniera dinamica. Si potrebbe usare anche il
      FormControl valorizzato alla chiusura della text area.
    */
  /*  let selected: AziendaStazioneForestale = null;
    if (event.isUserInput) {
      selected = event.source.value;

      if (event.source.selected) {
        // aggiungo gli elementi selezionati
        this.azStazForFiltered = this.azStazForFiltered.concat(this.aziendeStazioniForestali
          .filter(asf => asf.stazioneForestale.id === selected.id));
      } else {
        // rimuovo gli elementi selezionati
        this.azStazForFiltered = this.azStazForFiltered.filter(
          asf => asf.stazioneForestale.id !== selected.id);
      }

      this.azStazForFiltered.forEach(asf => console.log(asf.azienda.cuaa));

    }
  }*/

}

