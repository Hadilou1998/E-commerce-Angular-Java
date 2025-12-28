import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({ providedIn: 'root' })
export class ProductService {

    private API = 'http://localhost:8080/products';

    constructor(private http: HttpClient) {}

    getAll() {
        return this.http.get<any[]>(this.API);
    }
}