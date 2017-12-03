import { Component, OnInit, HostListener } from '@angular/core';
import {User} from "../../models/User";
import {AbstractControl, FormControl, FormGroup, Validators} from '@angular/forms';
import {AuthService} from "../../services/auth.service";
import {Router} from "@angular/router";
import {Role} from "../../models/User";
@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

    registerForm: FormGroup = new FormGroup({
    username: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required]),
    cpassword: new FormControl('', [Validators.required]),
    email: new FormControl('', [Validators.required, Validators.email]),
    nickname: new FormControl('', [Validators.required]),
  });

  constructor(private authService: AuthService, private router: Router) {
  }

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
    this.authService.register(new User(this.username.value, this.password.value, this.email.value, Role.USER, this.nickname.value))
      .subscribe(
        res => this.router.navigate(['/modules']),
        err => console.log(err))
  }
  
  
   get username(): AbstractControl {
    return this.registerForm.get('username');
  }

  get password(): AbstractControl {
    return this.registerForm.get('password');
  }

  get email(): AbstractControl {
    return this.registerForm.get('email');
  }
  
  get nickname(): AbstractControl {
    return this.registerForm.get('nickname');
  }
}
