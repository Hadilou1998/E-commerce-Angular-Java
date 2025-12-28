import { Component } from '@angular/core';
import { ProductService } from '../../core/services/product.service';
import { OnInit } from '@angular/core';

@Component({
  selector: 'app-product-list',
  imports: [],
  templateUrl: './product-list.component.html',
  styleUrl: './product-list.component.css'
})

export class ProductListComponent implements OnInit {

  products: any[] = [];

  constructor(private productService: ProductService) {}

  ngOnInit() {
    this.productService.getAll().subscribe(data => this.products = data);
  }
}