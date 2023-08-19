import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { ToastrService } from 'ngx-toastr';
import { Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Anagrafica } from '../model/anagrafica';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class GestioneAnagraficheService {
  private urlEndpoint = environment.gestioneAnagraficheUrl;
  dialogData: any;

  constructor(private http: HttpClient, private toastr: ToastrService) { }

  getDialogData() {
    return this.dialogData;
  }

  getAnagrafiche(): Observable<Anagrafica[]> {
    const url = `${this.urlEndpoint}/getList`;
    return this.http.get<Anagrafica[]>(url).pipe(
        // do something if you want
        catchError(this.handleError<Anagrafica[]>('getAnagrafiche', [])));
  }

  getAnagraficheForChiave(chiave: string, language: string): Observable<string[]> {
    const url = `${this.urlEndpoint}/getListForChiave/${chiave}/${language}`;
    return this.http.get<string[]>(url).pipe(
        // do something if you want
        catchError(this.handleError<string[]>('getAnagraficheForChiave', [])));
  }

  getListAnagrMisura(): Observable<string[]> {
    const url = `${this.urlEndpoint}/getListAnagrMisura`;
    return this.http.get<string[]>(url).pipe(
        // do something if you want
        catchError(this.handleError<string[]>('getListAnagrMisura', [])));
  }

  getListAnagrIntervento(misura: string): Observable<string[]> {
    const url = `${this.urlEndpoint}/getListAnagrIntervento/${misura}`;
    return this.http.get<string[]>(url).pipe(
        // do something if you want
        catchError(this.handleError<string[]>('getListAnagrIntervento', [])));
  }

  getListAnagrSottoIntervento(intervento: string): Observable<string[]> {
    const url = `${this.urlEndpoint}/getListAnagrSottointervento/${intervento}`;
    return this.http.get<string[]>(url).pipe(
        // do something if you want
        catchError(this.handleError<string[]>('getListAnagrSottoIntervento', [])));
  }

  getAnagrafica(id: number): Observable<Anagrafica> {
    const url = `${this.urlEndpoint}/${id}`;
    return this.http.get<Anagrafica>(url).pipe(
      // do something if you want
      catchError(this.handleError<Anagrafica>('getAnagrafica id=${id}')));
  }

  addAnagrafica(anagrafica: Anagrafica): Observable<Anagrafica> {
    this.dialogData = anagrafica;
    const url = `${this.urlEndpoint}/insert`;

    return this.http.post<Anagrafica>(url, anagrafica, httpOptions).pipe(
      catchError(this.handleError<Anagrafica>('addAnagrafica')));
  }

  deleteAnagrafica(anagrafica: Anagrafica): Observable<Anagrafica> {
    const url = `${this.urlEndpoint}/delete/${anagrafica.id}`;

    return this.http.delete(url, httpOptions).pipe(
      catchError(this.handleError<any>('deleteAnagrafica')));
  }

  updateAnagrafica(anagrafica: Anagrafica): Observable<Anagrafica> {
    this.dialogData = anagrafica;
    const url = `${this.urlEndpoint}/modify`;

    return this.http.put(url, anagrafica, httpOptions).pipe(
      // do something if you want
      catchError(this.handleError<any>('updateAnagrafica'))
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
