import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class CartService {

    private API = 'http://localhost:8080/cart';

    constructor(private http: HttpClient) {}

    getCart(): Observable<any> {
        return this.http.get(this.API);
    }

    addToCart(productId: number, quantity: number = 1): Observable<any> {
        return this.http.post(`${this.API}/items/add`, { 
            productId, 
            quantity 
        });
    }

    updateItem(itemId: number, quantity: number): Observable<any> {
        return this.http.put(`${this.API}/items/${itemId}`, { 
            quantity 
        });
    }

    removeItem(itemId: number): Observable<any> {
        return this.http.delete(`${this.API}/items/${itemId}`);
    }
}