import {Injectable} from '@angular/core';
import {Http} from "@angular/http";
import {User} from "../models/User";
import {Role} from "../models/User";
import {Routes, Server} from "../utils/ServerRoutes";
import {Observable} from "rxjs/Observable";


@Injectable()
export class AuthService {
  user: User;
  isLoggedIn: boolean = false;

  constructor(private http: Http) {
    this.user = new User();
  }

   
  getUsers(): Observable<User[]> {
    return this.http.get(Server.routeTo(Routes.USERS))
      .map(res => res.json())
  } 

  login(user: User) {
    return this.http.post(Server.routeTo(Routes.LOGIN), user)
      .map(res => {
        this.isLoggedIn = true;
        this.user = res.json();
        return this.user;
      })
  }
  
  
  deleteUser(user: User) {
    return this.http.post(Server.routeTo(Routes.DELETE), user)
      .map(res => {
        this.user = res.json();
        return this.user;
      })
  }

  ban(user: User) {
    return this.http.post(Server.routeTo(Routes.BAN), user)
      .map(res => {
        this.user = res.json();
        return this.user;
      })
  }  

    role(user: User, role: Role) {
    return this.http.post(Server.routeTo(Routes.ROLE), user,role)
      .map(res => {
        this.user = res.json();
        return this.user;
      })
  }  

  register(user: User) {
    return this.http.post(Server.routeTo(Routes.REGISTER), user)
      .map(res => {
        this.isLoggedIn = true;
        this.user = res.json();
        return this.user;
      })
  }

  logout() {
    return this.http.get(Server.routeTo(Routes.LOGOUT))
      .map(res => {
        this.user = new User();
        this.isLoggedIn = false;
      })
  }
}
