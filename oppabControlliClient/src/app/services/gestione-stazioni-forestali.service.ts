import { ControlloreStazioneForestale } from 'src/app/model/controlloreStazForestale';
import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { ToastrService } from 'ngx-toastr';
import { Observable, of } from 'rxjs';
import { StazioneForestale } from '../model/stazioneForestale';
import { catchError } from 'rxjs/operators';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class GestioneStazioniForestaliService {
  private urlEndpoint = environment.gestioneStazioniForestaliUrl;
  dialogData: any;

  constructor(private http: HttpClient, private toastr: ToastrService) { }
  getDialogData() {
    return this.dialogData;
  }

  getStazioniForestali(): Observable<StazioneForestale[]> {
    const url = `${this.urlEndpoint}/getList`;
    return this.http.get<StazioneForestale[]>(url).pipe(
        // do something if you want
        catchError(this.handleError<StazioneForestale[]>('getStazioniForestali', [])));
  }

  /*
  getStazioniForestaliNo404<Data>(id: number): Observable<StazioneForestale> {
    const url = `${this.urlEndpoint}/?id=${id}`;
    return this.http.get<StazioneForestale[]>(url).pipe(
      map(stazFor => StazioneForestale[0]), tap(h => {
        const outcome = h ? 'fetched' : 'did not find';
        // this.log('${outcome} StazioneForestale id=${id}');
      }), catchError(this.handleError<StazioneForestale>('getStazFor id=${id}')));
  }
*/

  getStazioneForestale(id: number): Observable<StazioneForestale> {
    const url = `${this.urlEndpoint}/${id}`;
    return this.http.get<StazioneForestale>(url).pipe(
      // do something if you want
      catchError(this.handleError<StazioneForestale>('getStazioneForestale id=${id}')));
  }

  addStazioneForestale(stazioneForestale: StazioneForestale): Observable<StazioneForestale> {
    this.dialogData = stazioneForestale;
    const url = `${this.urlEndpoint}/insert`;

    return this.http.post<StazioneForestale>(url, stazioneForestale, httpOptions).pipe(
      catchError(this.handleError<StazioneForestale>('addStazioneForestale')));
  }

  deleteStazioneForestale(stazioneForestale: StazioneForestale): Observable<StazioneForestale> {
    const url = `${this.urlEndpoint}/delete/${stazioneForestale.id}_${stazioneForestale.idStazioneForestale}`;

    return this.http.delete(url, httpOptions).pipe(
      catchError(this.handleError<any>('deleteStazioneForestale')));
  }

  updateStazioneForestale(stazioneForestale: StazioneForestale): Observable<StazioneForestale> {
    this.dialogData = stazioneForestale;
    const url = `${this.urlEndpoint}/modify`;

    return this.http.put(url, stazioneForestale, httpOptions).pipe(
      // do something if you want
      catchError(this.handleError<any>('modifyContr'))
    );
  }

  /*
  searchStazioniForestali(term: string): Observable<StazioneForestale[]> {
    if (!term.trim()) {
      return of([]);
    }
    return this.http.get<StazioneForestale[]>(`${this.gestioneStazioneForURL}/?name=${term}`).pipe(
      // do something if you want
      catchError(this.handleError<StazioneForestale[]>('StazioneForestale', [])));
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
