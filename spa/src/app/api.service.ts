import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(private http: HttpClient, private auth: AuthService) { }

  getHeaders() {
    return {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': 'Bearer ' + this.auth.getToken()
      })
    };
  }

  login(username, password) {
    return this.http.post(
      'http://127.0.0.1:8080/api/v1/login',
      {'username': username, 'password': password},
      { observe: 'response' }
    )
  }
  
  getPosts() {
    return this.http.get(
      'http://127.0.0.1:8080/api/v1/messages/all',
      this.getHeaders()
    );
  }

  insertPost(message: string) {
    return this.http.post(
      'http://127.0.0.1:8080/api/posts/add',
      {'message': message}
    )
  }
}
