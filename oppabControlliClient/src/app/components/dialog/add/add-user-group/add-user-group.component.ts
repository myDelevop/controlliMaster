import { Component, OnInit, Inject } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { Utente } from 'src/app/model/utente';
import { GestioneUtentiService } from 'src/app/services/gestione-utenti.service';
import { UtenteGruppo } from 'src/app/model/utenteGruppo';
import { Gruppo } from 'src/app/model/gruppo';


@Component({
  selector: 'app-add-user-group',
  templateUrl: './add-user-group.component.html',
  styleUrls: ['./add-user-group.component.css']
})
export class AddUserGroupComponent implements OnInit {

  formControl = new FormControl('', [Validators.required]);
  users: Utente[];
  groups: Gruppo[];
  usersGroups: UtenteGruppo[];

   // rimuovo i gruppi del selectedUser. L'elenco completo lo memorizzo in usersGroups facendo un'unica richiesta http
  filteredGroups: Gruppo[];

  selectedUser: Utente = null;
  selectedGroup: Gruppo = null;

  constructor(public dialogRef: MatDialogRef<AddUserGroupComponent>, @Inject(MAT_DIALOG_DATA) public data: UtenteGruppo,
              public dataService: GestioneUtentiService) {}

  ngOnInit() {
    this.initializeData();
  }

  initializeData(): void {
    this.dataService.getUsers()
      .subscribe(response => {
        this.users = response;
      });

    this.dataService.getGroups()
      .subscribe(response => {
        this.groups = response;
    });

    this.dataService.getUsersGroups()
    .subscribe(response => {
      this.usersGroups = response;
  });

  }

  getErrorMessage() {
    return this.formControl.hasError('required') ? 'Required field' :
      this.formControl.hasError('email') ? 'Not a valid email' : '';
  }

  updateGroupList(event) {
    if (event.isUserInput) {
      this.filteredGroups = this.groups;
      const currentIds: number[] =
            this.usersGroups.filter(x => x.utente.idUtente === event.source.value.idUtente).map(y => y.gruppo.idGruppo);
      this.filteredGroups = this.groups.filter(obj => !currentIds.includes(obj.idGruppo));
    }
  }

  public confirmAdd(): void {
    this.data.utente = this.selectedUser;
    this.data.gruppo = this.selectedGroup;
    this.dataService.addUserGroup(this.data as UtenteGruppo)
    .subscribe(hero => {
      // do something if you want
    });
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  submit() {}
}
