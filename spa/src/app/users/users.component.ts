import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ApiService } from '../api.service';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

  users: Object;
  userType: string;

  constructor(private api: ApiService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.userType = this.route.snapshot.paramMap.get('userType');
    this.getUsers();
  }

  getUsers(): void {
    this.api.getUsers(this.userType).subscribe(
      data => {
        this.users = data;
      },
      error => {
        console.log(error);
      }
    );
  }

}
