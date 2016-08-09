"use strict";
var router_1 = require('@angular/router');
var ses_component_1 = require('./ses.component');
var sqs_component_1 = require('./sqs.component');
var register_form_component_1 = require('./register-form.component');
var login_form_component_1 = require('./login-form.component');
var forgot_form_component_1 = require('./forgot-form.component');
var routes = [
    {
        path: '',
        redirectTo: '/login',
        pathMatch: 'full'
    },
    {
        path: 'sendEmail',
        component: ses_component_1.SESComponent
    },
    {
        path: 'message',
        component: sqs_component_1.SQSComponent
    },
    {
        path: 'register',
        component: register_form_component_1.RegisterFormComponent
    },
    {
        path: 'login',
        component: login_form_component_1.LoginFormComponent
    },
    {
        path: 'forgot',
        component: forgot_form_component_1.ForgotPasswordFormComponent
    }
];
exports.appRouterProviders = [
    router_1.provideRouter(routes)
];
//# sourceMappingURL=app.routes.js.map