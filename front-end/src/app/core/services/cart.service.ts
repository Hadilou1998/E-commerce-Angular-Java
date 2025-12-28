import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({ providedIn: 'root' })
export class CartService {

    private API = 'http://localhost:8080/cart';

    constructor(private http: HttpClient) {}

    add(item: any) {
        return this.http.post(this.API, item);
    }

    get(userId: number) {
        return this.http.get(`${this.API}/${userId}`);
    }
}