import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, BehaviorSubject, of } from 'rxjs';
import { environment } from 'src/environments/environment';
import { map, catchError } from 'rxjs/operators';
import { ActivatedRoute, Router } from '@angular/router';
import { Utente } from '../../model/utente';
import { JwtHelperService } from '@auth0/angular-jwt'

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private urlEndpoint = environment.authenticationUrl;

  constructor(private http: HttpClient, private router: Router) { }


  login(username: string, password: string) {
    let headers = new HttpHeaders();
    headers = headers.append('Authorization', 'Basic ' + btoa(`${username}:${password}`));
    headers.append('Content-Type', 'application/json');
    return this.http.post<any>(`${this.urlEndpoint}/login`, null, {headers})
          .pipe(map(res => {
            const helperJWT = new JwtHelperService();
            const jwtObj = helperJWT.decodeToken(res.jwt);
            const utente: Utente = new Utente(jwtObj);
            sessionStorage.setItem('loggedUser', JSON.stringify(utente));
            sessionStorage.setItem('token', res.jwt);
          }, catchError(this.handleError<any[]>('getUsers', []))
        ));
    }

  logout() {
    sessionStorage.removeItem('loggedUser');
    sessionStorage.removeItem('token');

    this.router.navigate(['/']);
  }


  public getObjToken(): any {
    if (this.isLoggedIn()) {
      const helperJWT = new JwtHelperService();
      const objToken = helperJWT.decodeToken(sessionStorage.getItem('token'));

      return objToken;
    }
  }


  public isLoggedIn(): boolean {
    const helperJWT = new JwtHelperService();
    const objToken = helperJWT.decodeToken(sessionStorage.getItem('token'));

    if (objToken && (new Date(objToken.exp * 1000) > new Date(Date.now()))) {
      return true;
    } else {
      return false;
    }

  }


  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      // TODO: send the error to remote logging infrastructure
      console.error('Error occurred. Details: ' + error.name + ' ' + error.message);

      return of(result as T);
    };
  }
}
