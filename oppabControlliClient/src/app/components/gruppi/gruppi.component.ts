import { Gruppo } from './../../model/gruppo';
import { Component, OnInit, ElementRef, ViewChild } from '@angular/core';
import { MatDialog, MatSort, MatTableDataSource, MatPaginator } from '@angular/material';
import { UpdateGroupComponent } from '../dialog/update/update-group/update-group.component';
import { AddGroupComponent } from '../dialog/add/add-group/add-group.component';
import { DeleteGroupComponent } from '../dialog/delete/delete-group/delete-group.component';
import { GestioneUtentiService } from 'src/app/services/gestione-utenti.service';
import { disableDebugTools } from '@angular/platform-browser';
import { Config } from 'src/app/config/config';

@Component({
  selector: 'app-gruppi',
  templateUrl: './gruppi.component.html',
  styleUrls: ['./gruppi.component.css']
})
export class GruppiComponent implements OnInit {

  displayedColumns: string[] = ['nome', 'descrizioneIT', 'descrizioneDE', 'actions'];
  pageSizeOptions = Config.pageSizeOptions;
  dataSource = null;
  groups: Gruppo[];

  constructor(private groupService: GestioneUtentiService, private dialog: MatDialog) { }

  @ViewChild(MatSort, {static: false}) sort: MatSort;
  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;

  ngOnInit() {
    this.getGroups();
  }

  getGroups(): void {
    this.groupService.getGroups()
      .subscribe(response => {
        this.dataSource = new MatTableDataSource(response);
        this.groups = response;
        this.refreshTable();
      });
  }


  deleteGroup(group: Gruppo) {
    const dialogRef = this.dialog.open(DeleteGroupComponent, {data: group, disableClose: true});

    dialogRef.afterClosed().subscribe(result => {
      if (result === 1) {
        const foundIndex = this.groups.findIndex(x => x.id === group.id);
        this.removeItemFromTable(foundIndex);
      }
    });
  }

  addGroup(group: Gruppo) {
    const dialogRef = this.dialog.open(AddGroupComponent, {data: {group}, disableClose: true});
    dialogRef.afterClosed().subscribe(result => {
      if (result === 1) {
        this.getGroups();
        this.addItemToTable(this.groupService.getDialogData());
      }
    });

  }

  updateGroup(group: Gruppo) {
    const dialogRef = this.dialog.open(UpdateGroupComponent, {data: group, disableClose: true});

    dialogRef.afterClosed().subscribe(result => {
      if (result === 1) {
        const foundIndex = this.groups.findIndex(x => x.id === group.id);
        this.groups[foundIndex] = this.groupService.getDialogData();
        this.getGroups();
      }
    });
  }

  private removeItemFromTable(index: number) {
    this.groups.splice(index, 1);
    this.refreshTable();
  }

  private addItemToTable(group: Gruppo) {
    this.groups.push(group);
    this.refreshTable();

  }

  private refreshTable(): void {


    this.dataSource.data = this.groups;
    this.dataSource.sort = this.sort;
    this.dataSource.paginator = this.paginator;
  }

  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }
}
