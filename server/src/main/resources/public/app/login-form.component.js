"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
var core_1 = require('@angular/core');
var router_1 = require('@angular/router');
var common_1 = require('@angular/common');
require('./rxjs-extensions');
var http_service_1 = require('./http.service');
var user_1 = require('./user');
var LoginFormComponent = (function () {
    function LoginFormComponent(httpService, router, location) {
        this.httpService = httpService;
        this.router = router;
        this.location = location;
        this.submitted = false;
        this.active = true;
        this.user = new user_1.User(this.username, this.password);
        this.location = location;
    }
    LoginFormComponent.prototype.authenticate = function () {
        var _this = this;
        this.submitted = true;
        this.httpService
            .postUser(this.user)
            .subscribe(function (data) { return _this.text = data.message; }, function (error) { return console.log("Error HTTP GET Service"); });
        //if user successfully logged, go to the send email page
        if (this.text === "Authentication Successful!") {
            this.allowed = true;
            this.router.navigateByUrl('/sendEmail');
        }
        else
            this.user = new user_1.User('', '');
    };
    LoginFormComponent.prototype.goRegister = function () {
        this.router.navigateByUrl('/register');
    };
    LoginFormComponent.prototype.goForgotPassword = function () {
        this.router.navigateByUrl('/forgot');
    };
    LoginFormComponent = __decorate([
        core_1.Component({
            selector: 'login-form',
            templateUrl: 'app/login-form.component.html',
            providers: [http_service_1.HttpService]
        }), 
        __metadata('design:paramtypes', [http_service_1.HttpService, router_1.Router, common_1.Location])
    ], LoginFormComponent);
    return LoginFormComponent;
}());
exports.LoginFormComponent = LoginFormComponent;
//# sourceMappingURL=login-form.component.js.map