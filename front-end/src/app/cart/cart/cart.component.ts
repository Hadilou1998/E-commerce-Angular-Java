import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CartService } from '../../core/services/cart.service';
import { AuthService } from '../../core/services/auth.service';

@Component({
  selector: 'app-cart',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './cart.component.html',
  styleUrl: './cart.component.css'
})
export class CartComponent implements OnInit {

  cart: any[] = [];

  constructor(
    private cartService: CartService,
    public auth: AuthService
  ) {}

  ngOnInit(): void {
    this.loadCart();
  }

  loadCart(): void {
    this.cartService.getCart().subscribe({
      next: data => this.cart = data,
      error: err => console.error('Error loading cart:', err)
    });
  }

  updateItem(item: any, event: Event): void {
    const quantity = Number((event.target as HTMLInputElement).value);

    if (quantity > 0) {
      this.cartService.updateItem(item.id, quantity).subscribe({
        next: () => this.loadCart(),
        error: err => console.error('Error updating item:', err)
      });
    }
  }

  removeItem(item: any): void {
    this.cartService.removeItem(item.id).subscribe({
      next: () => this.loadCart(),
      error: err => console.error('Error removing item:', err)
    });
  }

  total(): number {
    return this.cartService.getTotal(this.cart);
  }
}