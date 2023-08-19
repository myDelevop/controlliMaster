import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { catchError, map, tap } from 'rxjs/operators';
import { ToastrService } from 'ngx-toastr';
import { environment } from 'src/environments/environment';
import { Utente } from '../model/utente';
import { Gruppo } from '../model/gruppo';
import { UtenteGruppo } from '../model/utenteGruppo';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class GestioneUtentiService {
  private urlEndpoint = environment.gestioneUtentiUrl;
  dialogData: any;

  constructor(private http: HttpClient, private toastr: ToastrService) { }

  getDialogData() {
    return this.dialogData;
  }

  getUsers(): Observable<Utente[]> {
    const url = `${this.urlEndpoint}/findAllUtentiValidi`;
    return this.http.get<Utente[]>(url).pipe(
        // do something if you want
        catchError(this.handleError<Utente[]>('getUsers', [])));
  }

  /*
  getUserNo404<Data>(id: number): Observable<User> {
    const url = `${this.urlEndpoint}/?id=${id}`;
    return this.http.get<User[]>(url).pipe(
      map(users => users[0]), tap(h => {
        const outcome = h ? 'fetched' : 'did not find';
        // this.log('${outcome} user id=${id}');
      }), catchError(this.handleError<User>('getUser id=${id}')));
  }
*/

  getUser(id: number): Observable<Utente> {
    const url = `${this.urlEndpoint}/${id}`;
    return this.http.get<Utente>(url).pipe(
      // do something if you want
      catchError(this.handleError<Utente>('getUser id=${id}')));
  }

  addUser(user: Utente): Observable<Utente> {
    this.dialogData = user;
    const url = `${this.urlEndpoint}/insertUtente`;

    return this.http.post<Utente>(url, user, httpOptions).pipe(
      catchError(this.handleError<Utente>('addHero')));
  }

  deleteUser(user: Utente): Observable<Utente> {
    const url = `${this.urlEndpoint}/utente/${user.id}_${user.idUtente}`;

    return this.http.delete(url, httpOptions).pipe(
      catchError(this.handleError<any>('deleteUser')));
  }

  updateUser(user: Utente): Observable<Utente> {
    this.dialogData = user;
    const url = `${this.urlEndpoint}/updateUtente`;

    return this.http.put(url, user, httpOptions).pipe(
      // do something if you want
      catchError(this.handleError<any>('updateUser'))
    );
  }

  /*
  searchUsers(term: string): Observable<User[]> {
    if (!term.trim()) {
      return of([]);
    }
    return this.http.get<User[]>(`${this.gestioneUtentiUrl}/?name=${term}`).pipe(
      // do something if you want
      catchError(this.handleError<User[]>('Users', [])));
  }
  */

  getGroups(): Observable<Gruppo[]> {
    const url = `${this.urlEndpoint}/findAllGruppiValidi`;
    return this.http.get<Gruppo[]>(url).pipe(
        // do something if you want
        catchError(this.handleError<Gruppo[]>('getGroups', [])));
  }

  /*
  getGruoupNo404<Data>(id: number): Observable<Gruppo> {
    const url = `${this.groupsUrl}/?id=${id}`;
    return this.http.get<Gruppo[]>(url).pipe(
      map(groups => groups[0]), tap(h => {
        const outcome = h ? 'fetched' : 'did not find';
        // this.log('${outcome} group id=${id}');
      }), catchError(this.handleError<Group>('getGroup id=${id}')));
  }
  */

  getGroup(id: number): Observable<Gruppo> {
    const url = `${this.urlEndpoint}/${id}`;
    return this.http.get<Gruppo>(url).pipe(
      // do something if you want
      catchError(this.handleError<Gruppo>('getGroup id=${id}')));
  }

  addGroup(group: Gruppo): Observable<Gruppo> {
    this.dialogData = group;
    const url = `${this.urlEndpoint}/insertGruppo`;

    return this.http.post<Gruppo>(url, group, httpOptions).pipe(
      catchError(this.handleError<Gruppo>('addGroup')));
  }

  deleteGroup(group: Gruppo): Observable<Gruppo> {
    const url = `${this.urlEndpoint}/gruppo/${group.id}_${group.idGruppo}`;

    return this.http.delete(url, httpOptions).pipe(
      catchError(this.handleError<any>('deleteGroup')));
  }

  updateGroup(group: Gruppo): Observable<Gruppo> {
    this.dialogData = group;
    const url = `${this.urlEndpoint}/updateGruppo`;

    return this.http.put(url, group, httpOptions).pipe(
      // do something if you want
      catchError(this.handleError<any>('updateGroup'))
    );
  }

  /*
  searchGroups(term: string): Observable<Group[]> {
    if (!term.trim()) {
      return of([]);
    }
    return this.http.get<Group[]>(`${this.groupsUrl}/?name=${term}`).pipe(
      // do something if you want
      catchError(this.handleError<Group[]>('Groups', [])));
  }
  */

 getUsersGroups(): Observable<UtenteGruppo[]> {
   const url = `${this.urlEndpoint}/findAllUtentiGruppiValidi`;
   return this.http.get<UtenteGruppo[]>(url).pipe(
      // do something if you want
      catchError(this.handleError<UtenteGruppo[]>('getUsers', [])));
}

  /*
  getUserGroupNo404<Data>(id: number): Observable<UtenteGruppo> {
    const url = `${this.urlEndpoint}/?id=${id}`;
    return this.http.get<User[]>(url).pipe(
      map(users => usersgroups[0]), tap(h => {
        const outcome = h ? 'fetched' : 'did not find';
        // this.log('${outcome} user id=${id}');
      }), catchError(this.handleError<User>('getUser id=${id}')));
  }
*/

getUserGroup(id: number): Observable<UtenteGruppo> {
  const url = `${this.urlEndpoint}/${id}`;
  return this.http.get<UtenteGruppo>(url).pipe(
    // do something if you want
    catchError(this.handleError<UtenteGruppo>('getUserGroup id=${id}')));
}

addUserGroup(userGroup: UtenteGruppo): Observable<UtenteGruppo> {
  this.dialogData = userGroup;
  const url = `${this.urlEndpoint}/insertUtenteGruppo`;

  return this.http.post<UtenteGruppo>(url, userGroup, httpOptions).pipe(
    catchError(this.handleError<UtenteGruppo>('addHero')));
}

deleteUserGroup(userGroup: UtenteGruppo | number): Observable<UtenteGruppo> {
  const id = typeof userGroup === 'number' ? userGroup : userGroup.id;
  const url = `${this.urlEndpoint}/utenteGruppo/${id}`;

  return this.http.delete(url, httpOptions).pipe(
    catchError(this.handleError<any>('deleteUserGroup')));
}

updateUserGroup(userGroup: UtenteGruppo): Observable<UtenteGruppo> {
  this.dialogData = userGroup;
  const url = `${this.urlEndpoint}/updateUtenteGruppo`;

  return this.http.put(url, userGroup, httpOptions).pipe(
    // do something if you want
    catchError(this.handleError<any>('updateUser'))
  );
}

/*
searchUsersGroups(term: string): Observable<UtenteGruppo[]> {
  if (!term.trim()) {
    return of([]);
  }
  return this.http.get<User[]>(`${this.gestioneUtentiUrl}/?name=${term}`).pipe(
    // do something if you want
    catchError(this.handleError<User[]>('Users', [])));
}
*/


  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      // TODO: send the error to remote logging infrastructure
      console.error('Error occurred. Details: ' + error.name + ' ' + error.message);
      this.toastr.error('Si è verificato un errore, controlla la connessione ', '', {timeOut: 3000});

      return of(result as T);
    };
  }

}
