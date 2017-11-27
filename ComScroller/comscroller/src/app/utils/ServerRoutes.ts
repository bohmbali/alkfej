export class Routes {
  static LOGIN: String = 'user/login';
  static REGISTER: String = 'user/registration';
  static LOGOUT: String = 'user/logout';
  static BAN: String = 'user/ban';
  static DELETE: String = 'user/delete';
  static ROLE: String = 'user/role';
  static USERS: String = 'user/users';
  
}

export class Server {
  private static address: String = 'localhost';
  private static port: String = '4200';
  private static prefix: String = 'api';

  static routeTo(route: String) {
    return `http://${Server.address}:${Server.port}/${Server.prefix}/${route}`
  }
}


