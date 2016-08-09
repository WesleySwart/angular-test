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
var http_1 = require('@angular/http');
var Observable_1 = require('rxjs/Observable');
require('rxjs/Rx');
var HttpService = (function () {
    function HttpService(http) {
        this.http = http;
        this._emailUrl = 'http://localhost:8081/sendEmail'; // URL to web api
        this._messageUrl = 'http://localhost:8081/sendMessage'; // URL to web api
        this._receivemessageUrl = 'http://localhost:8081/receiveMessage'; // URL to web api
        this._registerUrl = 'http://localhost:8081/register-javaconfig'; //URL to web api
        this._loginUrl = 'http://localhost:8081/login-javaconfig'; //URL to web api
        this._forgotUrl = 'http://localhost:8081/forgot-javaconfig'; //URL to web api
        this.headers = new http_1.Headers();
        this.headers.append('Content-Type', 'application/json');
    }
    HttpService.prototype.getEmail = function () {
        return this.http.get(this._emailUrl)
            .map(function (response) { return response.json(); })
            .catch(this.handleError);
    };
    HttpService.prototype.getSendMessage = function () {
        return this.http.get(this._messageUrl)
            .map(function (response) { return response.json(); })
            .catch(this.handleError);
    };
    HttpService.prototype.getReceiveMessage = function () {
        return this.http.get(this._receivemessageUrl)
            .map(function (response) { return response.json(); })
            .catch(this.handleError);
    };
    HttpService.prototype.postRegister = function (user) {
        var creds = JSON.stringify({ username: user.username, password: user.password });
        return this.http.post(this._registerUrl, creds, { headers: this.headers })
            .map(function (response) { return response.json(); })
            .catch(this.handleError);
    };
    HttpService.prototype.postUser = function (user) {
        var creds = JSON.stringify({ username: user.username, password: user.password });
        return this.http.post(this._loginUrl, creds, { headers: this.headers })
            .map(function (response) { return response.json(); })
            .catch(this.handleError);
    };
    HttpService.prototype.changePassword = function (user) {
        var creds = JSON.stringify({ username: user.username, password: user.password });
        return this.http.post(this._forgotUrl, creds, { headers: this.headers })
            .map(function (response) { return response.json(); })
            .catch(this.handleError);
    };
    HttpService.prototype.handleError = function (error) {
        console.error('An error occurred', error);
        return Observable_1.Observable.throw(error.message || error);
    };
    HttpService = __decorate([
        core_1.Injectable(), 
        __metadata('design:paramtypes', [http_1.Http])
    ], HttpService);
    return HttpService;
}());
exports.HttpService = HttpService;
//# sourceMappingURL=http.service.js.map