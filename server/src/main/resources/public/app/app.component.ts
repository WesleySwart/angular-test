import { Component } from '@angular/core';
import { ROUTER_DIRECTIVES } from '@angular/router';

import { HttpService }        from './http.service';
import './rxjs-extensions';

@Component({
    selector: 'my-app',
    template: `
      <h1>{{title}}</h1>
      <nav>
        <a [routerLink]="['/sendEmail']" routerLinkActive="active">Email</a>
        <a [routerLink]="['/message']" routerLinkActive="active">Messages</a>
        <a [routerLink]="['/register']" routerLinkActive="active">Register</a>
        <a [routerLink]="['/login']" routerLinkActive="active">Login</a>
      </nav>
      <router-outlet></router-outlet>
    `,
    styleUrls: ['app/app.component.css'],
    directives: [ROUTER_DIRECTIVES],
    providers: [HttpService]
})

export class AppComponent {
    title = "My Training App";
}