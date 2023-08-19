import { GestioneAnagraficheService } from './../../../../services/gestione-anagrafiche.service';
import { Anagrafica } from './../../../../model/anagrafica';
import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';

@Component({
  selector: 'app-delete-anagrafica',
  templateUrl: './delete-anagrafica.component.html',
  styleUrls: ['./delete-anagrafica.component.css']
})
export class DeleteAnagraficaComponent implements OnInit {

  constructor(public dialogRef: MatDialogRef<DeleteAnagraficaComponent>, @Inject(MAT_DIALOG_DATA) public data: Anagrafica,
              public dataService: GestioneAnagraficheService) { }

  ngOnInit() {}

  onNoClick(): void {
    this.dialogRef.close();
  }

  confirmDelete(): void {
    this.dataService.deleteAnagrafica(this.data)
      .subscribe(response => {
        // do something if you want
      });
  }
}
