import { Injectable }    from '@angular/core';
import { Response, Http, Headers } from '@angular/http';
import { Observable } from 'rxjs/Observable';

import 'rxjs/Rx'
import { User } from './user';

@Injectable()
export class HttpService {

  public headers: Headers;

  private _emailUrl = 'http://localhost:8081/sendEmail';  // URL to web api
  private _messageUrl = 'http://localhost:8081/sendMessage';  // URL to web api
  private _receivemessageUrl = 'http://localhost:8081/receiveMessage';  // URL to web api
  private _registerUrl = 'http://localhost:8081/register-javaconfig'; //URL to web api
  private _loginUrl = 'http://localhost:8081/login-javaconfig'; //URL to web api
  private _forgotUrl = 'http://localhost:8081/forgot-javaconfig'; //URL to web api


  constructor(private http: Http) {
    this.headers = new Headers();
    this.headers.append('Content-Type', 'application/json');
  }

  getEmail() {
    return this.http.get(this._emailUrl)
      .map(response => response.json())
      .catch(this.handleError);
  }

  getSendMessage() {
    return this.http.get(this._messageUrl)
      .map(response => response.json())
      .catch(this.handleError);
  }

  getReceiveMessage() {
    return this.http.get(this._receivemessageUrl)
      .map(response => response.json())
      .catch(this.handleError);
  }

  postRegister(user: User) {
    let creds = JSON.stringify({ username: user.username, password: user.password });

    return this.http.post(this._registerUrl, creds, { headers: this.headers })
                    .map(response => response.json())
                    .catch(this.handleError);
  }

  postUser(user: User) {
    let creds = JSON.stringify({ username: user.username, password: user.password });

    return this.http.post(this._loginUrl, creds, { headers: this.headers })
                    .map(response => response.json())
                    .catch(this.handleError);
  }

  changePassword(user: User) {
    let creds = JSON.stringify({ username: user.username, password: user.password });

    return this.http.post(this._forgotUrl, creds, { headers: this.headers })
                    .map(response => response.json())
                    .catch(this.handleError);
  }

  private handleError(error: any) {
    console.error('An error occurred', error);
    return Observable.throw(error.message || error);
  }

}
