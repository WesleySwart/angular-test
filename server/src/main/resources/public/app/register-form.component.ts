import { Component } from '@angular/core'
import { Router }    from '@angular/router';
import { NgForm }    from '@angular/forms';
import { Location }  from '@angular/common';

import { Observable }        from 'rxjs/Observable';
import './rxjs-extensions';
import { HttpService }        from './http.service';

import { User } from './user';

@Component({
    selector: 'register-form',
    templateUrl: 'app/register-form.component.html',
    providers: [HttpService]
})

export class RegisterFormComponent {

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

    user = new User(this.username, this.password);
    
    postRegister() {
        //Regex for Alphanumeric numbers
        var letters = /^[0-9a-zA-Z]+$/;
        
        //if the user entered a username and password that is alphanumeric then it'll be accepted
        if((this.user.username).match(letters) && (this.user.password).match(letters))
        {    
            this.submitted = true;
            this.httpService
                .postRegister(this.user)
                .subscribe(
                    data => this.text = data.message,
                    error => console.log("Error HTTP GET Service")
                )
        }
        else
            this.text = "Invalid characters were used.";    
        //this.router.navigateByUrl('/sendEmail'); 
    }
}

