import { Component } from '@angular/core';
import { ConsultancyService } from './consultancy.service';

@Component({
  selector: 'app-root',
  standalone: true,
  templateUrl: './app.component.html',
  providers: [ConsultancyService]
})
export class AppComponent {
  title = 'madi-consultancy';
}
