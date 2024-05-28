import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FriendInfo, FriendResult } from './friends.query';
import { Observable } from 'rxjs';
import { AuthService } from '../auth/auth.service';

@Injectable({
  providedIn: 'root',
})
export class FriendsService {
  constructor(private http: HttpClient, private authService: AuthService) {}
  getFriendsInfo(): Observable<FriendInfo[]> {
    return this.http.get<FriendInfo[]>(
      `http://localhost:8080/player/getFriendsInfo?id=${this.authService.getLoginId()}`
    );
  }

  getFriendsResults(
    searchType: string,
    pattern: string
  ): Observable<FriendResult[]> {
    return this.http.get<FriendResult[]>(
      `http://localhost:8080/player/search?id=${this.authService.getLoginId()}&searchType=${searchType}&pattern=${pattern}`
    );
  }

  addFriend(id: number) {
    this.http
      .post(
        `http://localhost:8080/player/addFriend?id1=${this.authService.getLoginId()}&id2=${id}`,
        {
          responseType: 'text',
        }
      )
      .subscribe(
        (response) => {
          console.log(response);
        },
        (error) => {
          console.log(error);
        }
      );
  }
}
