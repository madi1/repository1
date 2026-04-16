import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';

@Component({
  selector: 'app-contact-form',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './contact-form.html',
  styleUrl: './contact-form.css'
})
export class ContactForm {
  isSending = false; // Add this line

  constructor(private http: HttpClient) {}

  onSubmit(formValue: any) {
    this.isSending = true; // Set to true when user clicks send
    
    // Replace with your microservice URL
    const url = `${environment.apiUrl}/api/book-audit`;

    this.http.post(url, formValue)
      .subscribe({
        next: () => {
          alert('Success! Your audit request has been sent.');
          this.isSending = false;
        },
        error: (err) => {
          alert('Error: Could not reach the email service.');
          this.isSending = false;
          console.error(err);
        }
      });
  }
}