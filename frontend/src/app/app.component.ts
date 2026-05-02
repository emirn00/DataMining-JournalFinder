import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  abstract: string = '';
  recommendations: string[] = [];
  loading: boolean = false;

  constructor(private http: HttpClient) {}

  findJournals() {
    if (!this.abstract.trim()) {
      alert('Please enter an abstract');
      return;
    }

    this.loading = true;
    // Backend API call (Spring Boot)
    this.http.post<string[]>('http://localhost:8080/api/recommend', { abstract: this.abstract })
      .subscribe({
        next: (data) => {
          this.recommendations = data;
          this.loading = false;
        },
        error: (err) => {
          console.error(err);
          this.loading = false;
          alert('Error finding journals. Make sure the backend is running.');
        }
      });
  }
}
