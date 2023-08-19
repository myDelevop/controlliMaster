import { ControlloreStazioneForestale } from 'src/app/model/controlloreStazForestale';
import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { GestioneControlloriService } from 'src/app/services/gestione-controllori.service';

@Component({
  selector: 'app-delete-controllore-staz-forestale',
  templateUrl: './delete-controllore-staz-forestale.component.html',
  styleUrls: ['./delete-controllore-staz-forestale.component.css']
})
export class DeleteControlloreStazForestaleComponent implements OnInit {

  constructor(public dialogRef: MatDialogRef<DeleteControlloreStazForestaleComponent>,
              @Inject(MAT_DIALOG_DATA) public data: ControlloreStazioneForestale,
              public controlloriService: GestioneControlloriService) { }

  ngOnInit() {}

  onNoClick(): void {
    this.dialogRef.close();
  }

  confirmDelete(): void {
    this.controlloriService.deleteControlloreStazioneForestale(this.data.id)
      .subscribe(response => {
        // do something if you want
      });
  }
}
