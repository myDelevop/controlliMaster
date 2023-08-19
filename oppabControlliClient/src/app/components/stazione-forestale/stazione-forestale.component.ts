import { DeleteStazioneForestaleComponent } from './../dialog/delete/delete-stazione-forestale/delete-stazione-forestale.component';
import { GestioneStazioniForestaliService } from './../../services/gestione-stazioni-forestali.service';
import { StazioneForestale } from './../../model/stazioneForestale';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatSort, MatDialog, MatTableDataSource, MatPaginator } from '@angular/material';
import { AddStazioneForestaleComponent } from '../dialog/add/add-stazione-forestale/add-stazione-forestale.component';
import { UpdateStazioneForestaleComponent } from '../dialog/update/update-stazione-forestale/update-stazione-forestale.component';
import { Config } from 'src/app/config/config';


@Component({
  selector: 'app-stazione-forestale',
  templateUrl: './stazione-forestale.component.html',
  styleUrls: ['./stazione-forestale.component.css']
})
export class StazioneForestaleComponent implements OnInit {

  displayedColumns: string[] = ['numero', 'ispettoratoForestaleIT', 'ispettoratoForestaleDE', 'nome', 'descrIT',
                                'descrDE', 'annoValiditaInizio', 'annoValiditaFine', 'note', 'actions'];
  pageSizeOptions = Config.pageSizeOptions;
  dataSource = null;
  stazioniForestali: StazioneForestale[];

  constructor(private stazioniForestaliService: GestioneStazioniForestaliService, private dialog: MatDialog) { }

  @ViewChild(MatSort, {static: false}) sort: MatSort;
  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;

  ngOnInit() {
    this.getStazioniForestali();
  }

  getStazioniForestali(): void {
    this. stazioniForestaliService.getStazioniForestali()
      .subscribe(response => {
        this.dataSource = new MatTableDataSource(response);
        this. stazioniForestali = response;
        this.refreshTable();
      });
  }

  deleteStazioneForestale(stazioneForestale: StazioneForestale) {
    const dialogRef = this.dialog.open(DeleteStazioneForestaleComponent, {
      data: stazioneForestale,  disableClose: true
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result === 1) {
        const foundIndex = this.stazioniForestali.findIndex(x => x.id === stazioneForestale.id);
        this.removeItemFromTable(foundIndex);
        this.getStazioniForestali();
      }
    });
  }

  addStazioneForestale(stazioneForestale: StazioneForestale) {
    const dialogRef = this.dialog.open(AddStazioneForestaleComponent, { data: {stazioneForestale} });
    dialogRef.afterClosed().subscribe(result => {
      if (result === 1) {
        this.getStazioniForestali();
        this.addItemToTable(this.stazioniForestaliService.getDialogData());
      }
    });

  }

  updateStazioneForestale(stazioneForestale: StazioneForestale) {
    const dialogRef = this.dialog.open(UpdateStazioneForestaleComponent, {data: stazioneForestale, disableClose: true} );

    dialogRef.afterClosed().subscribe(result => {
      if (result === 1) {
        const foundIndex = this.stazioniForestali.findIndex(x => x.id === stazioneForestale.id);
        this.stazioniForestali[foundIndex] = this.stazioniForestaliService.getDialogData();
        this.getStazioniForestali();
      }
    });
  }

  private removeItemFromTable(index: number) {
    this.stazioniForestali.splice(index, 1);
    this.refreshTable();
  }

  private addItemToTable(stazioneForestale: StazioneForestale) {
    this.stazioniForestali.push(stazioneForestale);
    this.refreshTable();
  }

  private refreshTable(): void {
    this.dataSource.data = this.stazioniForestali;
    this.dataSource.sort = this.sort;
    this.dataSource.paginator = this.paginator;
  }

  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }


}
