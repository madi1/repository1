import { Routes } from '@angular/router';
import { ContactFormComponent } from './contact-form/contact-form'; 

export const routes: Routes = [
  // ... your other routes (e.g., { path: '', component: HomeComponent }),
  { 
    path: 'book-audit', 
    component: ContactFormComponent 
  }
];