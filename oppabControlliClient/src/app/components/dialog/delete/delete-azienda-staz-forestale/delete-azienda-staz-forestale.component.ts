import { AssegnazioneControlliService } from './../../../../services/assegnazione-controlli.service';
import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { AziendaStazioneForestale } from 'src/app/model/AziendaStazioneForestale';

@Component({
  selector: 'app-delete-azienda-staz-forestale',
  templateUrl: './delete-azienda-staz-forestale.component.html',
  styleUrls: ['./delete-azienda-staz-forestale.component.css']
})
export class DeleteAziendaStazForestaleComponent implements OnInit {

  constructor(public dialogRef: MatDialogRef<DeleteAziendaStazForestaleComponent>,
              @Inject(MAT_DIALOG_DATA) public data: AziendaStazioneForestale,
              public assegnazioneControlloriService: AssegnazioneControlliService) { }

  ngOnInit() {}

  onNoClick(): void {
    this.dialogRef.close();
  }

  confirmDelete(): void {
    this.assegnazioneControlloriService.deleteAziendaStazioneForestale(this.data)
      .subscribe(response => {
        // do something if you want
      });
  }
}
