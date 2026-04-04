import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { environment } from '../environments/environment';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('angular');
  protected readonly backendPayload = signal('loading from backend...');
  protected readonly backendMessage = signal('loading from backend...');
  protected readonly backendStatus = signal('idle');

  constructor() {
    this.loadBackendData();
  }

  protected async loadBackendData() {
    try {
      this.backendStatus.set('fetching');
      const resp = await fetch(`${environment.apiUrl}/api/consultancy`);
      if (!resp.ok) {
        throw new Error('HTTP ' + resp.status);
      }
      const json = await resp.json();
      // expects { payload: 'my name is ABC', ... }
      this.backendPayload.set(json.payload ?? 'no payload');
      this.backendMessage.set(json.message ?? 'no message');
      this.backendStatus.set('ok');
    } catch (error) {
      const message = error instanceof Error ? error.message : String(error);
      this.backendPayload.set('error: ' + message);
      this.backendMessage.set('error');
      this.backendStatus.set('error');
    }
  }
}
