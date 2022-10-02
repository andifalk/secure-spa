import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-secure',
  templateUrl: './secure.component.html',
  styleUrls: ['./secure.component.css']
})
export class SecureComponent implements OnInit {

  constructor(private route: ActivatedRoute) {}

  ngOnInit(): void {
    this.route.queryParams
      .subscribe(params => {
        console.log(params);
        this.urlQueryValue = params.value;
        console.log(this.urlQueryValue);
      }
    );
  }

  name = new FormControl('Test');
  urlQueryValue: string = '';
  unsafeUrl = "javascript:alert('XSS')"
  unsafeHtml = "<em><script>alert('XSS')</script>Some Italic Text</em>";
  safeUrl = "https://spring.io"

  getHtmlSnippet() {
      return `<img src="nonexisting.jpg" onerror="alert('Failed to load image')">`;
  }
}
