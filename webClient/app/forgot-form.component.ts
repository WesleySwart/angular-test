import { Component } from '@angular/core'
import { Router }    from '@angular/router';
import { NgForm }    from '@angular/forms';
import { Location }  from '@angular/common';

import { Observable }        from 'rxjs/Observable';
import './rxjs-extensions';
import { HttpService }        from './http.service';

import { User } from './user';

@Component({
    selector: 'forgot_paswword-form',
    templateUrl: 'app/forgot-form.component.html',
    providers: [HttpService]
})

export class ForgotPasswordFormComponent {

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

    changePassword() {
        //Regex for Alphanumeric numbers
        var letters = /^[0-9a-zA-Z]+$/;
        
        //if the user entered a username and password that is alphanumeric then it'll be accepted
        if((this.user.password).match(letters))
        {
            this.submitted = true;
            this.httpService
                .changePassword(this.user)
                .subscribe(
                    data => this.text = data.message,
                    error => console.log("Error HTTP GET Service")
                )
        }
        else
        {
            this.text = "Invalid characters were used."
        }
        //if change password is successful go to the login page
        if(this.text == "Password Changed!")
        {
            this.allowed = true;
            this.router.navigateByUrl('/login');
        }
    
    }
}

