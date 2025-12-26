import { Component, OnInit } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { AuthService } from './auth/auth.service';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'front-end';
  /*constructor(private auth: AuthService) {}

  ngOnInit(): void {
    this.auth.login({
      email: 'admintest@example.com',
      password: 'admin123'
    }).subscribe(token => {
      console.log('JWT:', token);
      this.auth.saveToken(token);
    });
  }*/
}