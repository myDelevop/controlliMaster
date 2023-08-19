import { Component, OnInit, ElementRef, ViewChild } from '@angular/core';
import { MatTableDataSource, MatSort, MatDialog, MatPaginator } from '@angular/material';
import { AddUserComponent } from '../dialog/add/add-user/add-user.component';
import { DeleteUserComponent } from '../dialog/delete/delete-user/delete-user.component';
import { UpdateUserComponent } from '../dialog/update/update-user/update-user.component';
import { Utente } from 'src/app/model/utente';
import { GestioneUtentiService } from 'src/app/services/gestione-utenti.service';
import { Config } from 'src/app/config/config';

@Component({
  selector: 'app-utenti',
  templateUrl: './utenti.component.html',
  styleUrls: ['./utenti.component.css']
})
export class UtentiComponent implements OnInit {

  displayedColumns: string[] = ['nome', 'cognome', 'username', 'dominio', 'email', 'actions'];
  pageSizeOptions = Config.pageSizeOptions;
  dataSource = null;
  users: Utente[];

  constructor(private userService: GestioneUtentiService, private dialog: MatDialog) { }

  @ViewChild(MatSort, {static: false}) sort: MatSort;
  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;

  ngOnInit() {
    this.getUsers();
  }


  getUsers(): void {
    this.userService.getUsers()
      .subscribe(response => {
        this.dataSource = new MatTableDataSource(response);
        this.users = response;
        this.refreshTable();
      });
  }


  deleteUser(user: Utente) {
    const dialogRef = this.dialog.open(DeleteUserComponent, { data: user, disableClose: true });

    dialogRef.afterClosed().subscribe(result => {
      if (result === 1) {
        this.getUsers();
        const foundIndex = this.users.findIndex(x => x.id === user.id);
        this.removeItemFromTable(foundIndex);
      }
    });
  }

  addUser(user: Utente) {
    const dialogRef = this.dialog.open(AddUserComponent, { data: {user}, disableClose: true });
    dialogRef.afterClosed().subscribe(result => {
      if (result === 1) {
        this.getUsers();
        this.addItemToTable(this.userService.getDialogData());
      }
    });

  }

  updateUser(user: Utente) {
    const dialogRef = this.dialog.open(UpdateUserComponent, {data: user, disableClose: true});

    dialogRef.afterClosed().subscribe(result => {
      if (result === 1) {
        const foundIndex = this.users.findIndex(x => x.id === user.id);
        this.users[foundIndex] = this.userService.getDialogData();
        this.getUsers();
      }
    });
  }

  isLogged(user: Utente): boolean {
    const loggedUser: Utente = JSON.parse(sessionStorage.getItem('loggedUser'));
    if (loggedUser.username === user.username) {
      return true;
    } else {
      return false;
    }
  }

  private removeItemFromTable(index: number) {
    this.users.splice(index, 1);
    this.refreshTable();
  }

  private addItemToTable(user: Utente) {
    this.users.push(user);
    this.refreshTable();
  }

  private refreshTable(): void {
    this.dataSource.data = this.users;
    this.dataSource.sort = this.sort;
    this.dataSource.paginator = this.paginator;
  }

  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

}
