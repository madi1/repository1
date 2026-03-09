import { Component } from '@angular/core';
import { ConsultancyService } from './consultancy.service'; // Import your service

@Component({
  selector: 'app-root',
  standalone: true,
  templateUrl: './app.component.html', // This links to your html file
  providers: [ConsultancyService]      // This links to your service
})
export class AppComponent {
  title = 'madi-consultancy';
}
Use code with caution.
