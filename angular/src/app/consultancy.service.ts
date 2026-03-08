import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

// Match the Java DTO exactly
export interface ConsultancyResponse {
  message: string;
  status: string;
}

@Injectable({
  providedIn: 'root' // This makes the service a Singleton available everywhere
})
export class ConsultancyService {
  private apiUrl = 'http://localhost:8080/api/consultancy-message';

  constructor(private http: HttpClient) { }

  // Consume the Spring Boot endpoint
  getConsultancyData(): Observable<ConsultancyResponse> {
    // Angular automatically parses the JSON into your interface
    return this.http.get<ConsultancyResponse>(this.apiUrl);
  }
}
