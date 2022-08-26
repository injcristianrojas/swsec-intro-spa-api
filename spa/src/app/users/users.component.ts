import { Component, OnInit } from '@angular/core';
import { ApiService } from '../api.service';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

  users: Object;

  constructor(private api: ApiService) { }

  ngOnInit(): void {
    this.getUsers();
  }

  getUsers(): void {
    let userType = '1';
    this.api.getUsers(userType).subscribe(
      data => {
        this.users = data;
      },
      error => {
        console.log(error);
      }
    );
  }

}
