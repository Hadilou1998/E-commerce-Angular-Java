import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { ProductService } from '../../core/services/product.service';
import { AuthService } from '../../core/services/auth.service';

@Component({
  selector: 'app-product-list',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './product-list.component.html',
  styleUrl: './product-list.component.css'
})

export class ProductListComponent implements OnInit {

  products: any[] = [];

  constructor(
    private productService: ProductService,
    public auth: AuthService
  ) {}

  ngOnInit() {
    this.productService.getAll().subscribe({
      next: data => this.products = data
    });
  }

  addToCart(productId: number) {
    console.log('Ajout au panier', productId);
  }
}