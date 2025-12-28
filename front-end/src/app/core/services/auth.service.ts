import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({ providedIn: 'root' })
export class AuthService {

    private API = 'http://localhost:8080/auth';

    constructor(private http: HttpClient) {}

    login(data: any) {
        return this.http.post<string>(`${this.API}/login`, data);
    }

    register(data: any) {
        return this.http.post(`${this.API}/register`, data);
    }

    saveToken(token: string) {
        localStorage.setItem('token', token);
    }

    logout() {
        localStorage.removeItem('token');
    }

    isAuthenticated(): boolean {
        return !!localStorage.getItem('token');
    }
}