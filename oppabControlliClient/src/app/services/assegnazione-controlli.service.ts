import { AziendaStazioneForestale } from 'src/app/model/AziendaStazioneForestale';
import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { ToastrService } from 'ngx-toastr';
import { Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';


const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};
@Injectable({
  providedIn: 'root'
})
export class AssegnazioneControlliService {
  private urlEndpoint = environment.gestioneAssegnazioneControlliUrl;
  dialogData: any;

  constructor(private http: HttpClient, private toastr: ToastrService) { }

  getDialogData() {
    return this.dialogData;
  }

  getAziendeStazioniForestali(): Observable<AziendaStazioneForestale[]> {
    const url = `${this.urlEndpoint}/getListAziStazFor`;
    return this.http.get<AziendaStazioneForestale[]>(url).pipe(
        // do something if you want
        catchError(this.handleError<AziendaStazioneForestale[]>('getAziendeStazioniForestali', [])));
  }

  addAziendaStazioneForestale(aziendaStazioneForestale: AziendaStazioneForestale): Observable<AziendaStazioneForestale> {
    this.dialogData = aziendaStazioneForestale;
    const url = `${this.urlEndpoint}/insertAziStazFor`;

    return this.http.post<AziendaStazioneForestale>(url, aziendaStazioneForestale, httpOptions).pipe(
      catchError(this.handleError<AziendaStazioneForestale>('addAziendaStazioneForestale')));
  }

  deleteAziendaStazioneForestale(aziendaStazioneForestale: AziendaStazioneForestale): Observable<AziendaStazioneForestale> {
    const url = `${this.urlEndpoint}/deleteAziStazFor/${aziendaStazioneForestale.id}`;

    return this.http.delete(url, httpOptions).pipe(
      catchError(this.handleError<any>('deleteAziStazFor')));
  }

  updateAziendaStazioneForestale(aziendaStazioneForestale: AziendaStazioneForestale): Observable<AziendaStazioneForestale> {
    this.dialogData = aziendaStazioneForestale;
    const url = `${this.urlEndpoint}/modifyAziStazFor`;

    return this.http.put(url, aziendaStazioneForestale, httpOptions).pipe(
      // do something if you want
      catchError(this.handleError<any>('modifyAziStazFor'))
    );
  }

  assegnamentoAutomaticoAziendeStazFor(): Observable<AziendaStazioneForestale[]> {
    const url = `${this.urlEndpoint}/assegnamentoAutomaticoAziendeStazFor`;
    return this.http.get<AziendaStazioneForestale[]>(url).pipe(
        // do something if you want
        catchError(this.handleError<AziendaStazioneForestale[]>
          ('assegnamentoAutomaticoAziendeStazFor', [])));
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
