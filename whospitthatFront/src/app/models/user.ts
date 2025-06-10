import {Role} from './role';

export class user{

  id: string;
  name: string;
  email: string;
  password: string;
  highscore: number;
  pictureUrl: string;
  role: Role;

  constructor(id: string, name: string, email: string, password: string, highscore: number, pictureUrl: string, role: Role) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.password = password;
    this.highscore = highscore;
    this.pictureUrl = pictureUrl;
    this.role = role;
  }
}
