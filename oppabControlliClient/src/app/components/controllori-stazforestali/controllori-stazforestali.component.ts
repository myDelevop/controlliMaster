import { Config } from './../../config/config';
import { GestioneControlloriService } from 'src/app/services/gestione-controllori.service';
import { Component, OnInit, ViewChild } from '@angular/core';
import { ControlloreStazioneForestale } from 'src/app/model/controlloreStazForestale';
import { MatDialog, MatSort, MatTableDataSource, MatPaginator } from '@angular/material';
// tslint:disable-next-line: max-line-length
import { DeleteControlloreStazForestaleComponent } from '../dialog/delete/delete-controllore-staz-forestale/delete-controllore-staz-forestale.component';
// tslint:disable-next-line: max-line-length
import { AddControlloreStazForestaleComponent } from './../dialog/add/add-controllore-staz-forestale/add-controllore-staz-forestale.component';
// tslint:disable-next-line: max-line-length
import { UpdateControlloreStazForestaleComponent } from '../dialog/update/update-controllore-staz-forestale/update-controllore-staz-forestale.component';

@Component({
  selector: 'app-controllori-stazforestali',
  templateUrl: './controllori-stazforestali.component.html',
  styleUrls: ['./controllori-stazforestali.component.css']
})
export class ControlloriStazforestaliComponent implements OnInit {
   displayedColumns: string[] = ['controllore',
                                 'descrizioneStazioneForestaleIt', 'descrizioneStazioneForestaleDe',
                                 'annoValiditaInizio', 'annoValiditaFine', 'note', 'actions'];
  pageSizeOptions = Config.pageSizeOptions;
  dataSource = null;
  controlloriStazForestali: ControlloreStazioneForestale[];


  constructor(private controlloriService: GestioneControlloriService, private dialog: MatDialog) {}

  @ViewChild(MatSort, {static: false}) sort: MatSort;
  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;

  ngOnInit() {
    this.getControlloriStazForestali();
  }

  getControlloriStazForestali(): void {
    this.controlloriService.getControlloriStazForestali()
      .subscribe(response => {
        this.dataSource = new MatTableDataSource(response);

        // abbiamo la necessita di ridefinire il filtro perche il datasource contiene oggetti innestati
        this.dataSource.filterPredicate = (data, filter: string)  => {
          const accumulator = (currentTerm: string | number, key: string | number) => {
            switch (key) {
              case 'controllore':
                return currentTerm + data.controllore.username + data.controllore.nome + data.controllore.cognome;
              case 'stazioneForestale':
                return currentTerm + data.stazioneForestale.descrIT + data.stazioneForestale.descrIT;
              default:
                return currentTerm + data[key];
              }
            };
          const dataStr = Object.keys(data).reduce(accumulator, '').toLowerCase();
          const transformedFilter = filter.trim().toLowerCase();
          return dataStr.indexOf(transformedFilter) !== -1;
        };

        // abbiamo la necessita di ridefinire l'ordinamento perche il datasource contiene oggetti innestati
        this.dataSource.sortingDataAccessor = (currentTerm, key) => {

          switch (key) {
            case 'controllore':
              return currentTerm.controllore.nome.trim().toLowerCase() + currentTerm.controllore.cognome.trim().toLowerCase();
            case 'descrizioneStazioneForestaleIt':
              return currentTerm.stazioneForestale.descrIT.trim().toLowerCase();
            case 'descrizioneStazioneForestaleDe':
              return currentTerm.stazioneForestale.descrDE.trim().toLowerCase();
            default:
              return currentTerm[key];
          }
        };

        this.controlloriStazForestali = response;
        this.refreshTable();
      });
  }

  deleteControlloreStazioneForestale(controlloriStazForestali: ControlloreStazioneForestale) {
    const dialogRef = this.dialog.open(DeleteControlloreStazForestaleComponent, { data: controlloriStazForestali, disableClose: true });

    dialogRef.afterClosed().subscribe(result => {
      if (result === 1) {
        const foundIndex = this.controlloriStazForestali.findIndex(x => x.id === controlloriStazForestali.id);
        this.getControlloriStazForestali();
        this.removeItemFromTable(foundIndex);
      }
    });
  }

  addControlloreStazioneForestale(controlloriStazForestali: ControlloreStazioneForestale) {
    const dialogRef = this.dialog.open(AddControlloreStazForestaleComponent, { data: {controlloriStazForestali}, disableClose: true });
    dialogRef.afterClosed().subscribe(result => {
      if (result === 1) {
        this.getControlloriStazForestali();
        this.addItemToTable(this.controlloriService.getDialogData());
      }
    });

  }

  updateControlloreStazioneForestale(controlloreStazioneForestale: ControlloreStazioneForestale) {
    const dialogRef = this.dialog.open(UpdateControlloreStazForestaleComponent,
      { data: controlloreStazioneForestale, disableClose: true });

    dialogRef.afterClosed().subscribe(result => {
      if (result === 1) {
        const foundIndex = this.controlloriStazForestali.findIndex(x => x.id === controlloreStazioneForestale.id);
        this.controlloriStazForestali[foundIndex] = this.controlloriService.getDialogData();
        this.refreshTable();
      }
    });
  }

  private removeItemFromTable(index: number) {
    this.controlloriStazForestali.splice(index, 1);
    this.refreshTable();
  }

  private addItemToTable(controlloreStazioneForestale: ControlloreStazioneForestale) {
    this.controlloriStazForestali.push(controlloreStazioneForestale);
    this.refreshTable();
  }

  private refreshTable(): void {
    this.dataSource.data = this.controlloriStazForestali;
    this.dataSource.sort = this.sort;
    this.dataSource.paginator = this.paginator;
  }

  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }
}

