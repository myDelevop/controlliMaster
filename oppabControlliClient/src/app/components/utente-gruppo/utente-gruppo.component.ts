import { Component, OnInit, ViewChild } from '@angular/core';
import { UtenteGruppo } from 'src/app/model/utenteGruppo';
import { GestioneUtentiService } from 'src/app/services/gestione-utenti.service';
import { MatDialog, MatSort, MatTableDataSource, MatPaginator } from '@angular/material';
import { DeleteUserGroupComponent } from '../dialog/delete/delete-user-group/delete-user-group.component';
import { AddUserGroupComponent } from '../dialog/add/add-user-group/add-user-group.component';
import { Config } from 'src/app/config/config';



@Component({
  selector: 'app-utente-gruppo',
  templateUrl: './utente-gruppo.component.html',
  styleUrls: ['./utente-gruppo.component.css']
})
export class UtenteGruppoComponent implements OnInit {
  displayedColumns: string[] = ['username', 'nomeUtente', 'cognomeUtente', 'nomeGruppo',
                                'descrizioneGruppoIt', 'descrizioneGruppoDe', 'actions'];
  pageSizeOptions = Config.pageSizeOptions;
  dataSource = null;
  utentiGruppi: UtenteGruppo[];

  constructor(private userService: GestioneUtentiService, private dialog: MatDialog) {
  }

  @ViewChild(MatSort, {static: false}) sort: MatSort;
  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;

  ngOnInit() {
    this.getUtentiGruppi();
  }

  getUtentiGruppi(): void {
    this.userService.getUsersGroups().subscribe(response => {
      this.dataSource = new MatTableDataSource(response);

      // abbiamo la necessita di ridefinire il filtro perche il datasource contiene oggetti innestati
      this.dataSource.filterPredicate = (data, filter: string)  => {
        const accumulator = (currentTerm: string | number, key: string | number) => {
          switch (key) {
            case 'utente':
              return currentTerm + data.utente.username + data.utente.nome + data.utente.cognome;
            case 'gruppo':
              return currentTerm + data.gruppo.nome + data.gruppo.descrizioneIT + data.gruppo.descrizioneDE;
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
          case 'username':
            return currentTerm.utente.username.trim().toLowerCase();
          case 'nomeUtente':
            return currentTerm.utente.nome.trim().toLowerCase();
          case 'cognomeUtente':
            return currentTerm.utente.cognome.trim().toLowerCase();
          case 'nomeGruppo':
            return currentTerm.gruppo.nome.trim().toLowerCase();
          case 'descrizioneGruppoIt':
             return currentTerm.gruppo.descrizioneIT.trim().toLowerCase();
          case 'descrizioneGruppoDe':
            return currentTerm.gruppo.descrizioneDE.trim().toLowerCase();
          default:
            return currentTerm[key];
        }
      };
      this.utentiGruppi = response;
      this.refreshTable();
    });
  }

  deleteUtenteGruppo(utenteGruppo: UtenteGruppo) {
    const dialogRef = this.dialog.open(DeleteUserGroupComponent, { data: utenteGruppo, disableClose: true });

    dialogRef.afterClosed().subscribe(result => {
      if (result === 1) {
        const foundIndex = this.utentiGruppi.findIndex(x => x.id === utenteGruppo.id);
        this.getUtentiGruppi();
        this.removeItemFromTable(foundIndex);
      }
    });
  }

  addUtenteGruppo(utenteGruppo: UtenteGruppo) {
    const dialogRef = this.dialog.open(AddUserGroupComponent, { data: {utenteGruppo}, disableClose: true });
    dialogRef.afterClosed().subscribe(result => {
      if (result === 1) {
        this.getUtentiGruppi();
        this.addItemToTable(this.userService.getDialogData());
      }
    });

  }

/*  updateUtenteGruppo(i: number, id: number, idRel: number, utente: Utente, gruppo: Gruppo) {
    const dialogRef = this.dialog.open(UpdateUserGroupComponent, {
      data: {id, idRel, utente, gruppo}, disableClose: true
   });

    dialogRef.afterClosed().subscribe(result => {
      if (result === 1) {
        const foundIndex = this.utentiGruppi.findIndex(x => x.id === id);
        this.utentiGruppi[foundIndex] = this.userService.getDialogData();
        this.refreshTable();
      }
    });
  }
*/
  private removeItemFromTable(index: number) {
    this.utentiGruppi.splice(index, 1);
    this.refreshTable();
  }

  private addItemToTable(user: UtenteGruppo) {
    this.utentiGruppi.push(user);
    this.refreshTable();
  }

  private refreshTable(): void {
    this.dataSource.data = this.utentiGruppi;
    this.dataSource.sort = this.sort;
    this.dataSource.paginator = this.paginator;
  }

  applyFilter(filterValue: string) {

    this.dataSource.filter = filterValue.trim().toLowerCase();
  }
}
