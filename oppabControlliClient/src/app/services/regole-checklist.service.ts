import { ChecklistRegola } from '../model/checklistRegola';
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
export class ChecklistRegoleService {
  private urlEndpoint = environment.gestioneChecklistUrl;
  dialogData: any;

  constructor(private http: HttpClient, private toastr: ToastrService) { }

  getDialogData() {
    return this.dialogData;
  }

  getChecklistRegole(): Observable<ChecklistRegola[]> {
    const url = `${this.urlEndpoint}/getListCheckRegole`;
    return this.http.get<ChecklistRegola[]>(url).pipe(
        // do something if you want
        catchError(this.handleError<ChecklistRegola[]>('getChecklistRegole', [])));
  }

  /*
  getChecklistRegolaNo404<Data>(id: number): Observable<ChecklistRegola> {
    const url = `${this.urlEndpoint}/?id=${id}`;
    return this.http.get<ChecklistRegola[]>(url).pipe(
      map(regola => ChecklistRegola[0]), tap(h => {
        const outcome = h ? 'fetched' : 'did not find';
        // this.log('${outcome} controllore id=${id}');
      }), catchError(this.handleError<Controllore>('getChecklistRegolaNo404 id=${id}')));
  }
*/

  getChecklistRegola(id: number): Observable<ChecklistRegola> {
    const url = `${this.urlEndpoint}/${id}`;
    return this.http.get<ChecklistRegola>(url).pipe(
      // do something if you want
      catchError(this.handleError<ChecklistRegola>('getChecklistRegola id=${id}')));
  }

  addChecklistRegola(checklistRegola: ChecklistRegola): Observable<ChecklistRegola> {
    this.dialogData = checklistRegola;
    const url = `${this.urlEndpoint}/insertCheckRegole`;

    return this.http.post<ChecklistRegola>(url, checklistRegola, httpOptions).pipe(
      catchError(this.handleError<ChecklistRegola>('addCheckListRegola')));
  }

  deleteChecklistRegola(checklistRegola: ChecklistRegola | number): Observable<ChecklistRegola> {
    const id = typeof checklistRegola === 'number' ? checklistRegola : checklistRegola.id;
    const url = `${this.urlEndpoint}/deleteCheckRegola/${id}`;

    return this.http.delete(url, httpOptions).pipe(
      catchError(this.handleError<any>('deleteChecklistRegola')));
  }

  updateChecklistRegola(checklistRegola: ChecklistRegola): Observable<ChecklistRegola> {
    this.dialogData = checklistRegola;
    const url = `${this.urlEndpoint}/modifyCheckRegole`;

    return this.http.put(url, checklistRegola, httpOptions).pipe(
      // do something if you want
      catchError(this.handleError<any>('updateChecklistRegola'))
    );
  }

  /*
  searchChecklistRegola(term: string): Observable<ChecklistRegola[]> {
    if (!term.trim()) {
      return of([]);
    }
    return this.http.get<ChecklistRegola[]>(`${this.gestioneControlloreUrl}/?name=${term}`).pipe(
      // do something if you want
      catchError(this.handleError<ChecklistRegola[]>('ChecklistRegola', [])));
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
