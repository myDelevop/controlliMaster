import { ChecklistTemplate } from './../model/checklistTemaplate';
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
export class GestioneChecklistTemplateService {
  private urlEndpoint = environment.gestioneChecklistTemplateUrl;
  dialogData: any;

  constructor(private http: HttpClient, private toastr: ToastrService) { }

  getDialogData() {
    return this.dialogData;
  }

  getChecklistTemplates(): Observable<ChecklistTemplate[]> {
    const url = `${this.urlEndpoint}/getListCheckTemplate`;
    return this.http.get<ChecklistTemplate[]>(url).pipe(
        // do something if you want
        catchError(this.handleError<ChecklistTemplate[]>('getListCheckTemplate', [])));
  }

  getChecklistTemplate(id: number): Observable<ChecklistTemplate> {
    const url = `${this.urlEndpoint}/${id}`;
    return this.http.get<ChecklistTemplate>(url).pipe(
      // do something if you want
      catchError(this.handleError<ChecklistTemplate>('getChecklistTemplate id=${id}')));
  }

  addChecklistTemplate(checklistTemplate: ChecklistTemplate): Observable<ChecklistTemplate> {
    this.dialogData = checklistTemplate;
    const url = `${this.urlEndpoint}/insertCheckTemplate`;

    return this.http.post<ChecklistTemplate>(url, checklistTemplate, httpOptions).pipe(
      catchError(this.handleError<ChecklistTemplate>('addChecklistTemplate')));
  }

  deleteChecklistTemplate(checklistTemplate: ChecklistTemplate): Observable<ChecklistTemplate> {
    const url = `${this.urlEndpoint}/deleteCheckTemplate/${checklistTemplate.id}`;

    return this.http.delete(url, httpOptions).pipe(
      catchError(this.handleError<any>('deleteChecklistTemplate')));
  }

  updateChecklistTemplate(checklistTemplate: ChecklistTemplate): Observable<ChecklistTemplate> {
    this.dialogData = checklistTemplate;
    const url = `${this.urlEndpoint}/modifyCheckTemplate`;

    return this.http.put(url, checklistTemplate, httpOptions).pipe(
      // do something if you want
      catchError(this.handleError<any>('updateChecklistTemplate'))
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
