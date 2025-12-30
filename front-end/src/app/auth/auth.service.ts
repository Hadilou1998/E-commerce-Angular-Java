import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";

interface LoginRequest {
    email: string;
    password: string;
}

@Injectable({
    providedIn: "root"
})

export class AuthService {

    private apiUrl = "http://localhost:8080/auth";

    constructor(private http: HttpClient) {}

    login(data: LoginRequest): Observable<{ token: string, role: string }> {
        return this.http.post<{ token: string, role: string }>(
            `${this.apiUrl}/login`, 
            data
        );
    }

    saveAuth(token: string, role: string): void {
        localStorage.setItem('token', token);
        localStorage.setItem('role', role);
    }

    getRole(): string | null {
        return localStorage.getItem('role');
    }

    getToken(): string | null {
        return localStorage.getItem('token');
    }

    isAdmin(): boolean {
        return this.getRole() === 'ADMIN';
    }

    isUser(): boolean {
        return this.getRole() === 'USER';
    }

    isLoggedIn(): boolean {
        return !!this.getToken();
    }

    logout(): void {
        localStorage.removeItem('token');
        localStorage.removeItem('role');
    }
}