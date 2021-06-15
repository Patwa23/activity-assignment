import { Component, OnInit } from '@angular/core';
// import {AccountService} from '../../services/accountservice/account.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent {
  profile = {
    name: 'XYZ',
    clientid: 158,
    email: 'XYZ@email.com',
    tel: '+31(0)6 1234 5678',
    accountmanager: 'XYZ Manager'
  }
}
