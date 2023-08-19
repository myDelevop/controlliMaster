import { Component, HostListener } from '@angular/core';
import { AuthenticationService } from './services/auth/authentication.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'OPPAB Controlli';

  constructor(private authService: AuthenticationService) { }

  isLoggedIn() {
    return this.authService.isLoggedIn();
  }

  @HostListener('window:unload', [ '$event' ])
  unloadHandler(event) {
    this.authService.logout();
  }

  @HostListener('window:beforeunload', [ '$event' ])
  beforeUnloadHander(event) {
    this.authService.logout();
  }

}
