import { Component, OnInit, HostListener } from '@angular/core';
import {User} from "../../models/User";
import {AbstractControl, FormControl, FormGroup, Validators} from '@angular/forms';
import {AuthService} from "../../services/auth.service";
import {Router} from "@angular/router";


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
      constructor(private authService: AuthService, private router: Router) {
  }

model = new User("","");


  

// min-height
  document_height = document.documentElement.clientHeight;
  container_min_height = 0;

  resize(){
    this.document_height = document.documentElement.clientHeight;
    this.container_min_height = this.document_height - 120;
  }
  ngOnInit() {
    this.resize();
  }
  @HostListener('window:resize', ['$event'])onResize(event){
    this.resize();
  }
  
  submit() {
    this.authService.login(this.model)
      .subscribe(
        res => this.router.navigate(['/modules']),
        err => console.log(err))
  }

 
  
  
  
}
