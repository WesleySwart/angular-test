import { provideRouter, RouterConfig }  from '@angular/router';

import { SESComponent } from './ses.component';
import { SQSComponent } from './sqs.component';
import { RegisterFormComponent } from './register-form.component';
import { LoginFormComponent } from './login-form.component';
import { ForgotPasswordFormComponent } from './forgot-form.component';

const routes: RouterConfig = [
  
  {
    path: '',
    redirectTo: '/login',
    pathMatch: 'full'
  },
  {
    path: 'sendEmail',
    component: SESComponent
  },
  {
    path: 'message',
    component: SQSComponent
  },
  {
    path: 'register',
    component: RegisterFormComponent
  },
  {
    path: 'login',
    component: LoginFormComponent
  },
  {
    path: 'forgot',
    component: ForgotPasswordFormComponent
  }
];

export const appRouterProviders = [
  provideRouter(routes)
];
