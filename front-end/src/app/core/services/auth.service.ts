import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface LoginRequest {
    email: string;
    password: string;
}

export interface LoginResponse {
    token: string;
    role: 'USER' | 'ADMIN';
}

@Injectable({ providedIn: 'root' })
export class AuthService {

    private API = 'http://localhost:8080/auth';

    constructor(private http: HttpClient) {}

    login(data: LoginRequest): Observable<LoginResponse> {
        return this.http.post<LoginResponse>(
            `${this.API}/login`, 
            data
        );
    }

    register(data: any): Observable<any> {
        return this.http.post(
            `${this.API}/register`, 
            data
        );
    }

    saveAuth(token: string, role: string): void {
        localStorage.setItem('token', token);
        localStorage.setItem('role', role);
    }
    getToken(): string | null {
        return localStorage.getItem('token');
    }

    getRole(): string | null {
        return localStorage.getItem('role');
    }

    isLoggedIn(): boolean {
        return!!this.getToken();
    }

    isAuthenticated(): boolean {
        return this.isLoggedIn();
    }

    isUser(): boolean {
        return this.getRole() === 'USER';
    }

    isAdmin(): boolean {
        return this.getRole() === 'ADMIN';
    }

    logout() {
        localStorage.removeItem('token');
    }
}