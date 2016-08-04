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
require('./rxjs-extensions');
var http_service_1 = require('./http.service');
var SESComponent = (function () {
    function SESComponent(httpService, router) {
        this.httpService = httpService;
        this.router = router;
    }
    SESComponent.prototype.getEmail = function () {
        var _this = this;
        this.httpService
            .getEmail()
            .subscribe(function (data) { return _this.text = data.email; }, function (error) { return console.log("Error HTTP GET Service"); });
    };
    SESComponent = __decorate([
        core_1.Component({
            selector: 'ses-component',
            template: "\n        <h1>Email</h1>\n        <div>     \n            <h2>{{title}}</h2>\n            <button class=\"btn btn-default\" (click)=\"getEmail()\">Send Email</button>\n        </div>\n        <b>{{text}}</b>\n    ",
            providers: [http_service_1.HttpService]
        }), 
        __metadata('design:paramtypes', [http_service_1.HttpService, router_1.Router])
    ], SESComponent);
    return SESComponent;
}());
exports.SESComponent = SESComponent;
//# sourceMappingURL=ses.component.js.map