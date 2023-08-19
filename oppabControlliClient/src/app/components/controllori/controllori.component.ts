import { DeleteControlloreComponent } from './../dialog/delete/delete-controllore/delete-controllore.component';
import { Controllore } from './../../model/controllore';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog, MatSort, MatTableDataSource, MatPaginator } from '@angular/material';
import { GestioneControlloriService } from 'src/app/services/gestione-controllori.service';
import { AddControlloreComponent } from '../dialog/add/add-controllore/add-controllore.component';
import { UpdateControlloreComponent } from '../dialog/update/update-controllore/update-controllore.component';
import { Config } from 'src/app/config/config';

@Component({
  selector: 'app-controllori',
  templateUrl: './controllori.component.html',
  styleUrls: ['./controllori.component.css']
})
export class ControlloriComponent implements OnInit {
  displayedColumns: string[] = ['username', 'nome', 'cognome', 'note', 'actions'];
  dataSource = null;
  controllori: Controllore[];


  constructor(private controlloriService: GestioneControlloriService, private dialog: MatDialog) { }

  @ViewChild(MatSort, {static: false}) sort: MatSort;
  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;

  pageSizeOptions = Config.pageSizeOptions;

  ngOnInit() {
    this.getControllori();
  }

  getControllori(): void {
    this.controlloriService.getControllori()
      .subscribe(response => {
        this.dataSource = new MatTableDataSource(response);
        this.controllori = response;
        this.refreshTable();
      });
  }

  deleteControllore(controllore: Controllore) {
    const dialogRef = this.dialog.open(DeleteControlloreComponent, {
      data: controllore, disableClose: true
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result === 1) {
        this.getControllori();
        const foundIndex = this.controllori.findIndex(x => x.id === controllore.id);
        this.removeItemFromTable(foundIndex);
      }
    });
  }

  addControllore(controllore: Controllore) {
    const dialogRef = this.dialog.open(AddControlloreComponent, {
      data: {controllore}, disableClose: true
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result === 1) {
        this.getControllori();
        this.addItemToTable(this.controlloriService.getDialogData());
      }
    });

  }

  updateControllore(controllore: Controllore) {
    const dialogRef = this.dialog.open(UpdateControlloreComponent, {
       data: controllore, disableClose: true
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result === 1) {
        const foundIndex = this.controllori.findIndex(x => x.id === controllore.id);
        this.controllori[foundIndex] = this.controlloriService.getDialogData();
        this.getControllori();
      }
    });
  }

  private removeItemFromTable(index: number) {
    this.controllori.splice(index, 1);
    this.refreshTable();
  }

  private addItemToTable(controllore: Controllore) {
    this.controllori.push(controllore);
    this.refreshTable();
  }

  private refreshTable(): void {
    this.dataSource.data = this.controllori;
    this.dataSource.sort = this.sort;
    this.dataSource.paginator = this.paginator;
  }

  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }





}

