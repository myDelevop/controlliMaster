import { Azienda } from './../model/azienda';
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
export class GestioneAziendaService {

  private urlEndpoint = environment.gestioneAziendeUrl;
  dialogData: any;

  constructor(private http: HttpClient, private toastr: ToastrService) { }

  getDialogData() {
    return this.dialogData;
  }

  getAziende(): Observable<Azienda[]> {
    const url = `${this.urlEndpoint}/findAllAziendeValide`;
    return this.http.get<Azienda[]>(url).pipe(
        // do something if you want
        catchError(this.handleError<Azienda[]>('getAziende', [])));
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
