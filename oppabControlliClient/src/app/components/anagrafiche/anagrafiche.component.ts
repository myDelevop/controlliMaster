import { AddAnagraficaComponent } from './../dialog/add/add-anagrafica/add-anagrafica.component';
import { DeleteAnagraficaComponent } from './../dialog/delete/delete-anagrafica/delete-anagrafica.component';
import { GestioneAnagraficheService } from './../../services/gestione-anagrafiche.service';
import { Anagrafica } from './../../model/anagrafica';
import { Component, OnInit, ElementRef, ViewChild } from '@angular/core';
import { MatTableDataSource, MatSort, MatDialog, MatPaginator } from '@angular/material';
import { UpdateAnagraficaComponent } from '../dialog/update/update-anagrafica/update-anagrafica.component';
import { Config } from 'src/app/config/config';


@Component({
  selector: 'app-anagrafiche',
  templateUrl: './anagrafiche.component.html',
  styleUrls: ['./anagrafiche.component.css']
})
export class AnagraficheComponent implements OnInit {

  displayedColumns: string[] = ['chiave', 'valoreIT', 'valoreDE', 'annoValiditaInizio', 'annoValiditaFine', 'actions'];
  pageSizeOptions = Config.pageSizeOptions;
  dataSource = null;
  anagrafiche: Anagrafica[];

  constructor(private anagraficaService: GestioneAnagraficheService, private dialog: MatDialog) { }

  @ViewChild(MatSort, {static: false}) sort: MatSort;
  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;

  ngOnInit() {
    this.getAnagrafiche();
  }


  getAnagrafiche(): void {
    this.anagraficaService.getAnagrafiche()
      .subscribe(response => {
        this.dataSource = new MatTableDataSource(response);
        this.anagrafiche = response;
        this.refreshTable();
      });
  }


  deleteAnagrafica(anagrafica: Anagrafica) {
    const dialogRef = this.dialog.open(DeleteAnagraficaComponent, { data: anagrafica, disableClose: true });

    dialogRef.afterClosed().subscribe(result => {
      if (result === 1) {
        const foundIndex = this.anagrafiche.findIndex(x => x.id === anagrafica.id);
        this.removeItemFromTable(foundIndex);
        this.getAnagrafiche();
      }
    });
  }

  addAnagrafica(anagrafica: Anagrafica) {
    const dialogRef = this.dialog.open(AddAnagraficaComponent, { data: {anagrafica}, disableClose: true });
    dialogRef.afterClosed().subscribe(result => {
      if (result === 1) {
        this.getAnagrafiche();
        this.addItemToTable(this.anagraficaService.getDialogData());
      }
    });

  }

  updateAnagrafica(anagrafica: Anagrafica) {
    const dialogRef = this.dialog.open(UpdateAnagraficaComponent, {data: anagrafica, disableClose: true});

    dialogRef.afterClosed().subscribe(result => {
      if (result === 1) {
        const foundIndex = this.anagrafiche.findIndex(x => x.id === anagrafica.id);
        this.anagrafiche[foundIndex] = this.anagraficaService.getDialogData();
        this.getAnagrafiche();
      }
    });
  }

  private removeItemFromTable(index: number) {
    this.anagrafiche.splice(index, 1);
    this.refreshTable();
  }

  private addItemToTable(anagrafica: Anagrafica) {
    this.anagrafiche.push(anagrafica);
    this.refreshTable();
  }

  private refreshTable(): void {
    this.dataSource.data = this.anagrafiche;
    this.dataSource.sort = this.sort;
    this.dataSource.paginator = this.paginator;
  }

  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }
}
