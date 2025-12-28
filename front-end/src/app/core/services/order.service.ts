import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({ providedIn: 'root' })
export class OrderService {

    private API = 'http://localhost:8080/orders';

    constructor(private http: HttpClient) {}

    create(userId: number) {
        return this.http.post(`${this.API}/${userId}`, {});
    }
}