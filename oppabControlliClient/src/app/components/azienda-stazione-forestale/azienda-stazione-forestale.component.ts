import { AddAziendaStazForestaleComponent } from './../dialog/add/add-azienda-staz-forestale/add-azienda-staz-forestale.component';
import { UpdateAziendaStazForestaleComponent } from './../dialog/update/update-azienda-staz-forestale/update-azienda-staz-forestale.component';
import { DeleteAziendaStazForestaleComponent } from './../dialog/delete/delete-azienda-staz-forestale/delete-azienda-staz-forestale.component';
import { AssegnazioneControlliService } from './../../services/assegnazione-controlli.service';
import { AziendaStazioneForestale } from './../../model/AziendaStazioneForestale';
import { Component, OnInit, ViewChild } from "@angular/core";
import { Config } from 'src/app/config/config';
import { MatSort, MatPaginator, MatDialog, MatTableDataSource } from '@angular/material';
import { GestioneControlloriService } from 'src/app/services/gestione-controllori.service';
// tslint:disable-next-line: max-line-length
import { DeleteControlloreStazForestaleComponent } from '../dialog/delete/delete-controllore-staz-forestale/delete-controllore-staz-forestale.component';


@Component({
  selector: 'app-azienda-stazione-forestale',
  templateUrl: './azienda-stazione-forestale.component.html',
  styleUrls: ['./azienda-stazione-forestale.component.css']
})
export class AziendaStazioneForestaleComponent implements OnInit {
  displayedColumns: string[] = ['stazioneForestale', 'cuaa', 'ragioneSociale',
                                'localita', 'campagna', 'note', 'actions'];
  pageSizeOptions = Config.pageSizeOptions;
  dataSource = null;

  aziendeStazioneForestali: AziendaStazioneForestale[];

  constructor(private assegnazioneControlloriService: AssegnazioneControlliService, private dialog: MatDialog) {}

  @ViewChild(MatSort, {static: false}) sort: MatSort;
  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;

  ngOnInit() {
    this.getAziendeStazForestali();
  }

  getAziendeStazForestali(): void {
    this.assegnazioneControlloriService.getAziendeStazioniForestali()
      .subscribe(response => {
        this.dataSource = new MatTableDataSource(response);

 /*       // abbiamo la necessita di ridefinire il filtro perche il datasource contiene oggetti innestati
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
*/
        this.aziendeStazioneForestali = response;
        this.refreshTable();
      });
  }

  deleteAziendaStazioneForestale(aziendaStazForestale: AziendaStazioneForestale) {
    const dialogRef = this.dialog.open(DeleteAziendaStazForestaleComponent, {
       data: aziendaStazForestale, disableClose: true });

    dialogRef.afterClosed().subscribe(result => {
      if (result === 1) {
        const foundIndex = this.aziendeStazioneForestali.findIndex(
          x => x.id === aziendaStazForestale.id);
        this.removeItemFromTable(foundIndex);
        this.getAziendeStazForestali();
      }
    });
  }

  addAziendaStazioneForestale(aziendaStazioneForestale: AziendaStazioneForestale) {
    const dialogRef = this.dialog.open(AddAziendaStazForestaleComponent, {
       data: {aziendaStazioneForestale}, disableClose: true });
    dialogRef.afterClosed().subscribe(result => {
      if (result === 1) {
        this.getAziendeStazForestali();
        this.addItemToTable(this.assegnazioneControlloriService.getDialogData());
      }
    });

  }

  updateAziendaStazioneForestale(aziendaStazioneForestale: AziendaStazioneForestale) {
    const dialogRef = this.dialog.open(UpdateAziendaStazForestaleComponent,
      { data: aziendaStazioneForestale, disableClose: true });

    dialogRef.afterClosed().subscribe(result => {
      if (result === 1) {
        const foundIndex = this.aziendeStazioneForestali.findIndex(
          x => x.id === aziendaStazioneForestale.id);
        this.aziendeStazioneForestali[foundIndex] = this.assegnazioneControlloriService.getDialogData();
        this.refreshTable();
      }
    });
  }

  private removeItemFromTable(index: number) {
    this.aziendeStazioneForestali.splice(index, 1);
    this.refreshTable();
  }

  private addItemToTable(aziendaStazioneForestale: AziendaStazioneForestale) {
    this.aziendeStazioneForestali.push(aziendaStazioneForestale);
    this.refreshTable();
  }

  private refreshTable(): void {
    this.dataSource.data = this.aziendeStazioneForestali;
    this.dataSource.sort = this.sort;
    this.dataSource.paginator = this.paginator;
  }

  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }
}