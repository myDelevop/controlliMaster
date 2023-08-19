import { AddChecklistTemplateComponent } from './../dialog/add/add-checklist-template/add-checklist-template.component';
import { DeleteChecklistTemplateComponent } from './../dialog/delete/delete-checklist-template/delete-checklist-template.component';
import { GestioneChecklistTemplateService } from './../../services/gestione-checklist-template.service';
import { Component, OnInit, ViewChild } from '@angular/core';
import { ChecklistTemplate } from 'src/app/model/checklistTemaplate';
import { MatDialog, MatSort, MatPaginator, MatTableDataSource } from '@angular/material';
import { UpdateChecklistTemplateComponent } from '../dialog/update/update-checklist-template/update-checklist-template.component';
import { Config } from 'src/app/config/config';

@Component({
  selector: 'app-checklist-template',
  templateUrl: './checklist-template.component.html',
  styleUrls: ['./checklist-template.component.css']
})
export class ChecklistTemplateComponent implements OnInit {

  displayedColumns: string[] = ['anno', 'campagna', 'nomeIT', 'nomeDE', 'descrIT', 'descrDE',  'tipoModelloIT', 'tipoModelloDE', 'actions'];
  pageSizeOptions = Config.pageSizeOptions;
  dataSource = null;
  checklistTemplates: ChecklistTemplate[];

  constructor(private checklistTemplateService: GestioneChecklistTemplateService, private dialog: MatDialog) { }

  @ViewChild(MatSort, {static: false}) sort: MatSort;
  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;

  ngOnInit() {
    this.getChecklistTemplates();
  }


  getChecklistTemplates(): void {
    this.checklistTemplateService.getChecklistTemplates()
      .subscribe(response => {
        this.dataSource = new MatTableDataSource(response);
        this.checklistTemplates = response;
        this.refreshTable();
      });
  }


  deleteChecklistTemplate(checklistTemplate: ChecklistTemplate) {
    const dialogRef = this.dialog.open(DeleteChecklistTemplateComponent, { data: checklistTemplate, disableClose: true });

    dialogRef.afterClosed().subscribe(result => {
      if (result === 1) {
        const foundIndex = this.checklistTemplates.findIndex(x => x.id === checklistTemplate.id);
        this.removeItemFromTable(foundIndex);
        this.getChecklistTemplates();
      }
    });
  }

  addChecklistTemplate(checklistTemplate: ChecklistTemplate) {
    const dialogRef = this.dialog.open(AddChecklistTemplateComponent, { data: { checklistTemplate }, disableClose: true });
    dialogRef.afterClosed().subscribe(result => {
      if (result === 1) {
        this.getChecklistTemplates();
        this.addItemToTable(this.checklistTemplateService.getDialogData());
      }
    });

  }

  updateChecklistTemplate(checklistTemplate: ChecklistTemplate) {
    const dialogRef = this.dialog.open(UpdateChecklistTemplateComponent, {data: checklistTemplate, disableClose: true});

    dialogRef.afterClosed().subscribe(result => {
      if (result === 1) {
        const foundIndex = this.checklistTemplates.findIndex(x => x.id === checklistTemplate.id);
        this.checklistTemplates[foundIndex] = this.checklistTemplateService.getDialogData();
        this.getChecklistTemplates();
      }
    });
  }

  private removeItemFromTable(index: number) {
    this.checklistTemplates.splice(index, 1);
    this.refreshTable();
  }

  private addItemToTable(checklistTemplate: ChecklistTemplate) {
    this.checklistTemplates.push(checklistTemplate);
    this.refreshTable();
  }

  private refreshTable(): void {
    this.dataSource.data = this.checklistTemplates;
    this.dataSource.sort = this.sort;
    this.dataSource.paginator = this.paginator;
  }

  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

}
