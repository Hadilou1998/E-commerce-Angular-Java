import { Routes } from '@angular/router';
import { LoginComponent } from './auth/login/login.component';
import { RegisterComponent } from './auth/register/register.component';
import { ProductListComponent } from './products/product-list/product-list.component';
import { CartComponent } from './cart/cart/cart.component';
import { AuthGuard } from './core/guards/auth.guard';
import { OrdersComponent } from './orders/orders/orders.component';

export const routes: Routes = [
    { path: 'login', component: LoginComponent },
    { path: 'register', component: RegisterComponent },
    { path: 'products', component: ProductListComponent },
    { path: 'cart', component: CartComponent, canActivate: [AuthGuard] },
    { path: 'orders', component: OrdersComponent, canActivate: [AuthGuard] },
    { path: '', redirectTo: 'login', pathMatch: 'full' },
];