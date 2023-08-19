import { ChecklistRegoleService } from './../../../../services/regole-checklist.service';
import { ChecklistRegola } from './../../../../model/checklistRegola';
import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';

@Component({
  selector: 'app-delete-regola-checklist',
  templateUrl: './delete-regola-checklist.component.html',
  styleUrls: ['./delete-regola-checklist.component.css']
})
export class DeleteRegolaChecklistComponent implements OnInit {

  constructor(public dialogRef: MatDialogRef<DeleteRegolaChecklistComponent>, @Inject(MAT_DIALOG_DATA) public data: ChecklistRegola,
              public dataService: ChecklistRegoleService) { }

  ngOnInit() {}

  onNoClick(): void {
    this.dialogRef.close();
  }

  confirmDelete(): void {
    this.dataService.deleteChecklistRegola(this.data)
      .subscribe(response => {
        // do something if you want
      });
  }

}
