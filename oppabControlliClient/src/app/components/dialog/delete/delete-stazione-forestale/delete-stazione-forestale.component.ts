import { GestioneStazioniForestaliService } from './../../../../services/gestione-stazioni-forestali.service';
import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { StazioneForestale } from 'src/app/model/stazioneForestale';

@Component({
  selector: 'app-delete-stazione-forestale',
  templateUrl: './delete-stazione-forestale.component.html',
  styleUrls: ['./delete-stazione-forestale.component.css']
})
export class DeleteStazioneForestaleComponent implements OnInit {

  constructor(public dialogRef: MatDialogRef<DeleteStazioneForestaleComponent>, @Inject(MAT_DIALOG_DATA) public data: StazioneForestale,
              public stazioniForestaliService: GestioneStazioniForestaliService) { }

  ngOnInit() {
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  confirmDelete(): void {
    this.stazioniForestaliService.deleteStazioneForestale(this.data)
      .subscribe(response => {
        // do something if you want
      });
  }

}
