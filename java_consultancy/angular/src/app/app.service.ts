import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AppService {

  constructor(private http: HttpClient) { }

  // This calls your Java ContentController
  getWelcomeMessage(): Observable<string> {
    return this.http.get('/api/content/welcome', { responseType: 'text' });
  }

  // This calls your Java ConsultancyController
  getConsultancyData(): Observable<any> {
    return this.http.get('/api/consultancy');
  }
}
