import { Controllore } from './../../../../model/controllore';
import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { GestioneControlloriService } from 'src/app/services/gestione-controllori.service';

@Component({
  selector: 'app-delete-controllore',
  templateUrl: './delete-controllore.component.html',
  styleUrls: ['./delete-controllore.component.css']
})
export class DeleteControlloreComponent implements OnInit {

  constructor(public dialogRef: MatDialogRef<DeleteControlloreComponent>, @Inject(MAT_DIALOG_DATA) public data: Controllore,
              public controlloriService: GestioneControlloriService) { }

  ngOnInit() {
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  confirmDelete(): void {
    this.controlloriService.deleteControllore(this.data)
      .subscribe(response => {
        // do something if you want
      });
  }
}
