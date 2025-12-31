import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class ProductService {

    private API = 'http://localhost:8080/products';

    constructor(private http: HttpClient) {}

    getAll(): Observable<any[]> {
        return this.http.get<any[]>(this.API);
    }

    getById(id: number) {
        return this.http.get(`${this.API}/${id}`);
    }
}