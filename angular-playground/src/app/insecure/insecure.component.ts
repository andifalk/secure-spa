import { AfterViewInit, Component, ElementRef, OnInit, Sanitizer, ViewChild } from '@angular/core';
import { FormControl } from '@angular/forms';
import { DomSanitizer, SafeHtml, SafeScript, SafeUrl, SafeValue } from '@angular/platform-browser';
import { ActivatedRoute } from '@angular/router';
import * as DOMPurify from 'dompurify';

@Component({
  selector: 'app-insecure',
  templateUrl: './insecure.component.html',
  styleUrls: ['./insecure.component.css']
})
export class InsecureComponent implements OnInit, AfterViewInit {
  @ViewChild('myref') paragraphRef!: ElementRef;
  constructor(private sanitizer: DomSanitizer, private route: ActivatedRoute) {}

  ngOnInit(): void {
    this.route.queryParams
      .subscribe(params => {
        console.log(params);
        this.urlQueryValue = params.value;
        console.log(this.urlQueryValue);
        this.unsafeHtmlName = this.sanitizer.bypassSecurityTrustHtml(this.urlQueryValue)
        this.safeUrl = this.sanitizer.bypassSecurityTrustUrl(this.urlQueryValue);
      }
    );
  }
  
  urlQueryValue: string = '';

  name = new FormControl('Test');

  unsafeName = '' + this.name.value;
  unsafeUrl = 'javascript:alert("Hello XSS")'
  unsafeHtml = "<em><script>alert('HTML XSS')</script>Some Italic Text</em>";
  safeResourceUrl:SafeScript = this.sanitizer.bypassSecurityTrustResourceUrl(this.unsafeUrl)
  safeUrl: SafeUrl = this.sanitizer.bypassSecurityTrustUrl('');
  unsafeHtmlName: SafeHtml = this.sanitizer.bypassSecurityTrustHtml('');

  ngAfterViewInit(): void {
    //let payload: string = "<img src=\"none.jpg\" onerror=\"alert('ElementRef XSS')\"/>";
    //let payload: string = "<img src=\"none.jpg\" onerror=\"alert('ElementRef XSS')\"/>";
    this.paragraphRef.nativeElement.innerHTML = this.urlQueryValue;
    //this.paragraphRef.nativeElement.innerHTML = DOMPurify.sanitize(payload, {RETURN_TRUSTED_TYPE: true})
  }

}
