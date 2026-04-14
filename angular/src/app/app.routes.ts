import { Routes } from '@angular/router';
import { Landing } from './landing/landing';
import { ContactForm } from './contact-form/contact-form'; 

export const routes: Routes = [
  // This is the default "Yellow Pane" content
  { path: '', component: Landing }, 
  
  // This is what shows when you click the button for contact
  { path: 'book-audit', component: ContactForm } 
];