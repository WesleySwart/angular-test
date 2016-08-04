import { Component } from '@angular/core'
import { Router }    from '@angular/router';
import { NgForm }    from '@angular/forms';
import { Location }  from '@angular/common';

import { Observable }        from 'rxjs/Observable';
import './rxjs-extensions';
import { HttpService }        from './http.service';

import { User } from './user';

@Component({
    selector: 'login-form',
    templateUrl: 'app/login-form.component.html',
    providers: [HttpService]
})

export class LoginFormComponent {

    constructor(
        private httpService: HttpService,
        private router: Router,
        private location: Location) {
            this.location = location;
        }

    username: string;
    password: string;
    text: string;

    submitted = false;
    active = true;
    public allowed: boolean;

    user = new User(this.username, this.password);

    authenticate() {
        this.submitted = true;
        this.httpService
            .postUser(this.user)
            .subscribe(
                data => this.text = data.message,
                error => console.log("Error HTTP GET Service")
            )

        //if user successfully logged, go to the send email page
        if(this.text === "Authentication Successful!")
        {
            this.allowed = true;
            this.router.navigateByUrl('/sendEmail');
        }
        else
            this.user = new User('','');
    }

    goRegister() {
        this.router.navigateByUrl('/register');
    }

    goForgotPassword() {
        this.router.navigateByUrl('/forgot');
    }
}

