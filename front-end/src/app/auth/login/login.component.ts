import { Component } from "@angular/core";
import { CommonModule } from "@angular/common";
import { FormsModule } from "@angular/forms";
import { Router } from "@angular/router";
import { AuthService } from "../../core/services/auth.service";

@Component({
  selector: "app-login",
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: "./login.component.html",
  styleUrls: ["./login.component.css"]
})

export class LoginComponent {
  email: string = '';
  password: string = '';
  error: string = '';

  constructor(
    private auth: AuthService, 
    private router: Router
  ) {}

  login(): void {
    this.auth.login({ 
      email: this.email, 
      password: this.password 
    }).subscribe({
      next: (res) => {
        this.auth.saveAuth(res.token, res.role);
        this.router.navigate(['/products']);
      },
      error: () => {
        this.error = 'Email ou mot de passe incorrect.';
      }
    });
  }
}