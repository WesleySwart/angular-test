import { Component }         from '@angular/core';
import { Router }            from '@angular/router';

import { Observable }        from 'rxjs/Observable';
import './rxjs-extensions';
import { HttpService }        from './http.service';

import { LoginFormComponent } from './login-form.component';

@Component({
    selector: 'ses-component',
    template: `
        <h1>Email</h1>
        <div>     
            <h2>{{title}}</h2>
            <button class="btn btn-default" (click)="getEmail()">Send Email</button>
        </div>
        <b>{{text}}</b>
    `,
    providers: [HttpService]
})

export class SESComponent {

    constructor(
        private httpService: HttpService,
        private router: Router) {}
    
    text: string;

    getEmail() {
        this.httpService
            .getEmail()
            .subscribe(
                data => this.text = data.email,
                error => console.log("Error HTTP GET Service")
            );
    }
}