import { Location } from '@angular/common';
import { Component } from '@angular/core';

@Component({
  selector: 'app-about-app',
  templateUrl: './about-app.component.html',
  styleUrl: './about-app.component.css',
})
export class AboutAppComponent {
  constructor(private location: Location) {}
  goBack(): void {
    this.location.back();
  }
}
