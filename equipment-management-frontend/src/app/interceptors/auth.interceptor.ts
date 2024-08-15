import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { PersonneService } from '../Service/PersonneService';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(private personneService: PersonneService) {}

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(this.addAuthHeader(request)).pipe(
      catchError((error) => {
        if (error instanceof HttpErrorResponse && error.status === 401) {
          this.personneService.logout();
        }
        return throwError(error);
      })
    );
  }

  private addAuthHeader(request: HttpRequest<any>): HttpRequest<any> {
    const token = this.personneService.getToken();
    if (token) {
      return request.clone({
        setHeaders: {
          Authorization: `Bearer ${token}`
        }
      });
    }
    return request;
  }
}
