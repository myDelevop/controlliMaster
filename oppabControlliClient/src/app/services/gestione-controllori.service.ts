import { ControlloreStazioneForestale } from 'src/app/model/controlloreStazForestale';
import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { ToastrService } from 'ngx-toastr';
import { Observable, of } from 'rxjs';
import { Controllore } from '../model/controllore';
import { catchError } from 'rxjs/operators';


const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class GestioneControlloriService {
  private urlEndpoint = environment.gestioneControlloriUrl;
  dialogData: any;

  constructor(private http: HttpClient, private toastr: ToastrService) { }

  getDialogData() {
    return this.dialogData;
  }

  getControllori(): Observable<Controllore[]> {
    const url = `${this.urlEndpoint}/getListContr`;
    return this.http.get<Controllore[]>(url).pipe(
        // do something if you want
        catchError(this.handleError<Controllore[]>('getControllori', [])));
  }

  /*
  getControlloreNo404<Data>(id: number): Observable<Controllore> {
    const url = `${this.urlEndpoint}/?id=${id}`;
    return this.http.get<Controllore[]>(url).pipe(
      map(controllore => controllore[0]), tap(h => {
        const outcome = h ? 'fetched' : 'did not find';
        // this.log('${outcome} controllore id=${id}');
      }), catchError(this.handleError<Controllore>('getControllore id=${id}')));
  }
*/

  getControllore(id: number): Observable<Controllore> {
    const url = `${this.urlEndpoint}/${id}`;
    return this.http.get<Controllore>(url).pipe(
      // do something if you want
      catchError(this.handleError<Controllore>('getControllore id=${id}')));
  }

  addControllore(controllore: Controllore): Observable<Controllore> {
    this.dialogData = controllore;
    const url = `${this.urlEndpoint}/insertContr`;

    return this.http.post<Controllore>(url, controllore, httpOptions).pipe(
      catchError(this.handleError<Controllore>('addControllore')));
  }

  deleteControllore(controllore: Controllore): Observable<Controllore> {
    const url = `${this.urlEndpoint}/deleteContr/${controllore.id}_${controllore.idControllore}`;

    return this.http.delete(url, httpOptions).pipe(
      catchError(this.handleError<any>('deleteContr')));
  }

  updateControllore(controllore: Controllore): Observable<Controllore> {
    this.dialogData = controllore;
    const url = `${this.urlEndpoint}/modifyContr`;

    return this.http.put(url, controllore, httpOptions).pipe(
      // do something if you want
      catchError(this.handleError<any>('modifyContr'))
    );
  }

  /*
  searchControllori(term: string): Observable<Controllore[]> {
    if (!term.trim()) {
      return of([]);
    }
    return this.http.get<Controllori[]>(`${this.gestioneControlloreUrl}/?name=${term}`).pipe(
      // do something if you want
      catchError(this.handleError<Controllori[]>('Controllori', [])));
  }
  */

 getControlloriStazForestali(): Observable<ControlloreStazioneForestale[]> {
  const url = `${this.urlEndpoint}/getListContrStazFor`;
  return this.http.get<ControlloreStazioneForestale[]>(url).pipe(
      // do something if you want
      catchError(this.handleError<ControlloreStazioneForestale[]>('getControlloriStazForestali', [])));
}

/*
getControlloreStazForestaleNo404<Data>(id: number): Observable<ControlloreStazioneForestale> {
  const url = `${this.urlEndpoint}/?id=${id}`;
  return this.http.get<ControlloreStazioneForestale[]>(url).pipe(
    map(controlloreStazForestale => ControlloreStazioneForestale[0]), tap(h => {
      const outcome = h ? 'fetched' : 'did not find';
      // this.log('${outcome} controlloreStazForestale id=${id}');
    }), catchError(this.handleError<ControlloreStazioneForestale>('getControllore id=${id}')));
}
*/

getControlloreStazioneForestale(id: number): Observable<ControlloreStazioneForestale> {
  const url = `${this.urlEndpoint}/${id}`;
  return this.http.get<ControlloreStazioneForestale>(url).pipe(
    // do something if you want
    catchError(this.handleError<ControlloreStazioneForestale>('getControlloreStazioneForestale id=${id}')));
}

addControlloreStazioneForestale(controlloreStazioneForestale: ControlloreStazioneForestale):
      Observable<ControlloreStazioneForestale> {
  this.dialogData = controlloreStazioneForestale;
  const url = `${this.urlEndpoint}/insertContrStazFor`;

  return this.http.post<ControlloreStazioneForestale>(url, controlloreStazioneForestale, httpOptions).pipe(
    catchError(this.handleError<ControlloreStazioneForestale>('addControllore')));
}

deleteControlloreStazioneForestale(controlloreStazioneForestale: ControlloreStazioneForestale | number):
      Observable<ControlloreStazioneForestale> {
  const id = typeof controlloreStazioneForestale === 'number' ? controlloreStazioneForestale : controlloreStazioneForestale.id;
  const url = `${this.urlEndpoint}/deleteContrStazFor/${id}`;

  return this.http.delete(url, httpOptions).pipe(
    catchError(this.handleError<any>('deleteContrStazFor')));
}

updateControlloreStazioneForestale(controlloreStazioneForestale: ControlloreStazioneForestale):
      Observable<ControlloreStazioneForestale> {
  this.dialogData = controlloreStazioneForestale;
  const url = `${this.urlEndpoint}/modifyContrStazFor`;

  return this.http.put(url, controlloreStazioneForestale, httpOptions).pipe(
    // do something if you want
    catchError(this.handleError<any>('modifyContrStazFor'))
  );
}

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      // TODO: send the error to remote logging infrastructure
      console.error('Error occurred. Details: ' + error.name + ' ' + error.message);
      this.toastr.error('Si è verificato un errore, controlla la connessione ', '', {timeOut: 3000});

      return of(result as T);
    };
  }
}
