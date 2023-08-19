import { GestioneChecklistTemplateService } from 'src/app/services/gestione-checklist-template.service';
import { UpdateRegolaChecklistComponent } from './../dialog/update/update-regola-checklist/update-regola-checklist.component';
import { DeleteRegolaChecklistComponent } from './../dialog/delete/delete-regola-checklist/delete-regola-checklist.component';
import { AddRegolaChecklistComponent } from './../dialog/add/add-regola-checklist/add-regola-checklist.component';
import { ChecklistRegoleService } from './../../services/regole-checklist.service';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator, MatSort, MatDialog, MatTableDataSource } from '@angular/material';
import { ChecklistRegola } from 'src/app/model/checklistRegola';
import { DeleteUserComponent } from '../dialog/delete/delete-user/delete-user.component';
import { ChecklistTemplate } from 'src/app/model/checklistTemaplate';
import { Config } from 'src/app/config/config';

@Component({
  selector: 'app-regole-checklist',
  templateUrl: './regole-checklist.component.html',
  styleUrls: ['./regole-checklist.component.css']
})
export class RegoleChecklistComponent implements OnInit {


//  anno.campagna.intervento.sottointervento.tipoCampione.tipoControllo
  displayedColumns: string[] = ['anno', 'campagna', 'misura', 'intervento', 'sottoIntervento',
                              'tipoCampione', 'tipoControllo', 'idChecklistTemplate', 'actions'];
  pageSizeOptions = Config.pageSizeOptions;
  dataSource = null;
  regoleChecklist: ChecklistRegola[];

  checklistTemplates: ChecklistTemplate[];

  constructor(private checklistRegoleService: ChecklistRegoleService, private checklistTemplateService: GestioneChecklistTemplateService,
              private dialog: MatDialog) { }

  @ViewChild(MatSort, {static: false}) sort: MatSort;
  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;

  ngOnInit() {
    this.getChecklistRegole();
    this.getChecklistTemplates();
  }


  getChecklistRegole(): void {
    this.checklistRegoleService.getChecklistRegole()
      .subscribe(response => {
        this.dataSource = new MatTableDataSource(response);
        this.regoleChecklist = response;
        this.refreshTable();
      });
  }

  getChecklistTemplates(): void {
    this.checklistTemplateService.getChecklistTemplates()
      .subscribe(response => {
        this.checklistTemplates = response;
      });
  }

  deleteChecklistRegola(checklistRegola: ChecklistRegola) {
    const dialogRef = this.dialog.open(DeleteRegolaChecklistComponent, { data: checklistRegola, disableClose: true });

    dialogRef.afterClosed().subscribe(result => {
      if (result === 1) {
        const foundIndex = this.regoleChecklist.findIndex(x => x.id === checklistRegola.id);
        this.removeItemFromTable(foundIndex);
      }
    });
  }

  addChecklistRegola(checklistRegola: ChecklistRegola) {
    const dialogRef = this.dialog.open(AddRegolaChecklistComponent, { data: {checklistRegola}, disableClose: true });
    dialogRef.afterClosed().subscribe(result => {
      if (result === 1) {
        this.getChecklistRegole();
        this.addItemToTable(this.checklistRegoleService.getDialogData());
      }
    });

  }

  updateChecklistRegola(checklistRegola: ChecklistRegola) {
    const dialogRef = this.dialog.open(UpdateRegolaChecklistComponent, {data: checklistRegola, disableClose: true});

    dialogRef.afterClosed().subscribe(result => {
      if (result === 1) {
        const foundIndex = this.regoleChecklist.findIndex(x => x.id === checklistRegola.id);
        this.regoleChecklist[foundIndex] = this.checklistRegoleService.getDialogData();
        this.refreshTable();
      }
    });
  }

  private removeItemFromTable(index: number) {
    this.regoleChecklist.splice(index, 1);
    this.refreshTable();
  }

  private addItemToTable(checklistRegola: ChecklistRegola) {
    this.regoleChecklist.push(checklistRegola);
    this.refreshTable();
  }

  private refreshTable(): void {
    this.dataSource.data = this.regoleChecklist;
    this.dataSource.sort = this.sort;
    this.dataSource.paginator = this.paginator;
  }

  nameChecklistTemplateFromId(idChecklistTemplate: number): string {
    let res = 'N/A';

    if (this.checklistTemplates !== undefined && this.checklistTemplates.length > 0) {
      const filteredString: string[] = this.checklistTemplates.filter(
                x => x.idChecklistTemplate === idChecklistTemplate).map(y => y.nomeIT);
      if (filteredString.length === 1) {
        res = filteredString.pop();
      }
    }

    return res;
  }

  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }
}
