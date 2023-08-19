import { GestioneChecklistTemplateService } from './../../../../services/gestione-checklist-template.service';
import { ChecklistTemplate } from './../../../../model/checklistTemaplate';
import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';

@Component({
  selector: 'app-delete-checklist-template',
  templateUrl: './delete-checklist-template.component.html',
  styleUrls: ['./delete-checklist-template.component.css']
})
export class DeleteChecklistTemplateComponent implements OnInit {

  constructor(public dialogRef: MatDialogRef<DeleteChecklistTemplateComponent>, @Inject(MAT_DIALOG_DATA) public data: ChecklistTemplate,
              public dataService: GestioneChecklistTemplateService) { }

  ngOnInit() {}

  onNoClick(): void {
    this.dialogRef.close();
  }

  confirmDelete(): void {
    this.dataService.deleteChecklistTemplate(this.data)
      .subscribe(response => {
        // do something if you want
      });
  }
}
