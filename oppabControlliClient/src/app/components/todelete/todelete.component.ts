import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, FormBuilder } from '@angular/forms';
import { AziendaStazioneForestale } from 'src/app/model/AziendaStazioneForestale';
import { Azienda } from 'src/app/model/azienda';
import { StazioneForestale } from 'src/app/model/stazioneForestale';
import { CdkDragDrop, moveItemInArray, transferArrayItem } from '@angular/cdk/drag-drop';

@Component({
  selector: 'app-todelete',
  templateUrl: './todelete.component.html',
  styleUrls: ['./todelete.component.css']
})
export class TodeleteComponent implements OnInit {
/*
  stazioniForestali: StazioneForestale[] = [];
  aziende: Azienda[] = [];
  aziendeStazioniForestali: AziendaStazioneForestale[] = [];

  azStazForFilteredLeft: AziendaStazioneForestale[] = [];
  azStazForFilteredRight: AziendaStazioneForestale[] = [];

  defaultValue: StazioneForestale;
  disabled = false;*/

  ngOnInit() {/*
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


    if (sessionStorage.getItem('loggedGroup') === 'responsabileStazioneForestale') {
      // Bisogna prendere, se presente la stazione forestale del responsabile della stazione forestale,
      // altrimenti è possibile scegliere la stazione forestale dall'elenco di sinistra

      this.defaultValue = this.stazioniForestali[0];
      this.disabled = true;

      this.azStazForFilteredLeft = this.aziendeStazioniForestali
          .filter(asf => asf.stazioneForestale.id === this.defaultValue.id);
    }*/
  }

  public constructor() {
   }



  /*updateDynamicCuaaLeft(event) {
    let selected: AziendaStazioneForestale = null;

    if (event.isUserInput) {
      selected = event.source.value;

      this.azStazForFilteredLeft = this.aziendeStazioniForestali
        .filter(asf => asf.stazioneForestale.id === selected.id);
    }
  }

  updateDynamicCuaaRight(event) {
    let selected: AziendaStazioneForestale = null;

    if (event.isUserInput) {
      selected = event.source.value;

      this.azStazForFilteredRight = this.aziendeStazioniForestali
      .filter(asf => asf.stazioneForestale.id === selected.id);
    }
  }

  drop(event: CdkDragDrop<string[]>) {
    if (event.previousContainer === event.container) {
      moveItemInArray(event.container.data, event.previousIndex, event.currentIndex);
    } else {
      transferArrayItem(event.previousContainer.data,
                        event.container.data,
                        event.previousIndex,
                        event.currentIndex);
    }
  }

*/
}
