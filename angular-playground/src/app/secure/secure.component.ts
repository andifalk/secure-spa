import { Component } from '@angular/core';

@Component({
  selector: 'app-secure',
  templateUrl: './secure.component.html',
  styleUrls: ['./secure.component.css']
})
export class SecureComponent {

  constructor() { }

  unsafeUrl = "javascript:alert('XSS')"
  unsafeHtml = "<em><script>alert('XSS')</script>Some Italic Text</em>";
  safeUrl = "https://spring.io"

  getHtmlSnippet() {
      return `<img src="nonexisting.jpg" onerror="alert('Failed to load image')">`;
  }
}
