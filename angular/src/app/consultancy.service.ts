import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ConsultancyService {
  private http = inject(HttpClient);
  // Replace 8080 with your Java Microservice port
  private apiUrl = 'http://localhost:8080/api/consultancy'; 

  getDisplayData(): Observable<any> {
    return this.http.get<any>(this.apiUrl);
  }
}
