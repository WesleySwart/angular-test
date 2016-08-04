import { Component }         from '@angular/core';
import { Router }            from '@angular/router';

import { Observable }        from 'rxjs/Observable';
import './rxjs-extensions';
import { HttpService }        from './http.service';

import { LoginFormComponent } from './login-form.component';

@Component({
    selector: 'sqs-component',
    template: `
        <h1>SQS</h1>
        <div>     
            <h2>{{title}}</h2>
            <button class="btn btn-default" (click)="getSendMessage()">Send SQS Message</button>
            <button class="btn btn-default" (click)="getReceiveMessage()">Receive SQS Message</button>
        </div>
        <b>{{text}}</b>
    `,
    providers: [HttpService]
})

export class SQSComponent {

    constructor(
        private httpService: HttpService,
        private router: Router) {}
    
    text: string;

    getSendMessage() {
        this.httpService
            .getSendMessage()
            .subscribe(
                data => this.text = data.email,
                error => console.log("Error HTTP GET Service")
            );
    }

    getReceiveMessage() {
        this.httpService
            .getReceiveMessage()
            .subscribe(
                data => this.text = data.email,
                error => console.log("Error HTTP GET Service")
            );
    }
}