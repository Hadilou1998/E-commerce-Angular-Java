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

    login(data: LoginRequest): Observable<string> {
        return this.http.post(
            `${this.apiUrl}/login`, 
            data,
            { responseType: 'text' }
        );
    }

    saveToken(token: string): void {
        localStorage.setItem('token', token);
    }

    getToken(): string | null {
        return localStorage.getItem('token');
    }

    logout(): void {
        localStorage.removeItem('token');
    }

    isLoggedIn(): boolean {
        return !!this.getToken();
    }
}