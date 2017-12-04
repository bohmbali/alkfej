export class Role {
  static GUEST: String = "GUEST";
  static USER: String = "USER";
  static ADMIN: String = "ADMIN";
  static MODERATOR: String = "MODERATOR";
}

export class User {
  id: number;
  username: String;
  password: String;
  email: String;  
  nickname: String; 
  role: Role; 
  banned: boolean ;
  completed_games: String;
  
  constructor(username?: String, password?: String, email?: String,  nickname?: String, role?: Role, banned?: boolean, completed_games?: String) {
    this.username = username || "";
    this.password = password || "";
    this.email = email || "";
    this.role = role || Role.GUEST;
    this.nickname = nickname || "";
    this.banned = banned || false;
    this.completed_games || "";
  }
}
