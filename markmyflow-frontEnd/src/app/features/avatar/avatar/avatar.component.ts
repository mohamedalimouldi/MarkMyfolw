import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-avatar',
  template: `<div [style.background-color]="backgroundColor">{{ initials }}</div>`,
  styles: [`
    div {
      width: 50px;
      height: 50px;
      border-radius: 50%;
      font-size: 20px;
      font-weight: bold;
      color: #fff;
      display: flex;
      justify-content: center;
      align-items: center;
      text-transform: uppercase;
    }
  `]
})
export class AvatarComponent {
  @Input() name: string;
  initials: string;
  backgroundColor: string;

  ngOnInit() {
    // Get the first two letters of the name
    const letters = this.name.match(/\b(\w)/g) || [];
    this.initials = (letters.shift() || '') + (letters.pop() || '');

    // Generate a random background color based on the name
    const colorIndex = Math.abs(this.hashCode(this.name)) % 6;
    const colors = ['#f44336', '#E91E63', '#9C27B0', '#673AB7', '#3F51B5', '#2196F3'];
    this.backgroundColor = colors[colorIndex];
  }

  // Generate a hash code for the given string
  private hashCode(str: string): number {
    let hash = 0;
    for (let i = 0; i < str.length; i++) {
      hash = ((hash << 5) - hash) + str.charCodeAt(i);
      hash |= 0;
    }
    return hash;
  }
}
